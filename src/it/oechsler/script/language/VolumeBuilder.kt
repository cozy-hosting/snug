package it.oechsler.script.language

import it.oechsler.script.data.StorageClass

class VolumeBuilder private constructor(name: String) : StorageBuilder(name, StorageClass.DO_BLOCK_STORAGE) {

    companion object {

        fun volume(name: String, init: VolumeBuilder.() -> Unit): VolumeBuilder {
            return VolumeBuilder(name).apply(init)
        }

    }

}