package com.grasernetwork.game.games.zombies.door;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddeh on 31/03/2016.
 */
public class Door
{
	private final int id, price;
	private final int[] roomId, size;
	private boolean opened = false;
	private DoorType doorType;
	private BlockFace facing;
	private List<Location> doorBlockLocations;

	public Door(int id, int price, int[] roomId, int[] size, BlockFace facing, DoorType doorType)
	{
		this.id = id;
		this.price = price;
		this.roomId = roomId;
		this.size = size;
		this.facing = facing;
		this.doorType = doorType;
		this.doorBlockLocations = new ArrayList<Location>();
	}

	public int getId()
	{
		return id;
	}

	public int getPrice()
	{
		return price;
	}

	public int[] getRoomId()
	{
		return roomId;
	}

	public int[] getSize()
	{
		return size;
	}

	public DoorType getDoorType()
	{
		return doorType;
	}

	public BlockFace getFacing()
	{
		return facing;
	}

	public void setOpened(boolean opened)
	{
		this.opened = opened;
	}

	public boolean isOpen()
	{
		return opened;
	}

	public List<Location> getDoorBlockLocations()
	{
		return doorBlockLocations;
	}

	public void addDoorBlock(Location location)
	{
		doorBlockLocations.add(location);
	}
}
