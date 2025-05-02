package minerslab.lumos

import minerslab.lumos.api.registry.LumosRegistrate
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.KotlinModLoadingContext
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist

object Lumos {

    const val ID = "lumos"
    fun id(path: String) = ResourceLocation.tryBuild(ID, path)!!

}

@Mod(Lumos.ID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object LumosMod {

    private val context = KotlinModLoadingContext.get()

    @JvmField
    val REGISTRATE = LumosRegistrate(Lumos.ID).apply { registerEventListeners(context.getKEventBus()) }

    val logger: Logger = LogManager.getLogger(Lumos.ID)
    val proxy: CommonProxy = runForDist(
        ::ClientProxy, ::ServerProxy
    )

    init {
        proxy.initialize(context.getKEventBus())
    }


    private fun onClientSetup(event: FMLClientSetupEvent) {
        logger.info("[$event] Initializing client...")
    }


    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        logger.info("[$event] Server starting...")
    }

    @SubscribeEvent
    fun onCommonSetup(event: FMLCommonSetupEvent) {
        logger.info("[$event] Hello! This is working!")
    }

}