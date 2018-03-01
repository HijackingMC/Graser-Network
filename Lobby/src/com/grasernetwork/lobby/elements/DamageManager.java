package com.grasernetwork.lobby.elements;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.java.JavaPlugin;

public class DamageManager implements Listener
{
	private SpawnManager _spawnManager;
	
	public DamageManager(JavaPlugin plugin, SpawnManager spawnManager)
	{
		_spawnManager = spawnManager;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event)
	{
		if (event.getEntity() instanceof Player)
		{
			if (event.getCause() == DamageCause.VOID)
				event.getEntity().teleport(_spawnManager.getSpawnLocation());
			
			event.setCancelled(true);
		}
	}
}