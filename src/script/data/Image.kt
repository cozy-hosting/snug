package it.oechsler.script.data

import kotlinx.serialization.Serializable
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

@Serializable
data class Image(
    val name: String,
    val tag: String
){
    init {
        validate(this) {
            validate(Image::name).isNotEmpty().isNotBlank()
            validate(Image::tag).isNotEmpty().isNotBlank()
        }
    }
}
