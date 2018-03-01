package com.grasernetwork.lobby.elements;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.PlayerUtil;

public class StackerManager implements Listener
{
	public StackerManager(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event)
	{
		if (event.isCancelled())
			return;
		
		if (!(event.getRightClicked() instanceof Player))
			return;
		
		Player player = event.getPlayer();
		Player stacked = (Player) event.getRightClicked();
		
		if (stacked.getGameMode() != GameMode.SURVIVAL)
			return;
		
		if (stacked.getVehicle() != null)
			return;
		
		Entity top = player;
		while (top.getPassenger() != null)
			top = top.getPassenger();
		
		PlayerUtil.message(player, "You successfully stacked '%s'.", new String[]{ stacked.getName() }, ChatType.SERVER);
		PlayerUtil.message(stacked, "You were stacked by '%s'.", new String[]{ player.getName() }, ChatType.SERVER);
		
		top.setPassenger(stacked);
	}
	
	@EventHandler
	public void onThrow(PlayerInteractEvent event)
	{
		if (event.isCancelled())
			return;
		
		Player player = event.getPlayer();
		Entity target = player.getPassenger();
		
		if (player.getVehicle() != null)
			return;
		
		if (target == null)
			return;
		
		target.eject();
	}
}
