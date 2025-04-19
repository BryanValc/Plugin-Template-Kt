import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "2.1.20"
    id("com.gradleup.shadow") version "9.0.0-beta8"
    id("io.github.revxrsal.zapper") version "1.0.3"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.14"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "dev.smartshub"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }

    maven {
        name = "resparkReleases"
        url = uri("https://maven.respark.dev/releases")
    }

    maven ("https://repo.codemc.org/repository/maven-public/") {
        name = "codemc"
    }

    maven("https://repo.extendedclip.com/releases/") {
        name = "placeholderapi"
    }

    maven {
        url = uri("https://jitpack.io")
        name = "jitpack"
    }
}
// PURE MINECRAFT
val bStatsVersion: String by project
val papiVersion: String by project
val mcCoroutineVersion: String by project
val rTagVersion: String by project
val triumphVersion: String by project
val knbtVersion: String by project
val kamlVersion: String by project

// LICENSING
val licenseGateVersion: String by project

// SERIALIZATION AND CONFIGS
val serializationVersion: String by project

// CACHING AND DATABASES
val caffeineVersion: String by project
val koinVersion: String by project
val exposedVersion: String by project
val sqliteVersion: String by project
val mariadbVersion: String by project
val hikariVersion: String by project
val postgresVersion: String by project
val h2Version: String by project


dependencies {
    paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")
    zap("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // bStats
    zap("org.bstats:bstats-bukkit:$bStatsVersion")
    // Lamp
    zap("io.github.revxrsal:lamp.common:4.0.0-beta.25")
    zap("io.github.revxrsal:lamp.bukkit:4.0.0-beta.25")
    // PAPI
    compileOnly("me.clip:placeholderapi:$papiVersion")
    // coroutines
    zap("com.github.shynixn.mccoroutine:mccoroutine-bukkit-api:$mcCoroutineVersion")
    zap("com.github.shynixn.mccoroutine:mccoroutine-bukkit-core:$mcCoroutineVersion")
    // rTag
    zap("com.saicone.rtag:rtag:$rTagVersion")
    // Other modules
    zap("com.saicone.rtag:rtag-block:$rTagVersion")
    zap("com.saicone.rtag:rtag-entity:$rTagVersion")
    zap("com.saicone.rtag:rtag-item:$rTagVersion")

    // Guis
    zap("dev.triumphteam:triumph-gui:$triumphVersion")

    // License server
    // Implementation due to repo is not hosted at MavenCentral (zapper requieres it!)
    implementation("dev.respark.licensegate:license-gate:$licenseGateVersion")

    // serialization //maybe not needed? didn't hear any mention of it in hoplite and neither is hoplite using the annotations
    zap("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
    zap("org.jetbrains.kotlinx:kotlinx-serialization-hocon:$serializationVersion")   // .conf serializationx
    zap("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")    // .json serializationx
    zap("net.benwoodworth.knbt:knbt:$knbtVersion")                                   // .nbt  serializationx
    zap("com.charleskorn.kaml:kaml:$kamlVersion")                                    // .yml  serializationx

    // DI
    zap("io.insert-koin:koin-core:$koinVersion")
    //caching
    zap("com.github.ben-manes.caffeine:caffeine:$caffeineVersion")

    //ORM
    zap("org.jetbrains.exposed:exposed-core:$exposedVersion")
    zap("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
    zap("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    zap("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    zap("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")
    zap("org.jetbrains.exposed:exposed-json:$exposedVersion")
    zap("org.jetbrains.exposed:exposed-money:$exposedVersion")
    zap("org.jetbrains.exposed:exposed-spring-boot-starter:$exposedVersion")
    zap("org.jetbrains.exposed:exposed-migration:$exposedVersion")
    // databases
    zap("org.xerial:sqlite-jdbc:$sqliteVersion")
    zap("org.mariadb.jdbc:mariadb-java-client:$mariadbVersion")
    zap("org.postgresql:postgresql:$postgresVersion")
    zap("com.zaxxer:HikariCP:$hikariVersion")
    zap("com.h2database:h2:$h2Version")
}

// Where dependencies .jar will be stored
zapper {
    libsFolder = "libs"
    repositories { includeProjectRepositories() }
}


tasks {
    runServer {
        downloadPlugins {
            hangar("PlaceholderAPI", "2.11.6")
            hangar("ViaVersion", "5.3.1")
            hangar("ViaVersion", "5.3.1")
            hangar("ViaBackwards", "5.3.1")
            hangar("ViaRewind", "4.0.7")

        }
        minecraftVersion("1.21.4")
    }
}

tasks.withType(xyz.jpenilla.runtask.task.AbstractRun::class) {
    javaLauncher = javaToolchains.launcherFor {
        vendor = JvmVendorSpec.JETBRAINS
        languageVersion = JavaLanguageVersion.of(21)
    }
    jvmArgs(
        "-D${project.name.lowercase()}.debug=true",
        "-XX:+AllowEnhancedClassRedefinition" // TODO: create a separate artifact without shadowJar for this to be effective every time
        )
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks {
    shadowJar { // TODO: maybe include the project name in the relocate, in case they have different project with the same group
        relocate("com.saicone.rtag", "${project.group}.libs.rtag")
        relocate("org.bstats", "${project.group}.libs.bstats")
        relocate("dev.triumphteam.gui", "${project.group}.gui")

        //minimize() // optional, disable if you get weird behaviors in your plugin
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}

tasks.withType<KotlinJvmCompile> {
    compilerOptions {
        javaParameters = true
    }
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
