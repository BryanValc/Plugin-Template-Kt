package dev.smartshub.paperFoldKt.common.config

import kotlinx.serialization.Serializable

@Serializable
data class CacheConfig(
    val maxSize: Int,
    val minutesDuration: Int
)