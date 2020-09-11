plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("kapt") version "1.4.10"
    // this plugin will generate the OpenAPI specs from our app routes
    // to know more about OpenAPI see https://swagger.io/specification/
    id("io.jooby.openAPI") version "2.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"
application{
    mainClassName = "org.example.AppKt"
}
repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    // this is our chosen web framework
    // it's one of the most performant and it's elegant
    val joobyVersion = "2.9.0"
    implementation(group = "io.jooby", name = "jooby", version = joobyVersion) {
        // we'll specify this dependency ourselves
        exclude("com.typesafe", "config")
    }
    // this is to support jooby MVC API
    kapt(group = "io.jooby", name = "jooby-apt", version = joobyVersion)
    // and this is to show our API documentation
    runtimeOnly(group = "io.jooby", name = "jooby-swagger-ui", version = joobyVersion)
    // and this is for better documentation of our API
    implementation(group = "io.swagger.core.v3", name = "swagger-annotations", version = "2.1.4")
    // this is what jooby uses to load application config
    implementation(group = "com.typesafe", name = "config", version = "1.4.0")
}
val openAPI by tasks
val jar by tasks
jar.dependsOn(openAPI)

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class) {
    // this is needed for jooby to work, seems they reflect on methods parameters too
    kotlinOptions.javaParameters = true
    kotlinOptions.jvmTarget = "11"
}