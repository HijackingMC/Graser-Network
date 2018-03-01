package com.grasernetwork.lobby.cosmetic.crates;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.grasernetwork.lobby.cosmetic.crates.reward.Reward;

public class CrateData
{
	private Set<Location> _opened = new HashSet<Location>();
	private UUID _playerUUID;
	private CrateRating _rating;
	private CrateState _state;
	private BlockFace _facing;
	private Location _startBlock;
	private Location[] _crates;
	private Location _savedLoc;
	
	public LinkedList<Reward> rewards;
	public LinkedList<ArmorStand> holograms;
	
	public CrateData(Player player, CrateRating rating, CrateState state, Location location, Location[] crates, BlockFace facing, Location savedLocation)
	{
		_playerUUID = player.getUniqueId();
		_rating = rating;
		_startBlock = location;
		_crates = crates;
		_facing = facing;
		_savedLoc = savedLocation;
		_state = state;
		
		rewards = new LinkedList<Reward>();
		holograms = new LinkedList<ArmorStand>();
	}
	
	public UUID getPlayerUUID()
	{
		return _playerUUID;
	}
	
	public Player getPlayer()
	{
		return (Bukkit.getPlayer(_playerUUID) == null ? null : Bukkit.getPlayer(_playerUUID));
	}
	
	public CrateRating getCrateRating()
	{
		return _rating;
	}
	
	public Location getStartLocation()
	{
		return _startBlock;
	}
	
	public Location[] getCrateLocations()
	{
		return _crates;
	}
	
	public BlockFace getFacing()
	{
		return _facing;
	}
	
	public Location getSavedLocation()
	{
		return _savedLoc;
	}
	
	public CrateState getState()
	{
		return _state;
	}
	
	public void setState(CrateState state)
	{
		_state = state;
	}
	
	public void addCrateOpened(Location location)
	{
		_opened.add(location);
	}
	
	public boolean hasOpenedCrate(Location location)
	{
		return _opened.contains(location);
	}
	
	public int getCratesOpened()
	{
		return _opened.size();
	}
	
	public void clear()
	{
		_opened.clear();
		_crates = null;
	}
}
