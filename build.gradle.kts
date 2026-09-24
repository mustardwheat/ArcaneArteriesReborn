plugins {
    id("java-library")
    id("eclipse")
    id("com.gtnewhorizons.retrofuturagradle") version "2.0.4"
}

group = "com.keletu.arcane_arteries"
version = "1.0.0"

// Decouple the JVM running Gradle (17+) from the JVM used to compile/run the mod (8)
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
    withSourcesJar()
}

minecraft {
    mcVersion.set("1.12.2")
    // RFG 1.12.2 defaults: Forge 14.23.5.2847, MCP mappings stable_39 (same as the old FG2 build)

    username.set("Developer")
    extraRunJvmArguments.add("-ea:" + project.group)
}

tasks.processResources {
    val projVersion = project.version.toString() // needed for configuration cache
    inputs.property("version", projVersion)
    inputs.property("mcversion", "1.12.2")

    filesMatching("mcmod.info") {
        expand(mapOf("version" to projVersion, "mcversion" to "1.12.2"))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Required mods (also enforced by @Mod dependencies).
    // *-deof jars are already deobfuscated (MCP names), so they are used as-is.
    implementation(files("libs/BloodMagic-1.12.2-2.4.1-103-deof.jar"))
    implementation(files("libs/Thaumcraft-1.12.2-6.1.BETA26-deof.jar"))
    implementation(files("libs/Baubles-1.12-1.5.2-deof.jar"))

    // Obfuscated jars need the RFG deobfuscation transformer.
    implementation(rfg.deobf(files("libs/Botania-r1.10-364.4.jar")))

    // Blood Magic's Guide-API dependency and JEI for recipe browsing in dev runs.
    runtimeOnly(rfg.deobf(files("libs/Guide-API-1.12-2.1.8-63.jar")))
    runtimeOnly(rfg.deobf(files("libs/jei_1.12.2-4.15.0.287-obf.jar")))
    // Thaumic JEI so Crucible/Infusion/Arcane recipes show up in JEI.
    runtimeOnly(rfg.deobf(files("libs/ThaumicJEI-1.12.2-1.7.0.jar")))
}
