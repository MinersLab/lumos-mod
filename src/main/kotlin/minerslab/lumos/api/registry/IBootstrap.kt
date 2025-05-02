package minerslab.lumos.api.registry

import net.neoforged.bus.api.IEventBus

interface IBootstrap {
    fun bootstrap(bus: IEventBus) {}
}
