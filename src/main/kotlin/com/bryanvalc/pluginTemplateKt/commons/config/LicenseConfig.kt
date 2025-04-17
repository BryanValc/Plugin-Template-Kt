package com.bryanvalc.pluginTemplateKt.commons.config

import kotlinx.serialization.Serializable

@Serializable
data class LicenseConfig(
    val key: String,
    val scope: String
)