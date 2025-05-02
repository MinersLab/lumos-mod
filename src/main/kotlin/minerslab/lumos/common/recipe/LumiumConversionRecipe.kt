package minerslab.lumos.common.recipe

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import minerslab.lumos.api.recipe.LumosRecipeType
import minerslab.lumos.registry.recipe.ModRecipeSerializers
import minerslab.lumos.registry.recipe.ModRecipeTypes
import net.minecraft.core.HolderLookup
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeInput
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.Level

class LumiumConversionRecipeInput(
    val itemStack: ItemStack,
) : RecipeInput {
    override fun size() = 1

    override fun getItem(index: Int): ItemStack {
        assert(index == 0)
        return itemStack
    }
}

class LumiumConversionRecipe(
    val inputItem: Ingredient,
    val result: ItemStack,
) : Recipe<LumiumConversionRecipeInput> {
    override fun matches(
        input: LumiumConversionRecipeInput,
        level: Level,
    ) = inputItem.test(input.itemStack)

    override fun assemble(
        input: LumiumConversionRecipeInput,
        registries: HolderLookup.Provider,
    ): ItemStack = result.copy()

    override fun isSpecial() = true

    override fun getType(): LumosRecipeType<LumiumConversionRecipe> = ModRecipeTypes.LUMIUM_CONVERSION.get()

    override fun getSerializer(): LumiumConversionRecipeSerializer = ModRecipeSerializers.LUMIUM_CONVERSION_RECIPE_SERIALIZER.get()

    override fun canCraftInDimensions(
        width: Int,
        height: Int,
    ) = true

    override fun getResultItem(registries: HolderLookup.Provider) = result
}

class LumiumConversionRecipeSerializer : RecipeSerializer<LumiumConversionRecipe> {
    override fun codec(): MapCodec<LumiumConversionRecipe> = CODEC

    override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, LumiumConversionRecipe> = STREAM_CODEC

    companion object {
        val CODEC: MapCodec<LumiumConversionRecipe> =
            RecordCodecBuilder.mapCodec { inst: RecordCodecBuilder.Instance<LumiumConversionRecipe> ->
                inst
                    .group(
                        Ingredient.CODEC.fieldOf("ingredient").forGetter(LumiumConversionRecipe::inputItem),
                        ItemStack.CODEC.fieldOf("result").forGetter(LumiumConversionRecipe::result),
                    ).apply(inst, ::LumiumConversionRecipe)
            }

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, LumiumConversionRecipe> =
            StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC,
                LumiumConversionRecipe::inputItem,
                ItemStack.STREAM_CODEC,
                LumiumConversionRecipe::result,
                ::LumiumConversionRecipe,
            )
    }
}
