package minerslab.lumos.registry.recipe

import minerslab.lumos.Lumos.ID
import minerslab.lumos.api.recipe.LumosRecipeType
import minerslab.lumos.api.registry.IBootstrap
import minerslab.lumos.common.recipe.LumiumConversionRecipe
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.RecipeType
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object ModRecipeTypes : IBootstrap {

    @JvmField
    val RECIPE_TYPES: DeferredRegister<RecipeType<*>> = DeferredRegister.create(Registries.RECIPE_TYPE, ID)

    @JvmField
    val LUMIUM_CONVERSION: DeferredHolder<RecipeType<*>, LumosRecipeType<LumiumConversionRecipe>> =
        RECIPE_TYPES.register("lumium_conversion") { location -> LumosRecipeType(location) }

    override fun bootstrap(bus: IEventBus) {
        RECIPE_TYPES.register(bus)
    }

}