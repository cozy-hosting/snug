import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import it.oechsler.script.data.Mount
import it.oechsler.script.data.MountType
import it.oechsler.script.data.Path
import it.oechsler.script.language.MountBuilder.Companion.mounts
import it.oechsler.script.language.PathBuilder.Companion.path
import it.oechsler.script.language.PermissionBuilder.Companion.permission
import java.lang.Integer.parseInt


@Suppress("unused")
class MountBuilderTest : DescribeSpec({
    describe("Mounts") {
        val validMixedMounts = ValidTestValues.mixedMounts
        val validVolumeMounts = ValidTestValues.volumeMounts

        fun buildMounts(mounts: Set<Mount>, withFromSyntax: Boolean): Set<Mount> {
            return mounts {
                mounts.forEach {
                    if (withFromSyntax) {
                        when (it.type) {
                            MountType.VOLUME.value -> it.name from volume to path(Path(it.path)) with permission(parseInt(it.permission))
                            MountType.CONFIG.value -> it.name from config to path(Path(it.path)) with permission(parseInt(it.permission))
                            MountType.SECRET.value -> it.name from secret to path(Path(it.path)) with permission(parseInt(it.permission))
                        }
                    } else {
                        it.name to path(Path(it.path)) with permission(parseInt(it.permission))
                    }
                }
            }.toMounts()
        }

        it("returns a valid Set of mixed mountTypes Mounts with 'from' syntax") {
            buildMounts(validMixedMounts, true) shouldBe validMixedMounts
        }

        it("returns a valid Set of volume Mounts without 'from' syntax") {
            buildMounts(validVolumeMounts, false) shouldBe validVolumeMounts
        }
    }
})