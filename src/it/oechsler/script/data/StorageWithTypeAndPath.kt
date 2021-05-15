package it.oechsler.script.data

import it.oechsler.script.language.PathBuilder

data class StorageWithTypeAndPath(
    val storageWithType: StorageWithType,
    val path: PathBuilder
)
