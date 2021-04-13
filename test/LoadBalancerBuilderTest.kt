import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import it.oechsler.script.data.LoadBalancedDeployment
import it.oechsler.script.data.LoadBalancer
import it.oechsler.script.language.LoadBalancerBuilder.Companion.loadBalancer
import org.valiktor.ConstraintViolationException


@Suppress("unused")
class LoadBalancerBuilderTest : DescribeSpec({
    describe("LoadBalancerBuilder") {
        val validName = ValidTestValues.name
        val invalidName = InvalidTestValues.name
        val deployments = ValidTestValues.loadBalancedDeployments

        fun buildLoadBalancer(name: String, deployments: Set<LoadBalancedDeployment>): LoadBalancer {
            return loadBalancer(name) {
                deployments.forEach {
                    deployment(it.name) {
                        ports {
                            it.ports.forEach { port ->
                                port.source to port.destination
                            }
                        }
                    }
                }
            }.toLoadBalancer()
        }

        it("returns a valid LoadBalancer object") {
            buildLoadBalancer(validName, deployments)
        }
        it("throws ConstraintViolationException because the name isn't valid") {
            shouldThrow<ConstraintViolationException> {
                buildLoadBalancer(invalidName, deployments)
            }
        }
    }
})