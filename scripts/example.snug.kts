import it.oechsler.script.language.DeploymentBuilder.Companion.deploy
import it.oechsler.script.language.PortBuilder

fun PortBuilder.minecraft(pod: Int = 25565) =
    port(25565, pod, "minecraft")

deploy("nginx") {
    tags("personal", "website")
    replicas = 3

    container("proxy") {
        image("nginx", "latest")

        ports {
            minecraft()

            port(21, 5642, "ftp")
            port(443, 4043)
            port(45, "something")
            port(22)

            // Shorthand syntax
            "http" with 80 to 8080
            9000 to 8081
        }
    }
}

