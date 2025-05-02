package minerslab.lumos.common.block

import minerslab.lumos.common.recipe.LumiumConversionRecipeInput
import minerslab.lumos.registry.recipe.ModRecipeTypes
import net.minecraft.core.BlockPos
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.FlowingFluid
import kotlin.jvm.optionals.getOrNull

class LumiumLiquidBlock(
    fluid: FlowingFluid,
    properties: Properties,
) : LiquidBlock(fluid, properties) {
    fun doRecipe(
        entity: ItemEntity,
        level: Level,
    ) {
        val input = LumiumConversionRecipeInput(entity.item)
        val recipe =
            level.server
                ?.recipeManager
                ?.getRecipeFor(ModRecipeTypes.LUMIUM_CONVERSION.get(), input, level)
                ?.getOrNull()
                ?.value ?: return
        val result = recipe.assemble(input, level.registryAccess())
        if (!result.isEmpty && recipe.matches(input, level)) {
            val newItem = entity.item.copy()
            newItem.count -= recipe.inputItem.items[0].count
            entity.item = newItem
            val newItemEntity = ItemEntity(level, entity.x, entity.y, entity.z, recipe.result.copy())
            level.addFreshEntity(newItemEntity)
        }
    }

    override fun entityInside(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        entity: Entity,
    ) {
        if (entity is ItemEntity) doRecipe(entity, level)
        if (entity is LivingEntity) {
            entity.addEffect(MobEffectInstance(MobEffects.GLOWING, 20 * 20))
        }
        super.entityInside(state, level, pos, entity)
    }
}
