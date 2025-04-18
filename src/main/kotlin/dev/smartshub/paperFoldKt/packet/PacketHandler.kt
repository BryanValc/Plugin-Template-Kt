package dev.smartshub.paperFoldKt.packet

import io.netty.channel.Channel
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPipeline
import net.minecraft.network.protocol.game.ServerboundInteractPacket
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

// This class is used to inject a packet handler into the player's channel pipeline.
class PacketHandler {

     /*  This method injects a packet handler into the player's channel pipeline.
      *  You can use the inject method in the player join for example, basically inject when u need
      *  to intercept packets. In some cases will be on a command execution, or other events.
      *  For large logic u should create ur own ChannelDuplexHandler and use it here.
     */
    fun inject(player: Player) {
        val channelHandler: ChannelDuplexHandler = object : ChannelDuplexHandler() {
            @Throws(Exception::class)
            override fun channelRead(ctx: ChannelHandlerContext, rawPacket: Any) {
                // Example: intercepting a specific packet
                if (rawPacket is ServerboundInteractPacket) {
                    val packet: ServerboundInteractPacket = rawPacket as ServerboundInteractPacket
                    // ...
                }
                // DO NOT DELETE THIS LINE
                super.channelRead(ctx, rawPacket)
            }
        }
        val pipeline: ChannelPipeline = (player as CraftPlayer).getHandle().connection.connection.channel.pipeline()
        pipeline.addBefore("packet_handler", player.name, channelHandler)
    }

    // The stop method removes the packet handler from the player's channel pipeline.
    fun stop(player: Player) {
        val channel: Channel = (player as CraftPlayer).getHandle().connection.connection.channel
        channel.eventLoop().submit<Any?> {
            channel.pipeline().remove(player.name)
            null
        }
    }

}