package minerslab.lumos.integration.emi

import com.lowdragmc.lowdraglib.gui.modular.ModularUIContainer
import com.lowdragmc.lowdraglib.gui.widget.SlotWidget
import com.lowdragmc.lowdraglib.jei.IngredientIO
import dev.emi.emi.api.recipe.EmiRecipe
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler
import net.minecraft.world.inventory.Slot

class LumosEmiRecipeHandler : StandardRecipeHandler<ModularUIContainer> {
    override fun supportsRecipe(recipe: EmiRecipe) = recipe is LumosEmiRecipe

    override fun getInputSources(handler: ModularUIContainer): List<Slot> =
        handler.modularUI.slotMap.values
            .stream()
            .filter { e: SlotWidget -> e.ingredientIO == IngredientIO.INPUT || e.isPlayerContainer || e.isPlayerHotBar }
            .map { obj: SlotWidget -> obj.handler!! }
            .toList()

    override fun getCraftingSlots(handler: ModularUIContainer): MutableList<Slot> =
        handler.modularUI.slotMap.values
            .stream()
            .filter { e: SlotWidget -> e.ingredientIO == IngredientIO.INPUT }
            .map { obj: SlotWidget -> obj.handler!! }
            .toList()
}
