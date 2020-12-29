job("Build and push Docker") {

    startOn {
        gitPush {
            branchFilter {
                +"refs/heads/main"
            }
        }
    }
    docker {
        build {
            file = "./Dockerfile"
        }

        push("cozy.registry.jetbrains.space/p/snug/containers/cli") {
            tag = "0.0.1"
        }
    }

}