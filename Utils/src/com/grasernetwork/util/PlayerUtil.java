package com.grasernetwork.util;

import com.google.gson.JsonObject;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.Packet;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;

import net.minecraft.server.v1_9_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.grasernetwork.util.json.JsonBuilder;
import org.json.simple.JSONObject;

public class PlayerUtil
{

	public static void message(Player receiver, String message, String[] keys, ChatType type)
	{
		if(receiver == null)
			return;

		message = type.getColour() + JSONObject.escape(message);
		for (String s : keys) {
			message = message.replaceFirst("%s", type.getKeyColour() + s + type.getColour());
		}
		message = type.getPrefix() + message;
//		String json = "{\"text\":\"" + JSONObject.escape(message) + "\"}";
		String json = new JsonBuilder(message).toString();
		try
		{
			IChatBaseComponent baseComponent = IChatBaseComponent.ChatSerializer.a(json);
			((CraftPlayer) receiver).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(baseComponent));
		}
		catch (Exception e)
		{
			System.out.println("Malformed JSON");
			System.out.println(json);
		}
	}

	public static void message(Player receiver, String message, ChatType type)
	{
		if(receiver == null)
			return;

		message = type.getPrefix() + message;
		String json = new JsonBuilder(message).toString();
		try
		{
			IChatBaseComponent baseComponent = IChatBaseComponent.ChatSerializer.a(json);
			((CraftPlayer) receiver).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(baseComponent));
		}
		catch (Exception e)
		{
			System.out.println("Malformed JSON");
			System.out.println(json);
		}
	}

	public static void message(Player receiver, String message)
	{
		if(receiver == null)
			return;

		String json = new JsonBuilder(message).toString();
		try
		{
			IChatBaseComponent baseComponent = IChatBaseComponent.ChatSerializer.a(json);
			((CraftPlayer) receiver).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(baseComponent));
		}
		catch (Exception e)
		{
			System.out.println("Malformed JSON");
			System.out.println(json);
		}
	}
	
	public static void messageJson(Player receiver, JsonBuilder json)
	{
		if(receiver == null)
			return;
		try
		{
			IChatBaseComponent baseComponent = IChatBaseComponent.ChatSerializer.a(json.toString());
			((CraftPlayer) receiver).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(baseComponent));
		}
		catch (Exception e)
		{
			System.out.println("Malformed JSON");
			System.out.println(json);
		}
	}

	public static void sendPacket(Player player, Packet... packets)
	{
		PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
		for (Packet packet : packets)
			playerConnection.sendPacket(packet);
	}
}
