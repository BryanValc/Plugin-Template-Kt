package com.bryanvalc.pluginTemplateKt.commands

import com.bryanvalc.pluginTemplateKt.config.MainConfig
import com.bryanvalc.pluginTemplateKt.data.DummyRTag
import com.bryanvalc.pluginTemplateKt.data.SomeNestedStuff
import com.bryanvalc.pluginTemplateKt.utils.NbtConverter
import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.jorel.commandapi.kotlindsl.integerArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SetData: KoinComponent {

    val plugin: JavaPlugin by inject()
    val config: MainConfig by inject()

    fun register() {
        commandAPICommand("setdata") {
            withPermission("${plugin.name.lowercase()}.setdata")
            integerArgument("somedata")
            playerExecutor { player, args ->
                val someData = args["somedata"] as Int

                val someNestedStuff = SomeNestedStuff(someData)
                val dummyRTag = DummyRTag(someNestedStuff)

                val item = player.inventory.itemInMainHand
                if (item.isEmpty) return@playerExecutor

                NbtConverter.write(item, dummyRTag)
            }
            register(plugin)
        }
    }


}