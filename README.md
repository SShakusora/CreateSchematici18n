# Create: Schematic i18n

**Create: Schematic i18n** is a lightweight utility mod designed to bridge the gap between the **Create's** schematic system and Minecraft's built-in translation engine. It allows players and modpack creators to define localized names for `.nbt` schematic files using standard language files.

## Features

* ​**Custom Localization**​: Assign human-readable, localized names to your schematic files.
* ​**Dynamic Translation**​: Leverages the Minecraft I18n system to support multiple languages.
* ​**Improved UX**​: Say goodbye to cryptic filenames like `factory_v2_final_fixed.nbt` and hello to clean, translated titles in the schematic UI.
* ​**Seamless Integration**​: Works directly with Create’s existing schematic lists.

## How to Use

Instead of displaying the raw filename, this mod looks for a specific key in your language files (`en_us.json`, `zh_cn.json`, etc.).

### 1\. Identify your Schematic Key

The mod generates translation keys based on the schematic's filename (without the `.nbt` extension).

### 2\. Add Translations

Add the corresponding keys to your resource pack's language files:

**`assets/your_pack/lang/en_us.json`**

JSON

```
{
  "schematic.steam_engine_core": "Steam Engine Core",
  "schematic.automatic_farm_v1": "Compact Automatic Farm"
}
```

**`assets/your_pack/lang/zh_cn.json`**

JSON

```
{
  "schematic.steam_engine_core": "蒸汽机核心",
  "schematic.automatic_farm_v1": "小型自动农场"
}
```

### 3\. See the Result

Once the resource pack is active, the Create Schematic table and UI will display "Steam Engine Core" instead of `schematic.steam_engine_core.nbt`.

## Comparison Screenshots

Here's a quick visual comparison to demonstrate the mod's effect:

### Before: Raw Schematic Filenames

![Before](https://github.com/SShakusora/CreateSchematici18n/blob/1.20.1/Comparison%20Screenshots/before.PNG?raw=true)

### After: Localized Schematic Names

![After](https://github.com/SShakusora/CreateSchematici18n/blob/1.20.1/Comparison%20Screenshots/after.PNG?raw=true)

## Installation

1. Make sure you have **Minecraft Forge/NeoForge** and **Create** installed.
2. Download the latest version of ​**Create: Schematic i18n**​.
3. Place the `.jar` file into your `mods` folder.

## For Modpack Creators

You can use this mod to professionally localize included schematics in your modpack. Simply include the translation keys in your global resource pack or via KubeJS/OpenLoader.

## License

This project is licensed under the **MIT License** - see the [LICENSE](https://github.com/SShakusora/CreateSchematici18n/blob/1.20.1/LICENSE.txt) file for details.

## Links

* ​**GitHub Repository**​: [SShakusora/CreateSchematici18n](https://github.com/SShakusora/CreateSchematici18n)
* ​**Issue Tracker**​: Report bugs [here](https://github.com/SShakusora/CreateSchematici18n/issues)

