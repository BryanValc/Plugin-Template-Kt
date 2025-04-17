plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "2.1.20"

    id("com.gradleup.shadow") version "9.0.0-beta8"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "com.bryanvalc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }

    // licensing
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

val paperVersion: String by project // PURE MINECRAFT
val bStatsVersion: String by project
val adventureVersion: String by project
val commandApiVersion: String by project
val papiVersion: String by project
val mcCoroutineVersion: String by project

val rTagVersion: String by project
val knbtVersion: String by project
val kamlVersion: String by project

val licenseGateVersion: String by project // LICENSING

val serializationVersion: String by project // SERIALIZATION AND CONFIGS

val caffeineVersion: String by project // CACHING AND DATABASES
val koinVersion: String by project

val exposedVersion: String by project
val sqliteVersion: String by project
val mariadbVersion: String by project
val hikariVersion: String by project
val postgresVersion: String by project
val h2Version: String by project

dependencies {
    compileOnly("io.papermc.paper:paper-api:$paperVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // bStats
    implementation("org.bstats:bstats-bukkit:$bStatsVersion")
    // Adventure
    implementation("net.kyori:adventure-text-serializer-plain:$adventureVersion")
    implementation("net.kyori:adventure-text-serializer-legacy:$adventureVersion")
    // CommandAPI
    implementation("dev.jorel:commandapi-bukkit-kotlin:$commandApiVersion")
    compileOnly("dev.jorel:commandapi-bukkit-core:$commandApiVersion")
    // PAPI
    compileOnly("me.clip:placeholderapi:$papiVersion")
    // coroutines
    implementation("com.github.shynixn.mccoroutine:mccoroutine-bukkit-api:$mcCoroutineVersion")
    implementation("com.github.shynixn.mccoroutine:mccoroutine-bukkit-core:$mcCoroutineVersion")
    // rTag
    implementation("com.saicone.rtag:rtag:$rTagVersion")
    // Other modules
    implementation("com.saicone.rtag:rtag-block:$rTagVersion")
    implementation("com.saicone.rtag:rtag-entity:$rTagVersion")
    implementation("com.saicone.rtag:rtag-item:$rTagVersion")

    // license server
    implementation("dev.respark.licensegate:license-gate:$licenseGateVersion")

    // serialization //maybe not needed? didn't hear any mention of it in hoplite and neither is hoplite using the annotations
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-hocon:$serializationVersion")   // .conf serializationx
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")    // .json serializationx
    implementation("net.benwoodworth.knbt:knbt:$knbtVersion")                                   // .nbt  serializationx
    implementation("com.charleskorn.kaml:kaml:$kamlVersion")                                    // .yml  serializationx

    // DI
    implementation("io.insert-koin:koin-core:$koinVersion")
    //caching
    implementation("com.github.ben-manes.caffeine:caffeine:$caffeineVersion")

    //ORM
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-money:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-migration:$exposedVersion")
    // databases
    implementation("org.xerial:sqlite-jdbc:$sqliteVersion")
    implementation("org.mariadb.jdbc:mariadb-java-client:$mariadbVersion")
    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("com.zaxxer:HikariCP:$hikariVersion")
    implementation("com.h2database:h2:$h2Version")
}

tasks {
    runServer {
        downloadPlugins {
            hangar("PlaceholderAPI", "2.11.6")
            hangar("CommandAPI", "10.0.0")
            hangar("ViaVersion", "5.3.1")
            hangar("ViaBackwards", "5.3.1")
            hangar("ViaRewind", "4.0.7")

        }
        minecraftVersion("1.21")
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

        //minimize() // optional, disable if you get weird behaviors in your plugin
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
