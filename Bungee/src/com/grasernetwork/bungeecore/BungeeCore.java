package com.grasernetwork.bungeecore;

import java.net.InetSocketAddress;
import java.util.Random;

import net.md_5.bungee.api.ReconnectHandler;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import com.grasernetwork.bungeecore.network.NetworkMOTD;
import com.grasernetwork.bungeecore.network.NetworkManager;
import com.grasernetwork.common.jedis.JedisServer;

public class BungeeCore extends Plugin implements Listener
{
	private JedisServer _jedisServer;
	private Random _random;
	private NetworkMOTD _currentMotd = NetworkMOTD.NORMAL;
	public LobbyCleaner lobbyCleaner;

	@Override
	public void onEnable()
	{
		_jedisServer = new JedisServer("localhost");
		_random = new Random(System.nanoTime());

		new NetworkManager(this, _random);
		lobbyCleaner = new LobbyCleaner(this);

		deleteServer("lobby");

		new Thread(() ->
		{
			while (!_jedisServer.isConnected())
			{
				if (System.currentTimeMillis() % 1000 == 0)
					System.out.println("Connecting to redis");
			}

			System.out.println("Redis connection complete");
			_jedisServer.subscribe(new BungeeSubscriber(this), "bungee", "global");
		}).start();
	}

	public void createServer(String name, String ip, int port)
	{
		InetSocketAddress socket = new InetSocketAddress(ip, port);
		ServerInfo serverInfo = getProxy().constructServerInfo(name, socket, name, false);
		getProxy().getServers().put(name, serverInfo);

		if(name.toUpperCase().contains("LOBBY"))
		{
			lobbyCleaner.addLobbyServer(name);
		}
	}

	public void deleteServer(String name)
	{
		getProxy().getServers().remove(name);
	}

	public JedisServer getJedis()
	{
		return _jedisServer;
	}

	public NetworkMOTD getCurrentMOTD()
	{
		return _currentMotd;
	}

	public void setMotd(NetworkMOTD motd)
	{
		_currentMotd = motd;
	}
}
