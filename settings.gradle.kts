rootProject.name = "the-garden-foretells"
include("eureka-server")
include("api-gateway")
include("garden-manager")
include("garden-area-service")
include("garden-area-service:authentication-service")
findProject(":garden-area-service:authentication-service")?.name = "authentication-service"
include("authentication-service")
