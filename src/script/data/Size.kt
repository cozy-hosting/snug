package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isBetween
import org.valiktor.validate
import kotlin.math.roundToInt

@Serializable
data class Size(
    val quantity: Double
) {
    init {
        validate(this) {
            validate(Size::quantity)
                .isBetween(1.0, 1000.0)
        }
    }

    override fun toString(): String {
        val quantityString = when (quantity) {
            quantity.roundToInt().toDouble() -> quantity.roundToInt()
            else -> quantity
        }
        return "${quantityString}Gi"
    }
}
