package com.bryanvalc.pluginTemplateKt.config

import com.bryanvalc.pluginTemplateKt.commons.config.*
import kotlinx.serialization.Serializable

@Serializable
data class MainConfig(
    val cache: CacheConfig,
    val language: LanguageConfig,
    val license: LicenseConfig,
    val storage: StorageConfig
)