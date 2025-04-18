package dev.smartshub.paperFoldKt.common.module

import dev.smartshub.paperFoldKt.common.config.*
import dev.smartshub.paperFoldKt.common.module.repo.LocalesRepository
import dev.smartshub.paperFoldKt.common.module.repo.StorageRepository
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.koin.dsl.module

val storageModule = module {
    single<Storage> {
        val configuration = get<StorageConfig>() //this will be provided by the platform-specific implementation
        val repository = get<StorageRepository>()
        val tables = get<List<Table>>()
        Storage(configuration, repository.folder, tables)
    }

    single<Database?> {
        get<Storage>().setup()
    }

}

val licenseModule = module {
    single<License> {
        val configuration = get<LicenseConfig>() //this will be provided by the platform-specific implementation
        License(configuration)
    }
}

val langManagerModule = module {
    single<LangManager> {
        val configuration = get<LanguageConfig>()
        val repositories = get<LocalesRepository>()
        val plugin = get<JavaPlugin>()

        val defaultFile = plugin.getResource("locales/es_MX_messages.yml")

        LangManager(repositories.folder, configuration, defaultFile, "yml")
    }
}

val cacheModule = module {
    single<CacheManager> {
        val configuration = get<CacheConfig>()
        CacheManager(configuration)
    }
}

val baseModules = listOf(
    storageModule,
    licenseModule,
    langManagerModule,
    cacheModule
)