package com.grasernetwork.game.components;

import com.grasernetwork.game.GamePlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * Created by Teddeh on 08/03/2016.
 */
public abstract class Component implements Listener
{
	private GamePlugin plugin;
	private boolean registered;

	public Component(GamePlugin plugin, boolean selfRegister)
	{
		this.plugin = plugin;
		this.registered = false;

		if(selfRegister) register();
	}

	public boolean isRegistered()
	{
		return registered;
	}

	public void register()
	{
		if(registered)
			return;

		Bukkit.getPluginManager().registerEvents(this, plugin);
		registered = true;
	}

	public void unregister()
	{
		if(!registered)
			return;

		HandlerList.unregisterAll(this);
		registered = false;
	}
}
