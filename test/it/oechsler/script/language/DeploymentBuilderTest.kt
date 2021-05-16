package it.oechsler.script.language

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import it.oechsler.InvalidTestValues
import it.oechsler.ValidTestValues
import it.oechsler.script.data.Deployment
import it.oechsler.script.data.Publish
import it.oechsler.script.language.DeploymentBuilder.Companion.deployment
import org.valiktor.ConstraintViolationException


@Suppress("unused")
class DeploymentBuilderTest : DescribeSpec({
    describe("Deployment") {
        val validName = ValidTestValues.name
        val invalidName = InvalidTestValues.name
        val tags = ValidTestValues.tags
        val replicas = ValidTestValues.replicas

        fun buildDeployment(name: String, tags: Set<String>, replicas: Int): Deployment {
            return deployment(name) {
                this.replicas = replicas
                tags(*tags.toTypedArray())
                publish { }
            }.toDeployment()
        }

        it("returns a valid Deployment object") {
            buildDeployment(validName, tags, replicas) shouldBe Deployment(
                validName,
                tags,
                replicas,
                setOf(),
                setOf(),
                Publish(setOf(), setOf())
            )
        }
        it("throws ConstraintViolationException because the name isn't valid") {
            shouldThrow<ConstraintViolationException> {
                buildDeployment(invalidName, tags, replicas)
            }
        }
    }
})