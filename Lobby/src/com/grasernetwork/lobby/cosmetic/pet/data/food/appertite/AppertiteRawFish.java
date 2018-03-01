package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteRawFish extends Appertite
{

	public AppertiteRawFish(String name, int[] value)
	{
		super(FoodType.RAW_FISH, name, AppertiteType.FOOD, value, Material.RAW_FISH, (byte) 0);
	}
}
