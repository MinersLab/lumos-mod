package minerslab.lumos.common.item

import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class BaseTotemItem(properties: Properties) : Item(properties) {

    @Suppress("UNUSED_PARAMETER")
    fun onUseTotem(entity: LivingEntity, itemStack: ItemStack, hand: InteractionHand) {
        itemStack.shrink(1)
    }

}