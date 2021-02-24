import it.oechsler.script.language.DeploymentBuilder.Companion.deploy
import it.oechsler.script.data.Image
import it.oechsler.script.data.Port

deploy {
    name = "nginx"
    tags("personal", "website")
    replicas = 3

    container {
        name="proxy"
        image = Image("nginx", "latest")
        ports {
            "Port1" bind "8080:80"
            "Port2" bind "80:8080"
        }
    }
}

