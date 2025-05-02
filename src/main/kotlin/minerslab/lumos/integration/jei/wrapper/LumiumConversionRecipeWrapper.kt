package minerslab.lumos.integration.jei.wrapper

import com.lowdragmc.lowdraglib.jei.ModularWrapper
import minerslab.lumos.common.recipe.LumiumConversionRecipe
import minerslab.lumos.integration.xei.widget.LumiumConversionRecipeWidget

class LumiumConversionRecipeWrapper(recipe: LumiumConversionRecipe) : ModularWrapper<LumiumConversionRecipeWidget>(
    LumiumConversionRecipeWidget(recipe)
)
