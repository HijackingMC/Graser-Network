package com.grasernetwork.lobby.elements;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.core.rank.Rank;

public class ConnectionManager implements Listener
{
	private SpawnManager _spawnManager;
	private ProfileManager _profileManager;
	
	public ConnectionManager(JavaPlugin plugin, SpawnManager spawnManager, ProfileManager profileManager)
	{
		_spawnManager = spawnManager;
		_profileManager = profileManager;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		event.setQuitMessage(null);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		Profile profile = _profileManager.getProfile(player);
		Rank rank = profile.rank;
		
		player.teleport(_spawnManager.getSpawnLocation().add(0, 2, 0));
		player.setGameMode(GameMode.SURVIVAL);
		player.setHealth(player.getMaxHealth());
		player.setExp(0);
		player.setFireTicks(0);
		player.setSaturation(20);
		player.setAllowFlight(false);
		event.setJoinMessage(null);
		
		
		//TODO Broadcast when donators join the server
//		Bukkit.broadcastMessage("" + profile.getUUID());
//		Bukkit.broadcastMessage("" + profile.getRank());//try that <-
//		if (rank.hasPermission(Rank.DONOR1))
//		{
//			Bukkit.broadcastMessage(rank.getColour() + rank.getName() + player.getName());
////			Bukkit.broadcastMessage(profile.getRank().getColour() + profile.getRank().getName() + " " + player.getName() + C.Gold + " has joined the server!");
//		}
	}
}