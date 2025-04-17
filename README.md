# Plugin-Template-Kt

A Kotlin-Based Minecraft Framework for PaperMC plugins, with the express purpose of exploiting all the Kotlin ergonomics and including all the most common things commercial plugins require. It is Batteries Included and Opinionated (which means we won't include technologies that we don't like, for instance MongoDB, though you can easily integrate them if you want - we respect that. We just prefer the flavor of SQL. Here we don't care if another technology is purist or has better performance; we want to directly deliver server owners what they want as quickly and reliably as possible with very few lines of code).

## What Plugin-Template-Kt Isn't and Won't Do

- **Fork of Paper or other Server Software**: That takes a lot of effort, and we are here to take on commissions and publish our paid plugins.
- **Fully featured library**: We just want to join together the best libraries out there with some sugar syntax in a concise yet flexible way that speeds up production of commercial plugins with the most common use cases.
- **Core**: There are many plugins out there that excel at this already, even though if you decide to create a core with this, we would be interested in seeing that.
- **Multi Platform**: Paper now became a hard fork of Spigot, most servers run on Paper anyway, so we'll stick to that.
- **Proxy Framework**: There aren't as many libraries for proxies, that requires a higher skill level from you, and you're probably better off picking your own libraries by hand in that case (we will eventually split some general purpose libraries and utilities in a separate dependency for the sake of simplicity, but don't expect it to have the full sweetness of this framework).
- **Support older versions**: If you want to work with older versions, you probably know your stuff already or there's a better solution for that out there.
- **Fully guarantee the security of your product**: It comes with an obfuscation that you can easily enable/disable which is better than nothing, but if your product got really big, we encourage you to try other settings for ProGuard, or another solution altogether.
- **Have a separate dependencies plugin**: This is to avoid forcing you to update if that plugin gets updated, and to avoid mismatch between versions and conflicts if another developer uses another version of that separate dependencies plugin. You can create your own if you use this framework a lot, but please shade it under a different package name.
- **Become paywalled**: I do Software Development for a living, but this time I feel like giving software away, because I think it should be easier to develop plugins and I've seen all the time people reinventing the wheel in a catastrophic way. There are better developers than me out there that could help me improve this.

## Skill Level Required

This framework of course assumes you know how to code in Kotlin, and it would be even better if you had previous experience developing plugins. If you are new to Minecraft plugin development, I suggest you to star this project and check it later when you have a basic notion by practicing with some projects. Also, it would be a nice extra if you understand the following subjects:

- Understand how Dependency Injection works
- Working of `kotlinx.serialization` `@Serializable`
- Basic understanding of how SQL Transactions work
- Know what an ORM is
- Working of Kotlin Coroutines

### Ideal Skill Level

It takes into account the previous concepts, but if you are familiar with the following concepts, you will be able to get the most value out of this Framework:

- Know when and when not to use auto-expirable caches
- Understand how to debug properly, with breakpoints
- Modify Gradle builds
- Working of NBTs
- Know what NMS is
- Understand how to consume and provide placeholders with PlaceholderAPI

## Features

- **Full Power of PaperMC API and Kotlin**: This is very obvious, but you can leverage the full power of both, which are really powerful on their own.
- **Highly Modular and Decoupled**: Easy to test, thanks to Koin. You can pick what modules to use.
- **Automatic Database/Cache Setup**: You don't have to worry about how to instance the database or cache system—just provide the configuration. If you don't provide the configuration or don't consume the services, they won't be instantiated. Everything is decoupled so you can easily delete modules that you don't want without worrying about breaking your codebase.
- **Multi-Database Support**: SQLite, MariaDB, PostgreSQL, H2. You allow the server owners to provide the configuration, and that's it.
- **Simple ORM**: With Kotlin Exposed by JetBrains, has tons of sugar syntax.
- **Fast Cache**: With Caffeine. You don't have to worry about defining the size and expiration minutes—server owners can provide these parameters easily.
- **Multiple Config Formats**: With `kotlinx.serialization` (HOCON, JSON, YML).
- **Simple Facade for Configs**: Just pass the `File` or the `InputStream` (with `plugin.getResource("some_file.yml")`), and get the data class that you want.
- **Server License System**: Properly end-to-end encrypted, with `license-gate`.
- **MCCoroutines**: To leverage the power of Kotlin Coroutines, allowing you to use suspended functions and async threads in a clean way. It avoids the callback nightmare.
- **Simple and Blazingly Fast NBT Facade**: With `rTag`, you can write and read objects in a simple way (supports `ItemStack`, `Entity`, `TileEntity`).
- **Auto NBT Path Setup**: Even though `rTag` is very simple, you have to set up a custom ID so you don't mess with other plugin's tags. We did that work already for you! They are automatically added to a path based on your plugin and class name.
- **PlaceholderAPI Included**: To process and provide your own placeholders. Error-prone (it first verifies if PlaceholderAPI is present in the server as a generic `JavaPlugin`, only then it will reference PlaceholderAPI code).
- **Placeholder Processing**: Legacy and MiniMessage processed in that order with a sugar syntax Extension Function, as simple as `Player.sendParsed("SomeText")`.
- **Multi-Language**: You can have your `LanguagePack` data class which be mapped to a config format of your choice with `kotlinx.serialization`, as simple as `Player.getLanguagePack()`. This is really convenient because you get smart auto-completion.
- **Bundled Default Configurations**: Includes data classes for Database and Cache to help you get started.
- **Bundled Examples**: Of CommandAPI commands.
- **Bundled NBT Examples**: How to write data classes in `TileEntity`, `Entity`, `ItemStack`, and vice-versa.
- **Bundled LanguagePack Data Class**: So you only have to modify it and change the default language file (`.json`/`.yml`/`.hocon`).
- **Super Simple Commands**: With CommandAPI, it has its own Kotlin DSL which is really easy and Batteries Included (though we are considering switching over to Lamp).
- **Plain Text Serializer**: From `net.kyori`—if you ever went through the problem of trying to read or use `equals` for a text, this is the solution!
- **Legacy Serializer**: From `net.kyori`—if you want to handle legacy text in your own way.
- **bStats Included and Auto-Relocated**: You just provide your plugin ID from bStats—that's it!
- **Gradle Shadow**: It has many more utility stuff compared to the commonly bundled in the Minecraft Development Wizard.
- **Run Server in IntelliJ**: With a simple button, thanks to `jpenilla.run-paper`. It is recommended to use the debug button.
- **Easier Debugging**: Your terminal is connected to the `run-paper` server, and you can set breakpoints and inspect variables.
- **Debug Wrapper**: You can log debug messages in your environment, and they won't show in the server of your clients. It is only active if there's a custom JVM arg (the argument is already bundled in the `build.gradle.kts`).
- **Common Plugins Auto-Downloaded**: PlaceholderAPI, CommandAPI, ViaVersion, ViaBackwards, ViaRewind in the `run-paper` task.
- **Instant Reload Gracefully**: With HotSwap. When you are running the `run-paper` task, when you change your code, the IDE prompts you if you want to apply the changes. You don't have to restart the entire server (beware: currently won't work sometimes because of the Shadow plugin. We are trying to find a workaround—if you got the solution, please open a PR).

## Features Being Worked On

- **Single-Click ProGuard Obfuscation**: To add a layer of protection to your work. Any license system that you implement will eventually be bypassed by skilled enough crackers, but at least don't let your competitors steal your work.
- **Zapper Gradle Plugin**: For cleaner and overall better dependency management.
- **Lamp Commands**: So your plugin isn't required to depend on CommandAPI plugin.
- **PacketEvents**: A really good library for Packets, bundled with Shadow and proper boot, so your plugin isn't required to depend on PacketEvents plugin.
- **Paperweight-UserDev**: To have super simple NMS access.
- **Fix Overlap Between Shadow and HotSwap**: Maybe check PaperMake GitHub?
- **Fix Reload Command**: There was a huge refactor, and the bundled Reload command isn't working right now.
- **Version Update Check Utility**
- **Provide Code Snippets**: To make it even easier to write commands, event handlers, data classes, DAOs, and many more things.
- **Add a Test Framework**
- **Database Migration System**: That allows server owners to migrate databases (e.g., SQLite to MariaDB).
- **Opt-In Auto Reload When Config Changes**: Hoplite or `kfswatch` would make this easier.
- **Integrate Kotlin Channels**: In a fancy way to have something similar to Godot's signals (please don't over-abuse this).
- **Include MiniPlaceholders**

