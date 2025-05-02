package minerslab.lumos

import minerslab.lumos.data.ModDatagen
import minerslab.lumos.registry.fluid.ModFluids
import minerslab.lumos.registry.item.ModItems
import minerslab.lumos.registry.recipe.ModRecipeSerializers
import minerslab.lumos.registry.recipe.ModRecipeTypes
import net.neoforged.bus.api.IEventBus

abstract class CommonProxy {

    open fun initialize(bus: IEventBus) {
        ModItems.bootstrap(bus)
        ModFluids.bootstrap(bus)

        ModRecipeTypes.bootstrap(bus)
        ModRecipeSerializers.bootstrap(bus)

        ModDatagen.run()
    }

}