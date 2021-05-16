package it.oechsler

import it.oechsler.script.data.*
import it.oechsler.script.extensions.gb
import it.oechsler.script.language.PathBuilder.Companion.root

class ValidTestValues {
    companion object {
        const val name = "TestName"
        const val replicas = 3
        val tags = setOf("TestTag1", "TestTag2")
        val image = Image("TestImage", "latest")

        const val portNumber = 8080
        val portsWithName = setOf(Port("TestPort1", 8080, 8080), Port("TestPort2", 8081, 8081))
        val portsWithoutName = setOf(Port(null, 8080, 8080), Port(null, 8081, 8081))

        val hostnames = setOf("www.google.de", "google.de")
        val mixedMounts = setOf(Mount("TestMount1", MountType.VOLUME.value, "var/www", "775"), Mount("TestMount2", MountType.SECRET.value, "root/", "777"), Mount("TestMount2", MountType.CONFIG.value, "etc/", "777"))
        val volumeMounts = setOf(Mount("TestMount1", MountType.VOLUME.value, "var/www", "775"), Mount("TestMount2", MountType.VOLUME.value, "root/", "777"), Mount("TestMount2", MountType.VOLUME.value, "etc/", "777"))

        val loadBalancedDeployments = setOf(LoadBalancedDeployment("depoloyment1", portsWithoutName), LoadBalancedDeployment("depoloyment2", portsWithoutName))
        val publishPortNumbers = setOf(8080, 8081)
        val publishDomains =  setOf(PublishDomains(8080, setOf("google.de")), PublishDomains(8081, setOf("oechsler.it")))

        val size = 1.gb()
        const val volumeStorageClass = "do-block-storage"

        val validPath = Path("testPath")
        val validPathWithRoot = root / "testPath"
    }
}
