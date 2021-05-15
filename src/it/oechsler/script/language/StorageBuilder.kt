package it.oechsler.script.language

import it.oechsler.script.data.Size
import it.oechsler.script.data.Storage
import it.oechsler.script.data.StorageClass
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Suppress("unused")
open class StorageBuilder constructor(val name: String, private val storageClass: StorageClass): Script {

    lateinit var size: Size

    fun toStorage(): Storage {
        return Storage(name, size.toString(), storageClass.value)
    }

    override fun apply() {
        val serializer = Json { prettyPrint = true }
        println(serializer.encodeToString(toStorage()))
    }

    override fun rollback() {
        TODO("Not yet implemented")
    }

}