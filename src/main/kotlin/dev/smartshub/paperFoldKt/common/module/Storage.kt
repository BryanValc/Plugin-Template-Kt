package dev.smartshub.paperFoldKt.common.module

import dev.smartshub.paperFoldKt.common.config.StorageConfig
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

class Storage(
    private val config: StorageConfig,
    private val storageFolder: File,
    private val tables: List<Table>
) {

    fun setup(): Database? {

        val storageMethod = config.method

        var database: Database

        when(storageMethod) {
            "sqlite" ->
                database = Database.Companion.connect("jdbc:sqlite:${storageFolder}/${config.database}",
                    driver = "org.sqlite.JDBC")
            "mariadb" ->
                database = Database.Companion.connect("jdbc:mariadb://${config.address}/${config.database}?${config.args}",
                    driver = "org.mariadb.jdbc.Driver",
                    user = config.username,
                    password = config.password)
            "postgresql" ->
                database = Database.Companion.connect(
                    url = "jdbc:postgresql://${config.address}/${config.database}?${config.args}",
                    driver = "org.postgresql.Driver",
                    user = config.username,
                    password = config.password
                )
            "h2" -> {
                val h2Dir = storageFolder.resolve(config.address)
                if(!h2Dir.exists()) {
                    h2Dir.mkdirs()
                }
                val h2Path = storageFolder.resolve(config.address).resolve(config.database).absolutePath
                database = Database.Companion.connect(
                    url = "jdbc:h2:file:$h2Path",
                    driver = "org.h2.Driver",
                    user = config.username,
                    password = config.password
                )
            }
            else -> {
                return null
            }
        }

        // Beware PRAGMA will be a bitch for SQLite
        transaction(database) {
            SchemaUtils.create(*tables.toTypedArray())

            val statements = MigrationUtils.statementsRequiredForDatabaseMigration(*tables.toTypedArray())
            if (statements.isNotEmpty()) {
                // Execute migrations
                execInBatch(statements)
            }
        }

        return database

    }

}