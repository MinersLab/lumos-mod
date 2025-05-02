package minerslab.lumos.api.gui

import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture
import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture
import minerslab.lumos.Lumos
import java.util.function.Supplier

enum class GuiTextures(
    texture: IGuiTexture,
) : Supplier<IGuiTexture> by (Supplier { texture }) {
    ARROW_RIGHT(ResourceTexture(Lumos.id("textures/gui/arrow_right.png"))),
}
