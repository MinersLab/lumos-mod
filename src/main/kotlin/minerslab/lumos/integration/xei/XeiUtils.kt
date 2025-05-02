package minerslab.lumos.integration.xei

import minerslab.lumos.Lumos.ID
import net.minecraft.network.chat.Component

fun xeiCategoryTitle(category: String): Component =
    Component.translatable("title.$ID.xei.category.$category")