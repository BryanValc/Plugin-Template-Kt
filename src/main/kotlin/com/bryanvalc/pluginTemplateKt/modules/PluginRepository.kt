package com.bryanvalc.pluginTemplateKt.modules

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class PluginRepository(
    private val basePlugin: JavaPlugin
) {
    var placeholderApi: Plugin? = null

    fun setup() {
        val logger = basePlugin.logger
        placeholderApi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI")
    }

}