package it.oechsler.script.language

interface ScriptRoot {

    fun apply()

    fun rollback()

}