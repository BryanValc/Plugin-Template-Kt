package dev.smartshub.paperFoldKt.util

import dev.smartshub.paperFoldKt.common.module.LangManager
import dev.smartshub.paperFoldKt.localization.LanguagePack
import dev.smartshub.paperFoldKt.module.PluginRepository
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.minimessage.Context
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.Tag
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
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
        // usage: <a:https://yourlink>TextToShow</a>
        //TODO: maybe should use regex here
        var component = if(text.contains("<a:")) { //minimessage
            val extendedInstance = MiniMessage.builder()
                .editTags{b -> b.tag("a",::createA) }
                .build()
            extendedInstance.deserialize(text)
        } else {
            MiniMessage.miniMessage().deserialize(text)
        }
        return component                                            //return
    }

    fun createA(args: ArgumentQueue, context: Context): Tag {
        val link = args.popOr("The <a> tag requires exactly one argument, the link to open").value()

        val content = Component.text("Open $link")
        return Tag.styling(
            ClickEvent.openUrl(link),
            HoverEvent.showText(content)
        )

    }

    fun Player.getLanguagePack(): LanguagePack? = langManager.getForLang(this.locale())

}