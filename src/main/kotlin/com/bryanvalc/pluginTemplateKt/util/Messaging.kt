package com.bryanvalc.pluginTemplateKt.util

import com.bryanvalc.pluginTemplateKt.common.module.LangManager
import com.bryanvalc.pluginTemplateKt.localization.LanguagePack
import com.bryanvalc.pluginTemplateKt.module.PluginRepository
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object Messaging: KoinComponent {

    private val langManager: LangManager by inject()
    private val pluginRepository: PluginRepository by inject()

    fun Player.sendParsed(message: String) {
        val placeholderApiEnabled = pluginRepository.placeholderApi != null
        var text = message
        if (placeholderApiEnabled) { PlaceholderParse.parse(this, text)} //placeholders
        text = LegacyMessage.fromLegacy(text)                       //legacy
        var component = MiniMessage.miniMessage().deserialize(text) //minimessage
        this.sendMessage(component)                                 //send
    }

    fun Player.getParsed(message: String): Component {
        val placeholderApiEnabled = pluginRepository.placeholderApi != null
        var text = message
        if (placeholderApiEnabled) { PlaceholderParse.parse(this, text)} //placeholders
        text = LegacyMessage.fromLegacy(text)                       //legacy
        var component = MiniMessage.miniMessage().deserialize(text) //minimessage
        return component                                            //return
    }

    fun Player.getLanguagePack(): LanguagePack? = langManager.getForLang(this.locale())

}