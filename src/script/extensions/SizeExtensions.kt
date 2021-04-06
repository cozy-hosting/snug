package it.oechsler.script.extensions

import it.oechsler.script.data.Size
import it.oechsler.script.data.SizeUnit

fun Int.gib(): Size = this.toDouble().gib()

fun Double.gib(): Size = Size(this, SizeUnit.GIB)

fun Int.gb(): Size = this.toDouble().gb()

fun Double.gb(): Size = Size(this, SizeUnit.GB)