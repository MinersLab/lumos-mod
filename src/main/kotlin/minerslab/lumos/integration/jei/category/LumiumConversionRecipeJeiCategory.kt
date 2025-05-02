package minerslab.lumos.integration.jei.category

import com.lowdragmc.lowdraglib.jei.ModularUIRecipeCategory
import mezz.jei.api.gui.drawable.IDrawable
import mezz.jei.api.gui.drawable.IDrawableStatic
import mezz.jei.api.helpers.IJeiHelpers
import mezz.jei.api.recipe.RecipeType
import mezz.jei.api.registration.IRecipeRegistration
import minerslab.lumos.Lumos
import minerslab.lumos.common.recipe.LumiumConversionRecipe
import minerslab.lumos.integration.jei.getRecipeHoldersFromType
import minerslab.lumos.integration.jei.wrapper.LumiumConversionRecipeWrapper
import minerslab.lumos.integration.xei.widget.LumiumConversionRecipeWidget
import minerslab.lumos.integration.xei.xeiCategoryTitle
import minerslab.lumos.registry.fluid.ModFluids
import minerslab.lumos.registry.recipe.ModRecipeTypes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeHolder


class LumiumConversionRecipeJeiCategory(helpers: IJeiHelpers) : ModularUIRecipeCategory<RecipeHolder<LumiumConversionRecipe>>({ LumiumConversionRecipeWrapper(it.value) }) {

    companion object {
        @Suppress("UNCHECKED_CAST")
        val RECIPE_TYPE = RecipeType(Lumos.id("lumium_conversion"), RecipeHolder::class.java) as RecipeType<RecipeHolder<LumiumConversionRecipe>>

        fun registerRecipes(registration: IRecipeRegistration) {
            registration.addRecipes(
                RECIPE_TYPE,
                getRecipeHoldersFromType(ModRecipeTypes.LUMIUM_CONVERSION.get())
            )
        }

    }

    private val background = helpers.guiHelper.createBlankDrawable(LumiumConversionRecipeWidget.WIDTH, LumiumConversionRecipeWidget.HEIGHT)

    private val icon: IDrawable = helpers.guiHelper
        .createDrawableItemStack(ItemStack(ModFluids.LUMIUM.get().bucket))

    override fun getIcon() = icon

    @Deprecated("Deprecated in Java")
    @Suppress("removal")
    override fun getBackground(): IDrawableStatic = background

    override fun getTitle() = xeiCategoryTitle("lumium_conversion")

    override fun getRecipeType() = RECIPE_TYPE

}