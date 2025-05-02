package minerslab.lumos.integration.emi

import dev.emi.emi.api.EmiEntrypoint
import dev.emi.emi.api.EmiPlugin
import dev.emi.emi.api.EmiRegistry
import minerslab.lumos.integration.emi.category.LumiumConversionRecipeEmiCategory

@EmiEntrypoint
class LumosEmiPlugin : EmiPlugin {

    override fun register(registry: EmiRegistry) {
        LumiumConversionRecipeEmiCategory().register(registry)
    }

}