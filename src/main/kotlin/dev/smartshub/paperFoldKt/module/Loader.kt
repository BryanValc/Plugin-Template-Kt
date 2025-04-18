package dev.smartshub.paperFoldKt.module

import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Loader(
    val plugin: JavaPlugin
) {

    fun bootstrap() {
        if(!plugin.dataFolder.exists()){
            val couldCreate = plugin.dataFolder.mkdir()
            if(!couldCreate) plugin.logger.warning("Couldn't create plugin folder")
        }

        var configFile = File(plugin.dataFolder, "config.yml")
        if(!configFile.exists()){
            plugin.saveResource("config.yml", false)
        }

        var defaultLocale = File(plugin.dataFolder, "locales/es_MX_messages.yml")
        if(!defaultLocale.exists()){
            plugin.saveResource("locales/es_MX_messages.yml", false)
        }

    }

}