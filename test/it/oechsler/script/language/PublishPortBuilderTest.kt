package it.oechsler.script.language

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import it.oechsler.InvalidTestValues
import it.oechsler.ValidTestValues
import it.oechsler.script.data.PublishPort
import it.oechsler.script.language.PublishPortBuilder.Companion.port
import org.valiktor.ConstraintViolationException

@Suppress("unused")
class PublishPortBuilderTest : DescribeSpec({
    describe("PublishPort") {
        val validPortNumber = ValidTestValues.portNumber
        val invalidPortNumber = InvalidTestValues.portNumber

        it("returns a valid PublishPort object") {
            val port = port(validPortNumber).toPublishPort()
            port shouldBe PublishPort(validPortNumber)
        }

        it("throws ConstraintViolationException because the port is not valid") {
            shouldThrow<ConstraintViolationException> {
                port(invalidPortNumber).toPublishPort()
            }
        }
    }
})