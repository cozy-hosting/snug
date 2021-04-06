package it.oechsler.script.extensions

import it.oechsler.script.data.Size

fun Int.gb(): Size = this.toDouble().gb()

fun Double.gb(): Size = Size(this)