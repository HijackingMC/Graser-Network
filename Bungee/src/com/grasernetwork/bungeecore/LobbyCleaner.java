package com.grasernetwork.bungeecore;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

/**
 * Created by Teddeh on 18/03/2016.
 */
public class LobbyCleaner
{
	private HashMap<String, Integer> lobbyInfo;
	private HashSet<String> initialize;
	private final int max = 50;
	private BungeeCore bungeeCore;

	public LobbyCleaner(BungeeCore bungeeCore)
	{
		this.bungeeCore = bungeeCore;

		lobbyInfo = new HashMap<String, Integer>();
		initialize = new HashSet<String>();
	}

	public HashMap<String, Integer> getLobbyInfo()
	{
		return lobbyInfo;
	}

	public void addLobbyServer(String lobby)
	{
		if(lobbyInfo.containsKey(lobby))
			return;

		lobbyInfo.put(lobby, 0);
	}

	public void clean()
	{

	}

	/**
	 * Check if the given Lobby has room for another player to join
	 * Will return False if the given Lobby does not exist.
	 *
	 * @param lobby
	 * @return
	 */
	public boolean isRoom(String lobby)
	{
		if (!lobbyInfo.containsKey(lobby)) return false;

		return (lobbyInfo.get(lobby) < max);
	}

	/**
	 * Send the given player to the next available Lobby
	 * If no Lobbys are available, one will be created.
	 *
	 * @param player
	 */
	public void nextConnect(ProxiedPlayer player)
	{
		if (lobbyInfo.size() <= 0) return;

		for (Map.Entry<String, Integer> server : lobbyInfo.entrySet())
		{
			if (!isRoom(server.getKey())) continue;

			ServerInfo serverInfo = bungeeCore.getProxy().getServerInfo(server.getKey());
			if (serverInfo == null) continue;

			player.connect(serverInfo);
			return;
		}

		if (initialize.isEmpty())
		{
			/*
			 * initialize.add(lobbyName);
			 *
			 * copyFiles(templateLobby, toPath)
			 *
			 * Wait
			 * Change server port
			 * bungeeCore.createServer(name, ip, port);
			 *
			 * initialize.remove(lobbyName);
			 */
			System.out.println("Player sent to random lobby, loading new Lobby server.");

			String selected = null;
			for (String server : lobbyInfo.keySet())
			{
				if (selected == null)
				{
					selected = server;
				}

				if (lobbyInfo.get(server) < lobbyInfo.get(selected)) selected = server;
			}

			player.connect(bungeeCore.getProxy().getServers().get(selected));
		}
	}
}
