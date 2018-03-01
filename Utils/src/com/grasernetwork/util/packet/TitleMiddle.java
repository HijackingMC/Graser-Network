package com.grasernetwork.util.packet;

import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_9_R1.PlayerConnection;

import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleMiddle 
{
	public static void sendTitle(Player p, String title, String subTitle) 
	{
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + title + "\"}");
		IChatBaseComponent icbc2 = ChatSerializer.a("{\"text\": \"" + subTitle + "\"}");

		PacketPlayOutTitle[] packets = new PacketPlayOutTitle[]
				{
				new PacketPlayOutTitle(EnumTitleAction.TITLE, icbc),
				new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, icbc2),
				new PacketPlayOutTitle(10, 50, 10)
				};

		PlayerConnection c = ((CraftPlayer) p).getHandle().playerConnection;
		for(PacketPlayOutTitle packet : packets)
			c.sendPacket(packet);
	}
	
	public static void sendTitle(Player p, String title, String subTitle, int[] data) 
	{
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + title + "\"}");
		IChatBaseComponent icbc2 = ChatSerializer.a("{\"text\": \"" + subTitle + "\"}");

		PacketPlayOutTitle[] packets = new PacketPlayOutTitle[]
				{
				new PacketPlayOutTitle(EnumTitleAction.TITLE, icbc),
				new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, icbc2),
				new PacketPlayOutTitle(data[0], data[1], data[2])
				};

		PlayerConnection c = ((CraftPlayer) p).getHandle().playerConnection;
		for(PacketPlayOutTitle packet : packets)
			c.sendPacket(packet);
	}
	
	public static void sendTop(Player p, String text)
	{
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + text + "\"}");
		IChatBaseComponent icbc2 = ChatSerializer.a("{\"text\": \" \"}");

		PacketPlayOutTitle[] packets = new PacketPlayOutTitle[]
				{
				new PacketPlayOutTitle(EnumTitleAction.TITLE, icbc),
				new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, icbc2),
				new PacketPlayOutTitle(10, 50, 10)
				};

		PlayerConnection c = ((CraftPlayer) p).getHandle().playerConnection;
		for(PacketPlayOutTitle packet : packets)
			c.sendPacket(packet);
	}
	
	public static void sendBottom(Player p, String text)
	{
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \" \"}");
		IChatBaseComponent icbc2 = ChatSerializer.a("{\"text\": \"" + text + "\"}");

		PacketPlayOutTitle[] packets = new PacketPlayOutTitle[]
				{
				new PacketPlayOutTitle(EnumTitleAction.TITLE, icbc),
				new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, icbc2),
				new PacketPlayOutTitle(10, 50, 10)
				};

		PlayerConnection c = ((CraftPlayer) p).getHandle().playerConnection;
		for(PacketPlayOutTitle packet : packets)
			c.sendPacket(packet);
	}
}