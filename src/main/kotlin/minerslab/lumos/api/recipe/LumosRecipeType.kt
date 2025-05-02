package minerslab.lumos.api.recipe

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeType

class LumosRecipeType<T : Recipe<*>>(
    val registryName: ResourceLocation,
) : RecipeType<T> {
    override fun toString() = registryName.toString()
}
