plugins {
    java
    id("io.izzel.taboolib") version "1.56"
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
}

taboolib {
    install("common")
    install("common-5")
    install("module-chat")
    install("module-lang")
    install("module-metrics")
    install("module-configuration")
    install("platform-bukkit")
    classifier = null
    version = "6.0.12-13"

    description {
        contributors {
            name("Yunmoa")
        }
        desc("A Bukkit Menu Plugin for TabooLib")
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/public")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://repo.dmulloy2.net/repository/public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://jitpack.io")
    // taboo的仓库有时候github自动构建连不上, 丢到最后防止自动构建发生意外
    maven("https://repo.tabooproject.org/storages/public/releases")
}

configurations{
    maybeCreate("packShadow")
    get("compileOnly").extendsFrom(get("packShadow"))
    get("packShadow").extendsFrom(get("taboo"))
}

dependencies {
    compileOnly("ink.ptms.core:v11902:11902-minimize:mapped")
    compileOnly("ink.ptms.core:v11902:11902-minimize:universal")
    compileOnly(fileTree("libs"))
    "packShadow"(kotlin("stdlib"))
    "packShadow"("org.openjdk.nashorn:nashorn-core:15.4")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}