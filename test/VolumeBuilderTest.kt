import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import it.oechsler.script.data.Size
import it.oechsler.script.data.Storage
import it.oechsler.script.language.VolumeBuilder.Companion.volume
import org.valiktor.ConstraintViolationException

@Suppress("unused")
class VolumeBuilderTest : DescribeSpec({
    describe("Volume") {
        val validName = ValidTestValues.name
        val validSize = ValidTestValues.size
        val volumeStorageClass = ValidTestValues.volumeStorageClass
        val invalidName = InvalidTestValues.name
        fun buildVolume(name: String, size: Size): Storage {
            return volume(name) {
                this.size = size
            }.toStorage()
        }

        it("returns a valid Size object") {
            buildVolume(validName, validSize) shouldBe Storage(validName, validSize.toString(), volumeStorageClass)
        }
        it("throws a ConstraintViolationException because the name is invalid") {
            shouldThrow<ConstraintViolationException> {
                buildVolume(invalidName, validSize)
            }
        }
    }
})