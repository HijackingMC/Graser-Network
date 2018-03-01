package com.grasernetwork.game.games.zombies.door;

import org.bukkit.Material;

/**
 * Created by Teddeh on 31/03/2016.
 */
public enum DoorType
{
	WOODEN_FENCE(Material.FENCE),
	ITEM_GATE(Material.IRON_FENCE);

	private Material doorMaterial;

	private DoorType(Material doorMaterial)
	{
		this.doorMaterial = doorMaterial;
	}

	public Material getDoorMaterial()
	{
		return doorMaterial;
	}
}
