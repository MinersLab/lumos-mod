package minerslab.lumos.mixin;


import minerslab.lumos.common.item.BaseTotemItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static minerslab.lumos.registry.item.ModItems.TOTEM_OF_UNDYING_FRAGMENT;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract ItemStack getItemInHand(InteractionHand hand);

    @SuppressWarnings("DataFlowIssue")
    @Unique
    @Nullable
    private ServerPlayer lumos$selfPlayer() {
        try {
            return (ServerPlayer) (Object) this;
        } catch (Throwable throwable) {
            return null;
        }
    }

    @Unique
    @NotNull
    private LivingEntity lumos$self() {
        return (LivingEntity) (Object) this;
    }

    @Inject(method = "checkTotemDeathProtection", remap = false, at = @At("HEAD"), cancellable = true)
    private void checkTotemDeathProtection(DamageSource damageSource, CallbackInfoReturnable <Boolean> cir) {
        if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return;
        var self = lumos$self();
        ItemStack itemStack = ItemStack.EMPTY;
        for (InteractionHand interactionhand : InteractionHand.values()) {
            ItemStack newItemStack = getItemInHand(interactionhand);
            var result = net.neoforged.neoforge.common.CommonHooks.onLivingUseTotem(self, damageSource, itemStack, interactionhand);
            if (newItemStack.getItem() instanceof BaseTotemItem totem && result) {
                itemStack = newItemStack.copy();
                totem.onUseTotem(self, newItemStack, interactionhand);
                break;
            } else if (newItemStack.is(Items.TOTEM_OF_UNDYING) && result) {
                itemStack = newItemStack.copy();
                newItemStack.shrink(1);
                break;
            }
        }
        if (!itemStack.isEmpty()) {
            var serverPlayer = lumos$selfPlayer();
            if (serverPlayer != null) {
                serverPlayer.awardStat(Stats.ITEM_USED.get(itemStack.getItem()), 1);
                CriteriaTriggers.USED_TOTEM.trigger(serverPlayer, itemStack);
                serverPlayer.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
                if (itemStack.is(Items.TOTEM_OF_UNDYING)) {
                    serverPlayer.addItem(TOTEM_OF_UNDYING_FRAGMENT.asStack());
                }
            }

            self.setHealth(1.0F);
            self.removeEffectsCuredBy(net.neoforged.neoforge.common.EffectCures.PROTECTED_BY_TOTEM);
            self.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
            self.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
            self.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
            self.level().broadcastEntityEvent(self, EntityEvent.TALISMAN_ACTIVATE);
        }
        cir.setReturnValue(!itemStack.isEmpty());
    }

}
