package dev.smartshub.paperFoldKt.listener

import dev.smartshub.paperFoldKt.data.Players
import com.github.shynixn.mccoroutine.bukkit.asyncDispatcher
import com.github.shynixn.mccoroutine.bukkit.launch
import dev.smartshub.paperFoldKt.data.PlayerDao
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

class PlayerJoinListener(): Listener, KoinComponent {

    val plugin: JavaPlugin by inject()
    val database: Database by inject()

    @EventHandler
    fun playerJoin(event: PlayerJoinEvent) {
        plugin.launch(context = plugin.asyncDispatcher) {
            val player = event.player
            var didExist = true

            transaction(db = database) { // register player if doesn't exist

                var playerDao = PlayerDao.find { Players.id eq player.uniqueId }.firstOrNull()

                if (playerDao == null) {
                    PlayerDao.new (player.uniqueId) {
                        name = player.name
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