package dev.smartshub.paperFoldKt.common.config

import kotlinx.serialization.Serializable

@Serializable
data class StorageConfig(
    val method: String,
    val address: String,
    val database: String,
    val username: String,
    val password: String,
    val tablePrefix: String,
    val args: String
)