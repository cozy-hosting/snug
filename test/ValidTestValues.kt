import it.oechsler.script.data.*
import it.oechsler.script.extensions.gb
import it.oechsler.script.language.PathBuilder.Companion.root

class ValidTestValues {
    companion object {
        var name = "TestName"
        var replicas = 3
        var tags = setOf("TestTag1", "TestTag2")
        var image = Image("TestImage", "latest")

        var portNumber = 8080
        var portsWithName = setOf(Port("TestPort1", 8080, 8080), Port("TestPort2", 8081, 8081))
        var portsWithoutName = setOf(Port(null, 8080, 8080), Port(null, 8081, 8081))

        var containers = setOf(Container("Container1", image, portsWithoutName), Container("Container2", image, portsWithoutName))
        var hostnames = setOf("www.google.de", "google.de")
        var mixedMounts = setOf(Mount("TestMount1", MountType.VOLUME.value, "var/www", "775"), Mount("TestMount2", MountType.SECRET.value, "root/", "777"), Mount("TestMount2", MountType.CONFIG.value, "etc/", "777"))
        var volumeMounts = setOf(Mount("TestMount1", MountType.VOLUME.value, "var/www", "775"), Mount("TestMount2", MountType.VOLUME.value, "root/", "777"), Mount("TestMount2", MountType.VOLUME.value, "etc/", "777"))

        var loadBalancedDeployments = setOf(LoadBalancedDeployment("depoloyment1", portsWithoutName), LoadBalancedDeployment("depoloyment2", portsWithoutName))
        var publishPortNumbers = setOf(8080, 8081)
        var publishDomains =  setOf(PublishDomains(8080, setOf("google.de")), PublishDomains(8081, setOf("oechsler.it")))
        var publish = Publish(publishPortNumbers, publishDomains)

        var size = 1.gb()
        var volumeStorageClass = "do-block-storage"

        var validPath = Path("testPath")
        var validPathWithRoot = root / "testPath"
    }
}
