package com.grasernetwork.lobby.protection;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ProtectionComponent implements Listener
{
	private JavaPlugin _plugin;
	
	public ProtectionComponent(JavaPlugin plugin)
	{
		_plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public JavaPlugin getPlugin()
	{
		return _plugin;
	}
}