## Future Plans (We Still Have to Further Evaluate All of These)

- **Properly Document the Code**: Not a huge fan of it, as it's preferable that the code explains itself.
- **Integrate ArrowKt**: For a domain-first design, though we don't know if it will be helpful for most cases.
- **Integrate Geary ECS**: The ECS design itself aligns with the philosophy of this project, but we don't know if this approach is the best for most products.
- **Use Adventure API Logger**: The current wrapper that evaluates the JVM arg works great, but there has to be a way to properly use the debug level. We haven't figured out how to do it yet—any help is welcomed.
- **Use Hoplite**: The current facade works great, but Hoplite has a lot of sugar syntax for configurations of all formats and has Serializers for stuff like ArrowKt. We couldn't get past a bug that says no encoders were found, probably because of the Shadow plugin.
- **Implement Configuration Migrate Again**: Still need to think if this is a good behavior.
- **Auto-Generate Configuration Schemas**: To make it easier for server owners to configure your plugin and avoid many tickets related to typos. Though it might be overkill for really simple configurations, and not all server owners would know how to import them (maybe with a VSCode plugin, or maybe there's a way in which VSCode automatically does this, but we don't know).
- **Create an IntelliJ Wizard**: This is unexplored territory for us, and we have no idea how complicated this can be. For now, we think it's better to keep improving the ergonomics of the Framework (if you know how to create plugins for IDEA, you can reach out to us, and we can discuss what would be the best UI/UX approach. You can make it a paid plugin if that can further motivate you, and we would feature it here).
- **Create a Docs Page**: Very similar to the one that FastAPI has (I didn't copy the architecture of any specific framework, I just like that docs page), but that will be useful only if there are many people actually using this.
