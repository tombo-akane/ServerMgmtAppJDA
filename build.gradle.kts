import org.gradle.jvm.tasks.Jar

plugins {
    idea
    application
    id("java")
}

group = "com.github.tombo_akane"
version = "0.0.1-alpha"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.dv8tion:JDA:5.0.1")
    implementation("commons-cli:commons-cli:1.5.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.github.tombo_akane.Main")
}

val fatJar = task("fatJar", type = Jar::class) {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Implementation-Title"] = "ServerMgmtAppJDA"
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = "com.github.tombo_akane.Main"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
    destinationDirectory.set(layout.buildDirectory.dir("dist"))
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}