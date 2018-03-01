package com.grasernetwork.lobby.elements;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldManager implements Listener
{
	public WorldManager(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
		World world = Bukkit.getWorld("world");
		
		world.setGameRuleValue("doDaylightCycle", "false");
		world.setStorm(false);
		world.setThundering(false);
		world.setTime(6050);
		
		for (Entity entity : world.getEntities())
		{
			if (!(entity instanceof Player))
				entity.remove();
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		Player player = event.getPlayer();
		
		if (player.getGameMode() == GameMode.CREATIVE)
			return;
		
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onLeafDecay(LeavesDecayEvent event)
	{
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event)
	{
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockSpread(BlockSpreadEvent event)
	{
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent event)
	{
		Player player = event.getPlayer();
		
		if (player.getGameMode() == GameMode.CREATIVE)
			return;
		
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event)
	{
		Player player = event.getPlayer();
		
		if (player.getGameMode() == GameMode.CREATIVE)
			return;
		
		event.setCancelled(true);
	}
}
