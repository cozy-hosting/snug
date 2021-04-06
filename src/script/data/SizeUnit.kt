package it.oechsler.script.data

enum class SizeUnit(val value: String) {
    // Xi units are based on powers of 2
    GIB("Gi"),

    // X units are based in powers of 10
    GB("G")
}