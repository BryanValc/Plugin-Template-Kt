package com.bryanvalc.pluginTemplateKt

import com.bryanvalc.pluginTemplateKt.command.ReadData
import com.bryanvalc.pluginTemplateKt.command.Reload
import com.bryanvalc.pluginTemplateKt.command.SetData
import com.bryanvalc.pluginTemplateKt.common.modules.License
import com.bryanvalc.pluginTemplateKt.common.modules.baseModules
import com.bryanvalc.pluginTemplateKt.event.PlayerJoin
import com.bryanvalc.pluginTemplateKt.module.Debug
import com.bryanvalc.pluginTemplateKt.module.Loader
import com.bryanvalc.pluginTemplateKt.module.appModules
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import revxrsal.zapper.ZapperJavaPlugin

class PluginTemplateKt: ZapperJavaPlugin(), KoinComponent  {

    private val license: License by inject()
    private val loader: Loader by inject()
    private val debug: Debug by inject()

    override fun onEnable() {
        val pluginModule = module {
            single<JavaPlugin> {
                this@PluginTemplateKt
            }
        }

        val allModules = mutableListOf<Module>()
        allModules.add(pluginModule)
        allModules.addAll(baseModules)
        allModules.addAll(appModules)

        val finalModules = allModules.toList()

        startKoin {
            modules(finalModules)
        }

        loader.bootstrap()
        // it's up to you if you want to use license system
//        if(!license.validate()){
//            Bukkit.getPluginManager().disablePlugin(this)
//            return
//        }
//        debug.msg("License loaded correctly")

        // bStats, get your own at https://bstats.org/plugin-list
//        val pluginId = -1
//        val metrics = Metrics(this, pluginId)

        // Commands TODO: check Lamp
        Reload().register()
        ReadData().register()
        SetData().register()

        // Events
        server.pluginManager.registerEvents(PlayerJoin(), this)

        // Placeholders
//        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
//            Momentum().register()
//        }

    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
