import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import it.oechsler.script.data.Container
import it.oechsler.script.data.Port
import it.oechsler.script.language.ContainerBuilder.Companion.container
import org.valiktor.ConstraintViolationException


@Suppress("unused")
class ContainerBuilderTest : DescribeSpec({
    describe("Container") {
        val validName = ValidTestValues.name
        val invalidName = InvalidTestValues.name
        val ports = ValidTestValues.portsWithoutName
        val image = ValidTestValues.image

        fun buildContainer(name: String, ports: Set<Port>): Container {
            return container(name) {
                ports {
                    ports.forEach { port ->
                        port.source to port.destination
                    }
                }
                image(image.name, image.tag)
            }.toContainer()
        }

        it("returns a valid Container object") {
            buildContainer(validName, ports) shouldBe Container(validName, image, ports)
        }
        it("throws ConstraintViolationException because the name isn't valid") {
            shouldThrow<ConstraintViolationException> {
                buildContainer(invalidName, ports)
            }
        }
    }
})