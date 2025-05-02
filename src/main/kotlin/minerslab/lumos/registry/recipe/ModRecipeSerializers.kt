package minerslab.lumos.registry.recipe

import minerslab.lumos.Lumos.ID
import minerslab.lumos.api.registry.IBootstrap
import minerslab.lumos.common.recipe.LumiumConversionRecipeSerializer
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.RecipeSerializer
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

object ModRecipeSerializers : IBootstrap {
    @JvmField
    val RECIPE_SERIALIZERS: DeferredRegister<RecipeSerializer<*>> = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ID)

    @JvmField
    val LUMIUM_CONVERSION_RECIPE_SERIALIZER: DeferredHolder<RecipeSerializer<*>, LumiumConversionRecipeSerializer> =
        RECIPE_SERIALIZERS.register("lumium_conversion") { _: ResourceLocation -> LumiumConversionRecipeSerializer() }

    override fun bootstrap(bus: IEventBus) {
        RECIPE_SERIALIZERS.register(bus)
    }
}
