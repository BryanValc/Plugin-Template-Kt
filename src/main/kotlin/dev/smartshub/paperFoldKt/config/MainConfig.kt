package dev.smartshub.paperFoldKt.config

import dev.smartshub.paperFoldKt.common.config.*
import kotlinx.serialization.Serializable

@Serializable
data class MainConfig(
    val cache: CacheConfig,
    val language: LanguageConfig,
    val license: LicenseConfig,
    val storage: StorageConfig
)