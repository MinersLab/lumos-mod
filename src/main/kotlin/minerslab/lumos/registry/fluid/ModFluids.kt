package minerslab.lumos.registry.fluid

import com.tterrag.registrate.util.entry.FluidEntry
import minerslab.lumos.LumosMod.REGISTRATE
import minerslab.lumos.api.registry.IBootstrap
import minerslab.lumos.common.block.LumiumLiquidBlock
import net.neoforged.neoforge.fluids.BaseFlowingFluid

object ModFluids : IBootstrap {

    @JvmField
    val LUMIUM: FluidEntry<BaseFlowingFluid.Flowing> = REGISTRATE.lumosFluid("lumium")
        .properties { it.canSwim(true).canDrown(true).canHydrate(true).lightLevel(14) }
        .source(BaseFlowingFluid::Source)
        .block(::LumiumLiquidBlock)
        .defaultLang()
        .build()
        .bucket()
        .defaultModel()
        .defaultLang()
        .build()
        .register()

}