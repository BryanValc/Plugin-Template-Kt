package com.bryanvalc.pluginTemplateKt.util

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player

// this has the purpose of avoiding errors if PlaceholderAPI plugin isn't present in the plugins folder

object PlaceholderParse {

    fun parse(player: Player, message: String): String {
        return PlaceholderAPI.setPlaceholders(player, message)
    }

}