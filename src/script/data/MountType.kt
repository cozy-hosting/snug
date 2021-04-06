package it.oechsler.script.data

enum class MountType(val value: String) {
    VOLUME("persistentVolumeClaim"),
    // TODO: Build loader resources for these types
    CONFIG("configMap"),
    SECRET("secret"),
}