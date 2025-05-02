package minerslab.lumos.registry.item

import com.tterrag.registrate.util.DataIngredient
import com.tterrag.registrate.util.entry.ItemEntry
import minerslab.lumos.LumosMod.REGISTRATE
import minerslab.lumos.api.registry.IBootstrap
import minerslab.lumos.common.item.BaseTotemItem
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.ItemLike

object ModItems : IBootstrap {
    @JvmField
    val TOTEM_OF_UNDYING_FRAGMENT: ItemEntry<Item> =
        REGISTRATE
            .item("totem_of_undying_fragment", ::Item)
            .defaultModel()
            .defaultLang()
            .register()

    @JvmField
    val DEFECTIVE_TOTEM_OF_UNDYING: ItemEntry<BaseTotemItem> =
        REGISTRATE
            .item("defective_totem_of_undying", ::BaseTotemItem)
            .properties {
                it.stacksTo(1).rarity(Rarity.UNCOMMON)
            }.defaultModel()
            .defaultLang()
            .recipe {
                item,
                provider,
                ->
                provider.singleItem(DataIngredient.items(TOTEM_OF_UNDYING_FRAGMENT as ItemLike), RecipeCategory.COMBAT, item, 2, 1)
            }.register()
}
