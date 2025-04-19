<a href="https://discord.gg/bQbV6PsX">
  <img src="https://i.imgur.com/cL6yz85.png" />
</a>

Kotlin-based framework for PaperMC plugins that leverages Kotlin’s ergonomics and includes all the essential features for commercial plugins. It's Batteries Included and Opinionated.

## Current State of the Project ⚠️

We are currently mixing very strong technologies, the merging isn't that smooth at the beginning and there isn't a release yet, we will properly implement GitHub Releases, so before cloning/forking this project, please watch/star the project and then come back when there's a release, if you think you are experienced enough to help us get to that point any help is welcomed. 📈

## What Plugin-Template-Kt Isn't & Won't Do 🚫

- **Server Fork**: We’re focused on paid plugins, not forking PaperMC.
- **Fully Featured Library**: It combines top libraries with Kotlin sugar syntax to speed up commercial plugin production.🍯
- **Core**: There are existing solutions for this; if you build one with this framework, we’d love to see it!
- **Multi Platform**: We stick to Paper; no Spigot or Proxy here. 🧱
- **Support older versions**: If you’re targeting older versions, you probably know what you're doing! 🧙‍♂️
- **Security Guarantees**: Offers obfuscation, but for serious protection, you’ll want a stronger solution. 🔒
- **Separate Dependencies Plugin**: Avoids version mismatches and dependency conflicts. You can manage your own if needed. 🔄

## Skill Level 🎓

You should know Kotlin and ideally have some plugin dev experience. New to Minecraft plugin dev? 🌱 Bookmark this and come back later! Some helpful skills:

- Dependency Injection 🧩
- `kotlinx.serialization` `@Serializable` 📝
- SQL Transactions 💾
- ORM basics 📙
- Kotlin Coroutines ⏳

### Ideal Skills 🔥

It takes into account the previous concepts, but if you are familiar with the following concepts, you will be able to get the most value out of this Framework:

- Know when to use expirable caches 🕒
- Debugging with breakpoints 🐞
- Modifying Gradle builds ⚙️
- Handling NBT (Named Binary Tags) 📦
- NMS (Net Minecraft Server) 📡
- Integrating PlaceholderAPI 🧩🔄

## Features 🎉

- **Powerful PaperMC & Kotlin API**: Leverage both to their fullest. ⚡
- **Modular & Decoupled**: Easy testing thanks to Koin, choose the modules you need.
- **Auto Database/Cache Setup**: No need to configure manually—just provide the settings! 💾
- **Multi-Database Support**: SQLite, MariaDB, PostgreSQL, H2.
- **Simple ORM**: Thanks to Kotlin Exposed, with clean syntax.
- **Fast Cache**: Using Caffeine, no need to fiddle with sizes or expiration—server owners handle that. 🏎️
- **Multiple Config Formats**: HOCON, JSON, YML via `kotlinx.serialization`.
- **Simple Facade for Configs**: Pass a File/InputStream and get your data class.
- **Server License System**: Properly end-to-end encrypted, with `license-gate`.
- **MCCoroutines**: Kotlin Coroutines integration to avoid callback hell. 🙌
- **Super Fast NBT Facade**: With `rTag`, read/write data from ItemStacks, Entities, etc.
- **Auto NBT Path Setup**: Custom IDs auto-generated to avoid conflicts.
- **PlaceholderAPI Included**: Error-prone setup to process and provide your own placeholders.
- **Placeholder Processing**: Placeholders, Legacy and MiniMessage processed in that order, as simple as `Player.sendParsed("SomeText")`.
- **Multi-Language**: Automatically map Language Packs with auto-completion. 🌍
- **Bundled Default Configurations**: Includes data classes for Database and Cache to help you get started.
- **Bundled LanguagePack Data Class**: Modify the default language file  (`.json`/`.yml`/`.hocon`) easily.
- **Super Simple Commands**: With Lamp Commands, it's easy to use, with Batteries Included.
- **Plain Text Serializer**: From `net.kyori`—perfect for text comparison or `equals` issues, here's the solution! ✨
- **Legacy Serializer**: From `net.kyori`—if you want to handle legacy text in your own way.
- **bStats Included & Auto-Relocated**: Just provide your plugin ID, and bStats is ready to go.📊
- **Gradleup Shadow**: More utilities than the typical Minecraft Development Wizard bundle. ⚙️
- **Run Server in IntelliJ**: With a simple button, thanks to `jpenilla.run-paper`. It is recommended to use the debug button.
- **Easier Debugging**: Your terminal is connected to the `run-paper` server, and you can set breakpoints and inspect variables. 🐞
- **Debug Wrapper**: Logs debug messages to your environment server, not your clients' server, active only with a custom JVM arg(we've already setup this for you). 🛠️
- **Common Plugins Auto-Downloaded**: PlaceholderAPI, ViaVersion, ViaBackwards, ViaRewind in the `run-paper` task. 📥
- **Instant Reload Gracefully**: HotSwap lets you apply code changes without restarting the server (still ironing out a Shadow plugin issue).
- **Zapper Gradle Plugin**: Improved dependency management. ⚡
- - **Paperweight-UserDev**: Super simple NMS access.

## Features Being Worked On 🔨

- **Single-Click ProGuard Obfuscation**: A protection layer to make it harder for competitors to steal your code. 🔒
- **PacketEvents**: Bundled with Shadow for packet handling, your clients won't need to download PacketEvents plugin. 📦
- **Fix Overlap Between Shadow and HotSwap**: Working on a solution, possibly via PaperMake GitHub.
- **Fix Reload Command**: Post-refactor issues with the bundled reload command.
- **Version Update Check Utility**: Automated version check. 🔍
- **Provide Code Snippets**: With GitHub Gists, for commands, event handlers, data classes, DAOs, etc. ✂️
- **Add a Test Framework**: In progress to simplify unit tests. 🧪
- **Database Migration System**: Migrate databases (e.g., SQLite to MariaDB) with ease. 💾➡️💻
- **Opt-In Auto Reload When Config Changes**: `Hoplite` or `kfswatch` would make this easier. ⏱️
- **Integrate Kotlin Channels**:  Implementing a signal-like system (similar to Godot’s), but use sparingly. 🌀
- **Include MiniPlaceholders**: Integration for custom placeholder handling.
- **Code Documentation**:  Still prefer code to explain itself, but documentation may help. 📚
- **Create a Wiki Page**: A Wiki Page with practical examples on how to use the Framework. 📖

## Future Plans (Under evaluation, we'd like to know your thoughts) 🤔

- **Integrate ArrowKt**: For a domain-first design (unsure if it’s beneficial in most cases).
- **Integrate Geary ECS**:  ECS could align with the project philosophy but needs evaluation. 🧭
- **Use Adventure API Logger**: Debug level handling with the logger still needs refining.
- **Use Hoplite**: For better configuration syntax, but encountered bugs related to Shadow plugin.
- **Implement Configuration Migration Again**: We’re unsure about if this is a good behavior.
- **Auto-Generate Configuration Schemas**: Would simplify configuration for users but may be overkill. 🧰
- **Create an IntelliJ Wizard**: Exploring the possibility of an IntelliJ plugin for setup, if you’re experienced, reach out! 🧙‍♂️


## Disclaimer ⚠️

This is a third party framework and is not affiliated with PaperMC, Minecraft, Mojang AB, or Microsoft Inc, if you use it in a way that breaks the Minecraft EULA we will take no accountability.
