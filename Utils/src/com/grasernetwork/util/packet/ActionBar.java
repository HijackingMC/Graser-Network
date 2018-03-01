package com.grasernetwork.util.packet;

import com.grasernetwork.util.PlayerUtil;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import org.bukkit.entity.Player;

/**
 * Created by Teddeh on 03/04/2016.
 */
public class ActionBar
{
	public static void playActionBar(Player player, String message)
	{
		IChatBaseComponent str = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat(str, (byte) 2);
		PlayerUtil.sendPacket(player, packet);
	}
}
