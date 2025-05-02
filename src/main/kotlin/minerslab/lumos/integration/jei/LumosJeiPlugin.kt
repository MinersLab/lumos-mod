package minerslab.lumos.integration.jei

import com.lowdragmc.lowdraglib.Platform
import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.registration.IRecipeCatalystRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import minerslab.lumos.Lumos
import minerslab.lumos.integration.jei.category.LumiumConversionRecipeJeiCategory
import minerslab.lumos.registry.fluid.ModFluids
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack


@JeiPlugin
class LumosJeiPlugin : IModPlugin {

    fun isDisabled() = Platform.isModLoaded("jei") || !Platform.isModLoaded("rei")

    override fun getPluginUid(): ResourceLocation = Lumos.id("jei_plugin")

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        if (isDisabled()) return
        val jeiHelpers = registration.jeiHelpers
        registration.addRecipeCategories(LumiumConversionRecipeJeiCategory(jeiHelpers))
    }

    override fun registerRecipeCatalysts(registration: IRecipeCatalystRegistration) {
        if (isDisabled()) return
        registration.addRecipeCatalyst(ItemStack(ModFluids.LUMIUM.get().bucket), LumiumConversionRecipeJeiCategory.RECIPE_TYPE)
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        if (isDisabled()) return
        LumiumConversionRecipeJeiCategory.registerRecipes(registration)
    }

}