import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI
import java.util.Properties
import java.io.FileInputStream

plugins {
    kotlin("jvm") version "1.6.10"
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

group = "com.devingryu"
version = "1.0.3"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
}
tasks.test {
    useJUnitPlatform()
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
publishing {
    publications {
        val mavenJava by creating(MavenPublication::class) {
            artifactId = "kotlin-bitarray"
            from(components["java"])
            pom {
                name.set("kotlin-bitarray")
                description.set("A Minimal BitArray Implementation for Kotlin")
                packaging = "jar"
                url.set("https://github.com/devingryu/KotlinBitArray")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("devingryu")
                        name.set("HyungSeok Ryu")
                        email.set("devingryu@korea.ac.kr")
                    }
                }
                scm {
                    connection.set("https://github.com/devingryu/KotlinBitArray.git")
                    developerConnection.set("https://github.com/devingryu/KotlinBitArray.git")
                    url.set("https://github.com/devingryu/KotlinBitArray")
                }
            }
        }
    }
}
java {
    withJavadocJar()
    withSourcesJar()
}
val localProp = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}.onEach { (k,v) ->
    ext[k.toString()] = v
}

signing {
    sign(publishing.publications)
}




nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(URI("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(URI("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}



