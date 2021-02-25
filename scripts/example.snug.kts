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
            5000 to 8080
            80 to 8081
        }
    }
}

