package minerslab.lumos.mixin.client;

import minerslab.lumos.common.item.BaseTotemItem;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(method = "findTotem", remap = false, at = @At("RETURN"), cancellable = true)
    private static void findTotem(Player player, CallbackInfoReturnable <ItemStack> cir) {
        for (var hand : InteractionHand.values()) {
            var item = player.getItemInHand(hand);
            if (item.getItem() instanceof BaseTotemItem) {
                cir.setReturnValue(item);
                return;
            }
        }
    }

}
