package com.bryanvalc.pluginTemplateKt.commons.config

import kotlinx.serialization.Serializable

@Serializable
data class CacheConfig(
    val maxSize: Int,
    val minutesDuration: Int
)