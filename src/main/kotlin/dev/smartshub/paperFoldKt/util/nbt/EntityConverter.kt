package dev.smartshub.paperFoldKt.util.nbt

import com.saicone.rtag.RtagEntity
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object EntityConverter: KoinComponent {

    val plugin: JavaPlugin by inject()

    inline fun <reified T> write(entity: Entity, serializable: T): Boolean {
        val tag = RtagEntity(entity)
        // would use name of class, but if they refactor to another location the reference would be lost
        val success = tag.set(serializable, plugin.name.lowercase(), serializable::class.java.simpleName.lowercase())
        return success.also {
            if (it) { tag.load() }
        }
    }

    inline fun <reified T> remove(entity: Entity, serializable: T): Boolean {
        val tag = RtagEntity(entity)
        // would use name of class, but if they refactor to another location the reference would be lost
        val success = tag.remove(plugin.name.lowercase(), serializable::class.java.simpleName.lowercase())
        return success.also {
            if (it) { tag.load() }
        }
    }

    inline fun <reified T> read(entity: Entity): T? {
        val tag = RtagEntity(entity)
        return tag.getOptional(plugin.name.lowercase(), T::class.java.simpleName.lowercase()).`as`<T>(T::class.java)
    }

}