import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import it.oechsler.script.data.PublishDomains
import it.oechsler.script.language.PublishDomainBuilder.Companion.domains
import org.valiktor.ConstraintViolationException

@Suppress("unused")
class PublishDomainBuilderTest : DescribeSpec({
    describe("PublishDomain") {
        val portNumber = ValidTestValues.portNumber
        val validHostnames = ValidTestValues.hostnames
        val invalidHostname = InvalidTestValues.hostnames

        fun buildDomains(hostnames: Set<String>): PublishDomains {
            return domains(portNumber) {
                hostnames.forEach { domain(it) }
            }.toPublishDomains()
        }

        it("returns a valid PublishDomain object") {
            buildDomains(validHostnames) shouldBe PublishDomains(portNumber, validHostnames)
        }
        it("throws ConstraintViolationException because the hostname aren't valid") {
            shouldThrow<ConstraintViolationException> {
                buildDomains(invalidHostname)
            }
        }
    }
})