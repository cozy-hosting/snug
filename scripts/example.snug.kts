import it.oechsler.script.language.DeploymentBuilder.Companion.deploy

deploy {
    name = "nginx"
    withTags("personal", "website")
    replicas = 3
}

