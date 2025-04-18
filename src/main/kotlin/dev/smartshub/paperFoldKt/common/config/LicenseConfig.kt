package dev.smartshub.paperFoldKt.common.config

import kotlinx.serialization.Serializable

@Serializable
data class LicenseConfig(
    val key: String,
    val scope: String
)