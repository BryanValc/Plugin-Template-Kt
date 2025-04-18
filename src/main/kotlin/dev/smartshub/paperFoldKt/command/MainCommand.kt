package dev.smartshub.paperFoldKt.command

import dev.smartshub.paperFoldKt.config.MainConfig
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Subcommand
import revxrsal.commands.bukkit.actor.BukkitCommandActor
import revxrsal.commands.bukkit.annotation.CommandPermission

@Command("plugin-template")
@CommandPermission("plugin-template.admin")
class MainCommand: KoinComponent {

    val plugin: JavaPlugin by inject()
    val config: MainConfig by inject()

    @Subcommand("reload")
    fun reload(actor: BukkitCommandActor) {
        actor.sender().sendMessage("Reloading config...")
        //TODO: implementation, file system might change, then use the new one
    }

}