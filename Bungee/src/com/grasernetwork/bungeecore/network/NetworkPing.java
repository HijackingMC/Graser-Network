package com.grasernetwork.bungeecore.network;

import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.plugin.Plugin;

public class NetworkPing
{
	public static final ServerPing.PlayerInfo[] PING_MESSAGE(Plugin plugin)
	{
		return new ServerPing.PlayerInfo[]
			{
				new ServerPing.PlayerInfo(ChatColor.RED + "" + ChatColor.BOLD + "Graser Network", UUID.randomUUID().toString()),
				new ServerPing.PlayerInfo(ChatColor.RED + "" + ChatColor.STRIKETHROUGH + "---------------", UUID.randomUUID().toString()),
				new ServerPing.PlayerInfo(ChatColor.YELLOW + "0 " + ChatColor.DARK_AQUA + "Staff online", UUID.randomUUID().toString()),
				new ServerPing.PlayerInfo(ChatColor.YELLOW + "" +  plugin.getProxy().getOnlineCount() + ChatColor.DARK_AQUA + " Players online", UUID.randomUUID().toString()),
			};
	}
}
