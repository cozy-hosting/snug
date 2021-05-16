package it.oechsler.script.language

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import it.oechsler.script.data.Permission
import it.oechsler.script.language.PermissionBuilder.Companion.default
import it.oechsler.script.language.PermissionBuilder.Companion.everyone
import it.oechsler.script.language.PermissionBuilder.Companion.permission


@Suppress("unused")
class PermissionBuilderTest : DescribeSpec({
    describe("Permission") {

        fun buildPermission(permission: Int): String {
            return permission(permission).toString()
        }

        it("returns a valid permission string for 777") {
            buildPermission(777) shouldBe Permission(777).toString()
        }

        it("returns a valid permission string with 'everyone' syntax") {
            everyone.toString() shouldBe Permission(777).toString()
        }

        it("returns a valid permission string with 'default' syntax") {
            default.toString() shouldBe Permission(755).toString()
        }

    }
})