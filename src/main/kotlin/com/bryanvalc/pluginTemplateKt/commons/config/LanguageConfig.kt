package com.bryanvalc.pluginTemplateKt.commons.config

import kotlinx.serialization.Serializable

@Serializable
data class LanguageConfig(
    val fallback: String
)