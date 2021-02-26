package it.oechsler.script.language

import it.oechsler.script.data.Image

class ImageBuilder private constructor(private val name: String, private val tag: String) {

    companion object {

        fun image(name: String, tag: String) : ImageBuilder {
            return ImageBuilder(name, tag)
        }

    }

    fun toImage(): Image {
        return Image(name, tag)
    }

}