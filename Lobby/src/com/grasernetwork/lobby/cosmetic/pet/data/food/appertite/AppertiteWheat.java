package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteWheat extends Appertite
{

	public AppertiteWheat(String name, int[] value)
	{
		super(FoodType.WHEAT, name, AppertiteType.FOOD, value, Material.WHEAT, (byte) 0);
	}
}
