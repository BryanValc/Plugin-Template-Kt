package dev.smartshub.paperFoldKt.util.nbt

import com.saicone.rtag.RtagItem
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ItemConverter: KoinComponent {

    val plugin: JavaPlugin by inject()

    inline fun <reified T> write(item: ItemStack, serializable: T): Boolean {
        val tag = RtagItem(item)
        // would use name of class, but if they refactor to another location the reference would be lost
        val success = tag.set(serializable, plugin.name.lowercase(), serializable::class.java.simpleName.lowercase())
        return success.also {
            if (it) { tag.load() }
        }
    }

    inline fun <reified T> remove(item: ItemStack, serializable: T): Boolean {
        val tag = RtagItem(item)
        // would use name of class, but if they refactor to another location the reference would be lost
        val success = tag.remove(plugin.name.lowercase(), serializable::class.java.simpleName.lowercase())
        return success.also {
            if (it) { tag.load() }
        }
    }

    inline fun <reified T> read(item: ItemStack): T? {
        val tag = RtagItem(item)
        return tag.getOptional(plugin.name.lowercase(), T::class.java.simpleName.lowercase()).`as`<T>(T::class.java)
    }

}