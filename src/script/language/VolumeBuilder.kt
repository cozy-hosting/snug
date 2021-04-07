package it.oechsler.script.language

class VolumeBuilder private constructor(name: String) : StorageBuilder(name, "do-block-storage") {

    companion object {

        fun volume(name: String, init: VolumeBuilder.() -> Unit): VolumeBuilder {
            return VolumeBuilder(name).apply(init)
        }

    }

}