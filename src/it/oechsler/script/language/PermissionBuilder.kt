package it.oechsler.script.language

import it.oechsler.script.data.Permission

@Suppress("unused")
class PermissionBuilder private constructor(private val permission: Permission) {

    companion object {

        val everyone = permission(777)
        val default = permission(755)

        fun permission(all: Int): PermissionBuilder {
            return permission(Permission(all))
        }

        private fun permission(permission: Permission): PermissionBuilder {
            return PermissionBuilder(permission)
        }

    }

    override fun toString(): String {
        return permission.toString()
    }

}