package dev.smartshub.paperFoldKt.common.config

import kotlinx.serialization.Serializable

@Serializable
data class LanguageConfig(
    val fallback: String
)