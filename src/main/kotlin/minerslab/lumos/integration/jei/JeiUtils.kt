package minerslab.lumos.integration.jei

import net.minecraft.client.Minecraft
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeInput
import net.minecraft.world.item.crafting.RecipeType


fun <I : RecipeInput, T : Recipe<I>> getRecipeHoldersFromType(recipeType: RecipeType<T>) =
    Minecraft.getInstance().connection!!.recipeManager.getAllRecipesFor(recipeType).toList()