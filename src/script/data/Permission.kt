package it.oechsler.script.data

import org.valiktor.functions.isIn
import org.valiktor.validate

data class Permission(
    val user: Int,
    val group: Int,
    val everyone: Int
) {

    constructor(all: Int): this(
        (all / 100) % 10,
        (all / 10) % 10,
        all % 10
    )

    init {
        validate(this) {
            validate(Permission::user)
                .isIn(0..7)
            validate(Permission::group)
                .isIn(0..7)
            validate(Permission::everyone)
                .isIn(0..7)
        }
    }

    override fun toString(): String {
        return "$user$group$everyone"
    }
}