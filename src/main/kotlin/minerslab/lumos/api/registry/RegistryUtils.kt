package minerslab.lumos.api.registry

import com.tterrag.registrate.builders.ItemBuilder
import net.minecraft.core.component.DataComponentType
import net.minecraft.world.item.Item

fun <T : Item, P, V : Any> ItemBuilder<T, P>.component(
    component: DataComponentType<V>,
    value: ItemBuilder<T, P>.() -> V,
): ItemBuilder<T, P> = properties { it.component(component, value(this@component)) }
