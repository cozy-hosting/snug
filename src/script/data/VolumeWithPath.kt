package it.oechsler.script.data

import it.oechsler.script.language.PathBuilder
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

data class VolumeWithPath(
    val name: String,
    val path: PathBuilder
) {
    init {
        validate(this) {
            validate(VolumeWithPath::name)
                .isNotEmpty()
                .isNotBlank()
        }
    }
}
