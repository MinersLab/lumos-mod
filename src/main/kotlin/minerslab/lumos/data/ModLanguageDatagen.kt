package minerslab.lumos.data

import com.tterrag.registrate.providers.RegistrateLangProvider
import com.tterrag.registrate.util.nullness.NonNullConsumer
import minerslab.lumos.Lumos.ID

object ModLanguageDatagen : NonNullConsumer<RegistrateLangProvider> {
    override fun accept(provider: RegistrateLangProvider) =
        with(provider) {
            "title.$ID.xei.category.lumium_conversion"("Lumium Conversion")
        }
}

context(RegistrateLangProvider)
operator fun String.invoke(value: String) {
    add(this, value)
}
