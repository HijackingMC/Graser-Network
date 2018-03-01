package com.grasernetwork.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_9_R1.CraftServer;
import org.bukkit.entity.Player;

import com.grasernetwork.common.jedis.Subscriber;
import com.grasernetwork.util.C;

import net.md_5.bungee.api.ChatColor;

public class ServerSubscriber extends Subscriber
{
	private Core _core;

	public ServerSubscriber(Core core)
	{
		_core = core;
	}

	@Override
	public void onMessage(String channel, String message)
	{
		// CRE;ip;port;type
		// NAME;IP;PORT;SERVER_NAME
		String[] str = message.split(Pattern.quote(";"));

		if (channel.equals("bungee"))
		{
			if (str[0].equalsIgnoreCase("NAME"))
			{
				try
				{
					Server server = _core.getServer();
					String ip = InetAddress.getLocalHost().getHostAddress();

					int port = server.getPort();

					if (ip.equals(str[1]) && Integer.valueOf(str[2]) == port)
					{
						String serverName = str[3];
						CraftServer craftServer = (CraftServer) server;
						craftServer.getServer().getPropertyManager().setProperty("server-name", serverName);

						System.out.println("Server received name: " + server.getServerName());
						_core.getJedisServer().publish("bungee", "CRE;" + ip + ";" + port + ";" + server.getServerName());

					}

				} catch (UnknownHostException e)
				{
					_core.getManager().shutdown(true);
					e.printStackTrace();
				}
			}
		}else if(channel.equals("global")) {
			if(str[0].equals("KICK")) {
				String player = str[1];
				if(Bukkit.getPlayerExact(player) == null) return;
				String kickMessage = str[2];
				for(int i = 3; i < str.length; i++) {
					kickMessage += "\n" + str[i];
				}
				Player plyr = Bukkit.getPlayerExact(player);
				plyr.kickPlayer(ChatColor.translateAlternateColorCodes('&', kickMessage));
			}
		}
	}

}
