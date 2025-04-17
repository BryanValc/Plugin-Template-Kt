package com.bryanvalc.pluginTemplateKt.common.config

import kotlinx.serialization.Serializable

@Serializable
data class LanguageConfig(
    val fallback: String
)