package com.bryanvalc.pluginTemplateKt.data

import com.bryanvalc.pluginTemplateKt.commons.config.StorageConfig
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID

object Players : Table(), KoinComponent {

    private val config: StorageConfig by inject() //TODO: not sure if this is the best workaround

    val uuid: Column<UUID> = uuid("uuid")
    val name: Column<String> = varchar("name", 50)

    override val primaryKey = PrimaryKey(uuid)
    override val tableName: String
        get() = config.tablePrefix+"players"
}