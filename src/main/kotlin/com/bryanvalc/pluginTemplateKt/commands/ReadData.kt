package com.bryanvalc.pluginTemplateKt.commands

import com.bryanvalc.pluginTemplateKt.config.MainConfig
import com.bryanvalc.pluginTemplateKt.data.DummyRTag
import com.bryanvalc.pluginTemplateKt.utils.NbtConverter
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ReadData: KoinComponent {

    val plugin: JavaPlugin by inject()
    val config: MainConfig by inject()

    fun register() {
        commandAPICommand("readdata") {
            withPermission("${plugin.name.lowercase()}.readdata")
            playerExecutor { player, args ->
                val item = player.inventory.itemInMainHand
                if (item.isEmpty) return@playerExecutor

                val dummyRTag =  NbtConverter.read<DummyRTag>(item)
                if (dummyRTag == null) {
                    player.sendMessage("Requested data isn't present")
                } else {
                    player.sendMessage(dummyRTag.toString())
                }
            }
            register(plugin)
        }
    }


}