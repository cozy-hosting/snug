package it.oechsler.script.language

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import it.oechsler.InvalidTestValues
import it.oechsler.ValidTestValues
import it.oechsler.script.data.LoadBalancedDeployment
import it.oechsler.script.data.Port
import it.oechsler.script.language.LoadBalancedDeploymentBuilder.Companion.deployment
import org.valiktor.ConstraintViolationException


@Suppress("unused")
class LoadBalancedDeploymentBuilderTest : DescribeSpec({
    describe("LoadBalancedDeployment") {
        val validName = ValidTestValues.name
        val invalidName = InvalidTestValues.name
        val ports = ValidTestValues.portsWithoutName

        fun buildLoadBalancedDeployment(name: String, ports: Set<Port>): LoadBalancedDeployment {
            return deployment(name) {
                ports {
                    ports.forEach { port ->
                        port.source to port.destination
                    }
                }
            }.toLoadBalancedDeployment()
        }


        it("returns a valid LoadBalancer object") {
            buildLoadBalancedDeployment(validName, ports) shouldBe LoadBalancedDeployment(validName, ports)
        }
        it("throws ConstraintViolationException because the name isn't valid") {
            shouldThrow<ConstraintViolationException> {
                buildLoadBalancedDeployment(invalidName, ports)
            }
        }
    }
})