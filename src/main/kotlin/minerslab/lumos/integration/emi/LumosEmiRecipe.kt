package minerslab.lumos.integration.emi

import com.lowdragmc.lowdraglib.emi.ModularEmiRecipe
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup
import java.util.function.Supplier

abstract class LumosEmiRecipe(
    supplier: Supplier<WidgetGroup>,
) : ModularEmiRecipe<WidgetGroup>(supplier)
