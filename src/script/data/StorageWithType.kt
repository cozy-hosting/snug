package it.oechsler.script.data

import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

data class StorageWithType(
    val name: String,
    val type: MountType
) {
    init {
        validate(this) {
            validate(StorageWithType::name)
                .isNotEmpty()
                .isNotBlank()
        }
    }
}