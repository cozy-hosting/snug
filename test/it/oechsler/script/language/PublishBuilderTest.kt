package it.oechsler.script.language

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import it.oechsler.ValidTestValues
import it.oechsler.script.data.Publish
import it.oechsler.script.data.PublishDomains
import it.oechsler.script.language.PublishBuilder.Companion.publish

@Suppress("unused")
class PublishBuilderTest : DescribeSpec({
    describe("Publish") {
        val validPorts = ValidTestValues.publishPortNumbers
        val validDomains = ValidTestValues.publishDomains

        fun buildPublish(ports: Set<Int>, domains: Set<PublishDomains>): Publish {
            return publish {
                ports.forEach { port(it) }
                domains.forEach {
                    domains(it.port) {
                        it.hostnames.forEach { hostname ->
                            domain(hostname)
                        }
                    }
                }
            }.toPublish()
        }

        it("returns a valid Publish object") {
            buildPublish(validPorts, validDomains) shouldBe Publish(validPorts, validDomains)
        }
    }
})