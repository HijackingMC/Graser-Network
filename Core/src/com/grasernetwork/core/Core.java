package com.grasernetwork.core;

import java.net.InetAddress;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import com.grasernetwork.common.ServerType;
import com.grasernetwork.common.jedis.JedisServer;

public abstract class Core extends JavaPlugin
{
	private CoreManager _manager;
	private JedisServer _jedisServer;
	private ServerType type = ServerType.LOBBY;
	
	public void onEnable()
	{
		Bukkit.getLogger().info("Core working");
		getConfig().options().copyDefaults(true);
		saveConfig();

		_jedisServer = new JedisServer("localhost");
		_manager = new CoreManager(this);
		type = ServerType.valueOf(getConfig().getString("serverType")) == null ? ServerType.LOBBY : ServerType.valueOf(getConfig().getString("serverType"));
		while (!_jedisServer.isConnected())
		{
			if (System.currentTimeMillis() % 1000 == 0)
				System.out.println("Connecting to redis");
		}

		new Thread(() -> _jedisServer.subscribe(new ServerSubscriber(this),
				"bungee", "global")).start();

		// GETNAME;ip;port;type
		try
		{

			Server server = getServer();
			String ip = InetAddress.getLocalHost().getHostAddress();

			int port = server.getPort();
			_jedisServer.publish("bungee", "GETNAME;" + ip + ";" + port + ";" + type.name());

		} catch (Exception e)
		{
			e.printStackTrace();
			_manager.shutdown(true);
		}

		enable();
	}
	
	public abstract void enable();

	public CoreManager getManager()
	{
		return _manager;
	}

	public JedisServer getJedisServer()
	{
		return _jedisServer;
	}
}
