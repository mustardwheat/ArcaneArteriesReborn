# Arcane Arteries Reborn

An arcane upgrade for Blood Magic (MC 1.12.2).

This is a continuation of two now-deleted upstreams:
- [Arcane Arteries](https://github.com/MaxSaleh/ArcaneArteries) by MaxIsH0t
- Arcane Arteries Kedition by Keletu66666

Now maintained by [mustardwheat](https://github.com/mustardwheat) under the same MIT license.

Strengthens the links between **Thaumcraft 6**, **Botania** and **Blood Magic 2**:

- Infuse stone in a **Crucible** or a **Mana Pool** to get Thaumic / Mana Slates,
  which a Blood Altar (tier 2, 1500 LP) turns into Reinforced Slates.
- The two slates craft the **Thaumic Rune** and **Mana Rune**, both usable as
  Blood Runes when building a Blood Altar.
- The **Runic Altar** and Crucible yield more efficient sacrificial daggers;
  the Mana Pool and Infusion yield more efficient daggers of sacrifice.

## Requirements (game)

- Minecraft 1.12.2, Forge 14.23.5.2847
- Thaumcraft 6, Blood Magic 2, Botania (+ Baubles)

## Building

Toolchain: **RetroFuturaGradle 2.0.4** on **Gradle 9.5** (wrapper included).

- Gradle daemon JVM: **Java 25+** (RFG 2.x requirement, set via `org.gradle.java.home`)
- Mod compilation/runtime: **Java 8** toolchain (auto-detected; see
  `org.gradle.java.installations.paths` in `gradle.properties`)
- Mod dependencies are vendored in `libs/` (the upstream project ships `-deof`
  jars for Blood Magic / Thaumcraft / Baubles; Botania / Guide-API / JEI are
  deobfuscated at build time by `rfg.deobf`).

```powershell
./gradlew build      # produces build/libs/ArcaneArteriesReborn-<ver>.jar (+ -dev, -sources)
./gradlew runClient  # dev client with all dependency mods
./gradlew runServer  # dev server
```

## License

MIT — see [LICENSE](LICENSE). Original work (c) 2019 MaxIsH0t,
Kedition changes (c) Keletu66666, Reborn changes (c) mustardwheat.
