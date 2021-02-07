package it.oechsler.services.data

class ScriptException(message: String? = null, override val cause: Throwable? = null) :
    RuntimeException(message, cause)