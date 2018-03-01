package com.grasernetwork.lobby.npc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public enum NpcList
{
	ARCADE(
			"Arcade", 
			"eyJ0aW1lc3RhbXAiOjE0NTU4MzQwMTk2NzYsInByb2ZpbGVJZCI6IjczZmU0NmE5YTk0MjRkYjQ5YTAxNmIwNGZlN2FjOWNmIiwicHJvZmlsZU5hbWUiOiJUZWRfQWNjMDAyIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85YzgyZGMyZTY0ODdhOTNhZmM5NDcyZDY5ODU1YjkyYWQ3YjFiN2M3YTAxZWUyMjI0Y2VhMmZjMjYyZTFhMCJ9fX0=", 
			"jZjYI8fscJ9I7aees9tsx1ivjsVRrLni12+IdIueXLkUiQeJJ+KmP1h8EISEwxZop5WwUF4wlC1a0CdqYTlq5iWllhFzXFspO/lJSYwCLIfz+u7LJofmCT0cB1lnaexE5EYVHkjshtlvhca04yzu/pEaHC+EzvBNjzwpfv1wMXqO3diHmWM3/lgA6MiUSbz/Lr93KyOf+M5ZtIf2MGPUyPHFutYT0fg7Et2y1NzxI33coVxV1N+iqsNW+ZNBs5v0JTl/2+7ZEtE06+YWwbEOpJG0I1GihzR6FFgiuswjUhXKvN8ztmLRaTRgo4TMx0Ul2ufgqs/6W2+TpDhRaUQYe6s8O4lMoLNOLQS/tiobQ9GRbBJEiWqbSGl/SyQkO6ZK5UM19ALzO67OPlTlQWHd1/v4cH7n15ViAz8mopztg0cOevKI+qjDroDQFK2BETMYb1eg3TYvsVf9UOZNxxyTmS+YevM2AHxiLFN49zcnhMUlXoRAaRDh/9mkbWrWkPAjCE724sA1ZmNZOvDHwQIAf+bXFpog/z6N4a0rdznQ7iXt5pv/ZlSESbkG5uS3e3PYZDP6T+96TEhtE19LeiX3Ok2C4h7MvIxCjhKSmJIDknY7jeEjdWzBDVbmQ1ERL5C2u0ziMxY9BvMbkbVuH21Ela4iSCVVBaaoxk+tNYjhVjQ=",
			new Location(Bukkit.getWorld("world"), -31.5, 65.5, 229.5, (float) -27.5, (float) 6.5),
			null
	);
	
	private String _serverName;
	private String _skinData;
	private String _skinSignature;
	private Location _location;
	private ItemStack _equipment;
	
	NpcList(String serverName, String skinData, String skinSignature, Location location, ItemStack equipment)
	{
		_serverName = serverName;
		_skinData = skinData;
		_skinSignature = skinSignature;
		_location = location;
		_equipment = equipment;
	}
	
	public String getServerName()
	{
		return _serverName;
	}
	
	public String getSkinData()
	{
		return _skinData;
	}
	
	public String getSkinSignature()
	{
		return _skinSignature;
	}
	
	public Location getLocation()
	{
		return _location;
	}
	public ItemStack getEquipment()
	{
		return _equipment;
	}
}
