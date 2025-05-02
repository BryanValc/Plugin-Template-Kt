package dev.smartshub.paperFoldKt.util

import dev.smartshub.paperFoldKt.common.module.LangManager
import dev.smartshub.paperFoldKt.localization.LanguagePack
import dev.smartshub.paperFoldKt.module.PluginRepository
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.entity.Player
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object Messaging: KoinComponent {

    private val langManager: LangManager by inject()
    private val pluginRepository: PluginRepository by inject()

    fun Player.sendParsed(message: String) {                  //legacy
        var component = this.getParsed(message)
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