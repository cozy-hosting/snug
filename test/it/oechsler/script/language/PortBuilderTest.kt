package it.oechsler.script.language

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import it.oechsler.InvalidTestValues
import it.oechsler.ValidTestValues
import it.oechsler.script.data.NameAndSourcePort
import it.oechsler.script.data.Port
import it.oechsler.script.language.PortBuilder.Companion.ports
import org.valiktor.ConstraintViolationException


@Suppress("unused")
class PortBuilderTest : DescribeSpec({
    describe("Port") {
        val validPortsWithName = ValidTestValues.portsWithName
        val validPortsWithoutName = ValidTestValues.portsWithoutName
        val invalidName = InvalidTestValues.name
        val invalidPortNumber = InvalidTestValues.portNumber

        fun buildPorts(ports: Set<Port>): Set<Port> {
            return ports {
                ports.forEach { port ->
                    // 'from' to syntax when name is provided
                    if (port.name != null) port.name!! from port.source to port.destination
                    else port.source to port.destination
                }
            }.toPorts()
        }

        describe("infix 'to' syntax without name") {
            it("returns a valid Ports Set") {
                buildPorts(validPortsWithoutName) shouldBe validPortsWithoutName
            }

            it("throws an ConstraintViolationException because the Port can't be 0") {
                shouldThrow<ConstraintViolationException> {
                    ports {
                        0 to 0
                    }.toPorts()
                }
            }
            it("throws an ConstraintViolationException because the Port can't be over 65535") {
                shouldThrow<ConstraintViolationException> {
                    ports {
                        invalidPortNumber to invalidPortNumber
                    }.toPorts()
                }
            }
        }

        describe("infix 'from' and 'to' syntax") {
            it("returns a valid Ports Set") {
                buildPorts(validPortsWithName) shouldBe validPortsWithName
            }
            it("throws an ConstraintViolationException because the Portname can't be empty") {
                shouldThrow<ConstraintViolationException> {
                    ports {
                        invalidName from validPortsWithName.first().source to validPortsWithName.first().destination
                    }.toPorts()
                }
            }
        }

        describe("NameAndSourcePort and infix 'to' syntax") {
            it("returns a valid Ports Set") {
                val ports = ports {
                    validPortsWithName.forEach { port ->
                        NameAndSourcePort(port.name!!, port.source) to port.destination
                    }
                }.toPorts()
                ports shouldBe validPortsWithName
            }
            it("throws an ConstraintViolationException because the Portname can't be empty") {
                shouldThrow<ConstraintViolationException> {
                    ports {
                        NameAndSourcePort(
                            invalidName,
                            validPortsWithName.first().source
                        ) to validPortsWithName.first().destination
                    }.toPorts()
                }
            }
        }
    }
})