package minerslab.lumos.data

import com.tterrag.registrate.providers.ProviderType
import minerslab.lumos.LumosMod.REGISTRATE

object ModDatagen : Runnable {

    override fun run() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, ModLanguageDatagen)
    }

}
