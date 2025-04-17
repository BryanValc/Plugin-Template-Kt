package com.bryanvalc.pluginTemplateKt.event

import com.bryanvalc.pluginTemplateKt.data.Players
import com.github.shynixn.mccoroutine.bukkit.asyncDispatcher
import com.github.shynixn.mccoroutine.bukkit.launch
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PlayerJoin(): Listener, KoinComponent {

    val plugin: JavaPlugin by inject()
    val database: Database by inject()

    @EventHandler
    fun playerJoin(event: PlayerJoinEvent) {
        plugin.launch(context = plugin.asyncDispatcher) {
            val player = event.player
//            delay(10_000)//simulate huge db delay
            var didExist = true

            transaction(db = database) { // register player if it doesn't exist
                var playerReg = Players.selectAll().where { Players.uuid eq player.uniqueId }.toList()
                if (playerReg.isEmpty()) {
                    Players.insert {
                        it[uuid] = player.uniqueId
                        it[name] = player.name
                    }
                    didExist = false
                }
            }
            if(!didExist){
                player.sendMessage("Your data has been created")
            } else {
                player.sendMessage("Your data has been loaded")
            }


        }
    }


}