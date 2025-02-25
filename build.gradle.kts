import java.util.Properties

plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.1.0"
    `maven-publish`
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    id("signing")
    java
    id("org.jetbrains.dokka") version "1.9.20"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin.jvmToolchain(17)


// Publishing configuration

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.dokkaHtml) // Generates Javadoc using Dokka (for Kotlin projects)
}


val publishGroupId = "io.github.karya-inc"
val publishArtifactVersion = "0.0.1"
val publishArtifactId = "serialization-ktx"

group = publishGroupId
version = publishArtifactVersion

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = publishGroupId
            artifactId = publishArtifactId
            version = publishArtifactVersion

            afterEvaluate { from(components["kotlin"]) }
            artifact(tasks.named("sourcesJar"))
            artifact(tasks.named("javadocJar"))

            pom {
                name.set(publishArtifactId)
                description.set("A Kotlin serialization utility library")
                url.set("hhttps://github.com/karya-inc/serialization-ktx.git")

                licenses {
                    license {
                        name.set("GNU license")
                        url.set("https://opensource.org/license/gpl-3-0")
                    }
                }

                developers {
                    developer {
                        id.set("divyansh@karya.in")
                        name.set("Divyansh Kushwaha")
                        email.set("divyansh@karya.in")
                    }
                }

                scm {
                    connection.set("scm:git:ssh://git@github.com/karya-inc/serialization-ktx.git")
                    developerConnection.set("scm:git:ssh://git@github.com/karya-inc/serialization-ktx.git")
                    url.set("https://github.com/karya-inc/serialization-ktx.git")
                }
            }
        }
    }
}

val ossrhToken: String by extra("")
val ossrhTokenPassword: String by extra("")
val sonatypeStagingProfileId: String by extra("")
val signingKeyId: String by extra("")
val signingPassword: String by extra("")
val signingKey: String by extra("")

val secretPropsFile = rootProject.file("local.properties")
val properties = Properties()

if (secretPropsFile.exists()) {
    secretPropsFile.inputStream().use { properties.load(it) }
    properties.forEach { (name, value) ->
        extra[name.toString()] = value
    }
}

nexusPublishing {
    repositories {
        sonatype {
            stagingProfileId = sonatypeStagingProfileId
            username = ossrhToken
            password = ossrhTokenPassword

            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

signing {
    useInMemoryPgpKeys(
        signingKeyId,
        signingKey,
        signingPassword
    )
    sign(publishing.publications)
}
