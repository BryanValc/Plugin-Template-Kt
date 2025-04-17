package com.bryanvalc.pluginTemplateKt.localization

import kotlinx.serialization.Serializable

@Serializable
data class LanguagePack(
    val welcome: String
)