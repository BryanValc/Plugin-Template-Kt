package dev.smartshub.paperFoldKt

import dev.smartshub.paperFoldKt.command.MainCommand
import dev.smartshub.paperFoldKt.common.module.License
import dev.smartshub.paperFoldKt.common.module.baseModules
import dev.smartshub.paperFoldKt.listener.PlayerJoinListener
import dev.smartshub.paperFoldKt.module.Debug
import dev.smartshub.paperFoldKt.module.Loader
import dev.smartshub.paperFoldKt.module.appModules
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import revxrsal.commands.Lamp
import revxrsal.commands.bukkit.BukkitLamp
import revxrsal.commands.bukkit.actor.BukkitCommandActor
import revxrsal.zapper.ZapperJavaPlugin

class PaperFoldKt: ZapperJavaPlugin(), KoinComponent  {

    private val license: License by inject()
    private val loader: Loader by inject()
    private val debug: Debug by inject()

    override fun onLoad() {
        initModules()
    }

    override fun onEnable() {
        debug.msg { "Enabling plugin template in kotlin!" }

        // Validate license. This is a must; also we recommend to hide into other code
        // parts, here at main class is not a good practice and is easily detectable!
        checkLicense()

        // Load & init command
        loadCommands()

        // Register events, remember that can use MCCoroutine for async tasks
        registerEvents()

        // Hook other plugins, like PlaceholderAPI or FAWE if needed
        hookPlugins()

        // BStats metrics (remember to change the pluginId!!!)
        setupMetrics()
    }

    override fun onDisable() {
       debug.msg { "Disabling plugin template in kotlin!" }
    }

    private fun initModules(){
        val pluginModule = module { single<JavaPlugin> {
                this@PaperFoldKt
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
    }

    private fun loadCommands(){
        val lamp: Lamp<BukkitCommandActor> = BukkitLamp.builder(this)
            .build()
        lamp.register(MainCommand())
    }

    private fun checkLicense(){
        if(!license.validate()){
            Bukkit.getPluginManager().disablePlugin(this)
            return
        }
        debug.msg { "License loaded correctly" }
    }

    private fun registerEvents(){
        server.pluginManager.registerEvents(PlayerJoinListener(), this)
    }

    private fun hookPlugins(){
    // Quick example:
    //  if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) Momentum().register()
    }

    private fun setupMetrics(){
        val pluginId = -1
        Metrics(this, pluginId)
    }

}
