package minerslab.lumos.api.registry

import com.tterrag.registrate.Registrate
import com.tterrag.registrate.builders.FluidBuilder
import com.tterrag.registrate.builders.ItemBuilder
import com.tterrag.registrate.util.nullness.NonNullFunction
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.neoforged.neoforge.fluids.BaseFlowingFluid

open class LumosRegistrate(modid: String) : Registrate(modid) {

    fun <T : Item> item(name: String, factory: (Item.Properties) -> T): ItemBuilder<T, Registrate> {
        return item(name, NonNullFunction { factory(it) })
    }

    fun lumosFluid(name: String): FluidBuilder<BaseFlowingFluid.Flowing, Registrate> =
        fluid(self(), name, ResourceLocation.fromNamespaceAndPath(modid, "block/" + name + "_still"), ResourceLocation.fromNamespaceAndPath(modid, "block/" + name + "_flow"))

}