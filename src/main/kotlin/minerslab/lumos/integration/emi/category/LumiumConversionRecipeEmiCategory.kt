package minerslab.lumos.integration.emi.category

import dev.emi.emi.api.EmiRegistry
import dev.emi.emi.api.recipe.EmiRecipeCategory
import dev.emi.emi.api.stack.EmiStack
import minerslab.lumos.Lumos
import minerslab.lumos.common.recipe.LumiumConversionRecipe
import minerslab.lumos.integration.emi.LumosEmiRecipe
import minerslab.lumos.integration.emi.LumosEmiRecipeHandler
import minerslab.lumos.integration.xei.widget.LumiumConversionRecipeWidget
import minerslab.lumos.integration.xei.xeiCategoryTitle
import minerslab.lumos.registry.fluid.ModFluids
import minerslab.lumos.registry.recipe.ModRecipeTypes
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.RecipeHolder

class LumiumConversionRecipeEmiCategory : EmiRecipeCategory(Lumos.id("lumium_conversion"), EmiStack.of(ModFluids.LUMIUM.get().bucket)) {
    override fun getName() = xeiCategoryTitle("lumium_conversion")

    inner class Recipe(
        private val recipe: RecipeHolder<LumiumConversionRecipe>,
    ) : LumosEmiRecipe({
            LumiumConversionRecipeWidget(recipe.value)
        }) {
        override fun getId(): ResourceLocation = recipe.id

        override fun getCategory() = this@LumiumConversionRecipeEmiCategory

        override fun getDisplayWidth() = LumiumConversionRecipeWidget.WIDTH

        override fun getDisplayHeight() = LumiumConversionRecipeWidget.HEIGHT
    }

    fun register(registry: EmiRegistry) {
        registry.addCategory(this)
        registry.addRecipeHandler(null, LumosEmiRecipeHandler())
        registry.addWorkstation(this, EmiStack.of(ModFluids.LUMIUM.get()))
        registry.recipeManager
            .getAllRecipesFor(ModRecipeTypes.LUMIUM_CONVERSION.get())
            .map(::Recipe)
            .map(registry::addRecipe)
    }
}
