package dev.smartshub.paperFoldKt.module

import org.bukkit.plugin.java.JavaPlugin

// TODO: check adventure-text-logger-slf4j, there's a way to properly use debug, kyori dev wouldn't waste time on this
class Debug(val plugin: JavaPlugin) {
    val logger = plugin.logger
    val debugEnabled = System.getProperty("${plugin.name.lowercase()}.debug","false").toBoolean()

    fun msg(message: String) {
        if(!debugEnabled) return
        logger.info { message }
    }

}