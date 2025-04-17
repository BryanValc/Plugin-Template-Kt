package com.bryanvalc.pluginTemplateKt.common.modules

import com.bryanvalc.pluginTemplateKt.common.config.LanguageConfig
import com.bryanvalc.pluginTemplateKt.common.utils.ConfLoader
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.Locale


// TODO: check Adventure localization https://docs.advntr.dev/localization.html
class LangManager (
    val localesFolder: File,
    val config: LanguageConfig,
    val plugin: JavaPlugin
) {

    var languages = mutableMapOf<String, Any>()

    inline fun <reified T> getForLang(locale: Locale): T? {
        val language = locale.language.lowercase() + "_" + locale.country.uppercase()
        return loadLanguage(language)
    }


    inline fun <reified T> loadLanguage(language: String): T? {

        val lang = languages[language]
        if(lang!=null) return lang as T?

        var langFile = File(localesFolder, "${language}_messages.yml")
        if(!langFile.exists()){

            val defaultLang = languages[config.fallback] as T? // Use fallback language

            if(defaultLang == null) {
                langFile = File(localesFolder, "es_MX_messages.yml")
            } else {
                return defaultLang
            }

        }

        val defaultFile = plugin.getResource("locales/es_MX_messages.yml")

        val languagePackage = if (defaultFile == null) {
            ConfLoader.load<T>(langFile)!!
        } else {
            ConfLoader.load<T>(defaultFile, "yml")!!
        }

        languages.put(language, languagePackage)
        return languagePackage
    }


}