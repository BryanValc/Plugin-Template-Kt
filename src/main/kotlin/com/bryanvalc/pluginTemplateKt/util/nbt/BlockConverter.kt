package com.bryanvalc.pluginTemplateKt.util.nbt

import com.saicone.rtag.RtagBlock
import org.bukkit.block.Block
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object BlockConverter: KoinComponent {

    val plugin: JavaPlugin by inject()


    inline fun <reified T> write(tileEntity: Block, serializable: T) {
        val tag = RtagBlock(tileEntity)
        // would use name of class, but if they refactor to another location the reference would be lost
        tag.set(serializable, plugin.name.lowercase(), serializable::class.java.simpleName.lowercase())
        tag.load()
    }

    inline fun <reified T> remove(tileEntity: Block, serializable: T) {
        val tag = RtagBlock(tileEntity)
        // would use name of class, but if they refactor to another location the reference would be lost
        tag.remove(plugin.name.lowercase(), serializable::class.java.simpleName.lowercase())
        tag.load()
    }

    inline fun <reified T> read(tileEntity: Block): T? {
        val tag = RtagBlock(tileEntity)
        return tag.getOptional(plugin.name.lowercase(), T::class.java.simpleName.lowercase()).`as`<T>(T::class.java)
    }

}