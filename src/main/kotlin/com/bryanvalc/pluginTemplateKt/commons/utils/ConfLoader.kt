package com.bryanvalc.pluginTemplateKt.commons.utils


import com.typesafe.config.ConfigFactory

import kotlinx.serialization.hocon.Hocon
import kotlinx.serialization.hocon.decodeFromConfig
import kotlinx.serialization.json.Json
import java.io.File
import com.charleskorn.kaml.Yaml
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import java.io.InputStream
import java.util.UUID

object ConfLoader {


    val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    val yaml = Yaml

    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified T> load(file: File): T? {
        return when (file.extension.lowercase()) {
            "json", ".json" -> json.decodeFromString<T>(file.readText())
            "yaml", "yml", ".yaml", ".yml" -> yaml.default.decodeFromString<T>(file.readText())
            "conf", ".conf" -> {
                val config = ConfigFactory.parseFile(file).resolve()
                Hocon.decodeFromConfig<T>(config)
            }
            else -> throw IllegalArgumentException("Unsupported file format: ${file.extension}")
        }

    }

    inline fun <reified T> load(stream: InputStream, extension: String): T? {

        val tempFile = File.createTempFile("${UUID.randomUUID()}", extension)
        stream.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return load(tempFile)

    }

}