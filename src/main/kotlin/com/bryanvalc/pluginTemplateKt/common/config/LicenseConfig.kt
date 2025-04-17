package com.bryanvalc.pluginTemplateKt.common.config

import kotlinx.serialization.Serializable

@Serializable
data class LicenseConfig(
    val key: String,
    val scope: String
)