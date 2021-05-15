package it.oechsler.script.language

interface Script {

    fun apply()

    fun rollback()

}