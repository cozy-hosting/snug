import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import it.oechsler.script.data.Path
import it.oechsler.script.language.PathBuilder.Companion.path

@Suppress("unused")
class PathBuilderTest : DescribeSpec({
    describe("Path") {
        val validPath = ValidTestValues.validPath
        val validPathWithRoot = ValidTestValues.validPathWithRoot

        it("returns a valid Path") {
            path(validPath).toString() shouldBe Path(validPath.toString()).toString()
        }
        it("returns a valid Path with 'root' syntax") {
            path(validPathWithRoot).toString() shouldBe Path(validPathWithRoot.toString()).toString()
        }
    }
})