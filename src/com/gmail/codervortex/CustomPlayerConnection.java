package com.gmail.codervortex;

import java.io.IOException;

import net.minecraft.server.v1_7_R4.MinecraftServer;
import net.minecraft.server.v1_7_R4.PacketDataSerializer;
import net.minecraft.server.v1_7_R4.PacketPlayInCustomPayload;
import net.minecraft.server.v1_7_R4.PlayerConnection;
import net.minecraft.util.io.netty.buffer.Unpooled;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.gmail.codervortex.MessageGUI.MessageType;

public class CustomPlayerConnection extends PlayerConnection
{

    private PlayerConnection con;
    private MessageGUI a;
    public CustomPlayerConnection(Player p, PlayerConnection con, MessageGUI gui)
    {
        super(MinecraftServer.getServer(), ((CraftPlayer) p).getHandle().playerConnection.networkManager, ((CraftPlayer) p).getHandle());
        this.con = con;
        this.a = gui;
    }

    @Override
    public void a(PacketPlayInCustomPayload p)
    {
        if (p.c().equalsIgnoreCase("MC|AdvCdm") && a.type == MessageType.COMMAND_BLOCK) {
            PacketDataSerializer s = new PacketDataSerializer(Unpooled.wrappedBuffer(p.e()));
            s.readByte();
            s.readInt();
            s.readInt();
            s.readInt();
            String msg = null;
            try
            {
                msg = s.c(s.readableBytes());
            } 
            catch (IOException e)
            {
                e.printStackTrace();
            }
            a.callback(msg);
        }
    }
    
    public void restore()
    {
        player.playerConnection = con;
        player.playerConnection.networkManager.a(con);
    }
}
