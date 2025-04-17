package com.bryanvalc.pluginTemplateKt.commands

import com.bryanvalc.pluginTemplateKt.commons.utils.ConfLoader
import com.bryanvalc.pluginTemplateKt.config.MainConfig
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

class Reload: KoinComponent {

    val plugin: JavaPlugin by inject()
    val config: MainConfig by inject()

    fun register() {
        commandAPICommand("reload") {
            withPermission("${plugin.name.lowercase()}.reload")
            playerExecutor { player, args ->

                val configFile = File(plugin.dataFolder, "config.yml")
//                val defaultConfig = plugin.getResource("config.yml")

                val node = ConfLoader.load<MainConfig>(configFile)!!

                //FIXME: make reload actually work

                player.sendMessage("Reload successful")
            }
            register(plugin)
        }
    }


}