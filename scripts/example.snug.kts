fun PortBuilder.minecraft(pod: Int = 25565) =
    port(25565, pod, "minecraft")

resources {
    deployment("nginx") {
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
        publish {
            port(8081)
            port(8082)
            port(10)
            domains(8080) {
                domain("www.oechsler.it")
                domain("oechsler.it")
            }
            domains(80) {
                domain("www.obert.dev")
                domain("obert.dev")
            }
        }
    }
    loadBalancer("public-edpoint") {
        deployment("nginx") {
            ports {
                8080 to 80
                8081 to 443
            }
        }
    }
}


