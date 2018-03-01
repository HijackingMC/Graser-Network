package com.grasernetwork.lobby.elements;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SpawnManager
{
	private final Location _spawnLocation = new Location(Bukkit.getWorld("world"), -28, 76, 238);
	
	public Location getSpawnLocation()
	{
		return _spawnLocation;
	}
}
