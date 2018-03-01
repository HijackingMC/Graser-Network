package com.grasernetwork.bungeecore.network;

import com.grasernetwork.bungeecore.BungeeCore;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.chat.BaseComponentSerializer;
import net.md_5.bungee.chat.ComponentSerializer;
import net.md_5.bungee.event.EventHandler;

import java.util.HashSet;
import java.util.Random;

public class NetworkManager implements Listener
{
	private BungeeCore _plugin;
	private Random _random;
	private HashSet<String> justJoined;

	public NetworkManager(BungeeCore plugin, Random random)
	{
		_plugin = plugin;
		_random = random;

		justJoined = new HashSet<String>();

		plugin.getProxy().getPluginManager().registerListener(plugin, this);
	}

	@EventHandler
	public void onJoin(PreLoginEvent event)
	{
		int version = event.getConnection().getVersion();
		if (version < NetworkVersion.VERSION)
		{
			event.setCancelled(true);
			event.setCancelReason(NetworkVersion.KICK_REASON);
		}
		if (_plugin.getCurrentMOTD() == NetworkMOTD.SCHEDULED_MAINTENENCE || _plugin.getCurrentMOTD() == NetworkMOTD.UNSCHEDULED_MAINTENENCE)
		{
			//CHECK IF THEy ARE ADMIN+ THEN LET THEM IN
		}
	}

	@EventHandler
	public void onFirstJoin(PostLoginEvent event)
	{
		_plugin.lobbyCleaner.nextConnect(event.getPlayer());

//		for (String server : _plugin.lobbyCleaner.getLobbyInfo().keySet())
//		{
//			String
//			if(_plugin.LOBBY.getPlayers().contains(event.getPlayer()))
//				return;
//
//			event.getPlayer().connect(_plugin.LOBBY);
//			foundLobby = true;
//			break;
//		}
//
//		if(!foundLobby)
//		{
//			System.out.println("Server found: " + server.getName());
//			event.getPlayer().disconnect(new TextComponent("No Lobby servers could be found"));
//		}
	}

//	@EventHandler
//	public void onJoin(ServerConnectEvent event)
//	{
//		System.out.println("RAN2");
//		event.setCancelled(true);
//		if (justJoined.contains(event.getPlayer().getName()))
//		{
//			for (ServerInfo server : _plugin.getProxy().getServers().values())
//			{
//				if (server.getName().toUpperCase().contains("LOBBY"))
//				{
//					System.out.println("Lobby found: " + server.getName());
////				    event.getPlayer().setReconnectServer(_plugin.getProxy().getServers().get(serverName));
////					event.setTarget(_plugin.getProxy().getServerInfo(server.getName()));
////			    	event.setTarget(server);
//
//					event.getPlayer().connect(_plugin.LOBBY);
//					System.out.println("Sending to server: " + server.getName());
//					break;
//				} else
//				{
//					System.out.println("Server found: " + server.getName());
//				}
//			}
//
//			justJoined.remove(event.getPlayer().getName());
//		}
////			event.setCancelled(true);
////			event.getPlayer().connect(_plugin.getProxy().getServers().get(_plugin.getProxy().getServers().keySet().toArray()[_random.nextInt(_plugin.getProxy().getServers().size())]));
//
////			justJoined.remove(event.getPlayer());
////		}
//	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPing(ProxyPingEvent event)
	{
		ServerPing ping = event.getResponse();
		int version = event.getConnection().getVersion();

		ping.setDescription(_plugin.getCurrentMOTD().getMotd());
		ping.setPlayers(new ServerPing.Players(_plugin.getProxy().getOnlineCount() + 1, _plugin.getProxy().getOnlineCount(), NetworkPing.PING_MESSAGE(_plugin)));

		if (version < NetworkVersion.VERSION)
		{
			ping.setVersion(new ServerPing.Protocol(NetworkVersion.PROTOCOL_VERSION, NetworkVersion.VERSION));
		}
		event.setResponse(ping);
	}
}
