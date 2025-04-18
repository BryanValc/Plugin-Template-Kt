package dev.smartshub.paperFoldKt.common.module

import dev.smartshub.paperFoldKt.common.config.LanguageConfig
import dev.smartshub.paperFoldKt.common.service.ConfLoader

import java.io.File
import java.io.InputStream
import java.util.Locale


// TODO: check Adventure localization https://docs.advntr.dev/localization.html
class LangManager (
    val localesFolder: File,
    val config: LanguageConfig,
    val defaultFile: InputStream?,
    val defaultFileExtension: String
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

        val languagePackage = if (defaultFile == null) {
            ConfLoader.load<T>(langFile)!!
        } else {
            ConfLoader.load<T>(defaultFile, defaultFileExtension)!!
        }

        languages.put(language, languagePackage)
        return languagePackage
    }


}