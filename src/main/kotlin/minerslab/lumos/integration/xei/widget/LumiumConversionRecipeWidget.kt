package minerslab.lumos.integration.xei.widget

import com.lowdragmc.lowdraglib.gui.widget.ImageWidget
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget
import com.lowdragmc.lowdraglib.gui.widget.TankWidget
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup
import com.lowdragmc.lowdraglib.jei.IngredientIO
import com.lowdragmc.lowdraglib.utils.TagOrCycleItemStackTransfer
import com.mojang.datafixers.util.Either
import com.mojang.datafixers.util.Pair
import minerslab.lumos.api.gui.GuiTextures
import minerslab.lumos.common.recipe.LumiumConversionRecipe
import minerslab.lumos.registry.fluid.ModFluids
import net.minecraft.core.NonNullList
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient.TagValue
import net.neoforged.neoforge.fluids.FluidStack
import net.neoforged.neoforge.fluids.capability.templates.FluidTank
import net.neoforged.neoforge.items.ItemStackHandler

class LumiumConversionRecipeWidget(val recipe: LumiumConversionRecipe) : WidgetGroup() {

    companion object {
        const val WIDTH = 120
        const val HEIGHT = 20
    }

    init {
        setClientSideWidget()
        val worldFluidTank = FluidTank(1000)
        worldFluidTank.fluid = FluidStack(ModFluids.LUMIUM.get(), 1000)
        val worldFluidWidget = TankWidget(worldFluidTank, 4 + 18 + 4 + 20 + 4, 0, 20, 20, false, false)
            .setBackground(TankWidget.FLUID_SLOT_TEXTURE)
            .setIngredientIO(IngredientIO.CATALYST)
        val itemInput: List<Either<List<Pair<TagKey<Item>, Int>>, List<ItemStack>>> = recipe.inputItem.values.map {
            if (it is TagValue) Either.left(listOf(Pair.of(it.tag, it.items.first().count)))
            else Either.right(it.items.toList())
        }
        val itemInputWidget = SlotWidget(TagOrCycleItemStackTransfer(itemInput), 0, 2, 1, false, false)
            .setBackgroundTexture(SlotWidget.ITEM_SLOT_TEXTURE)
            .setIngredientIO(IngredientIO.INPUT)
        val itemOutputWidget = SlotWidget(ItemStackHandler(NonNullList.of(ItemStack.EMPTY, recipe.result.copy())), 0, 4 + 18 + 4 + 20 + 4 + 20 + 4 + 20 + 4, 1, false, false)
            .setBackgroundTexture(SlotWidget.ITEM_SLOT_TEXTURE)
            .setIngredientIO(IngredientIO.OUTPUT)
        addWidgets(
            ImageWidget(4 + 18 + 4, 2, 20, 15, GuiTextures.ARROW_RIGHT.get())
        )
        addWidgets(
            ImageWidget(4 + 18 + 4 + 20 + 4 + 20 + 4, 2, 20, 15, GuiTextures.ARROW_RIGHT.get())
        )
        addWidgets(itemInputWidget, worldFluidWidget, itemOutputWidget)
    }

}
