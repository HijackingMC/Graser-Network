package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteWater extends Appertite
{

	public AppertiteWater(String name, int[] value)
	{
		super(FoodType.WATER, name, AppertiteType.FOOD, value, Material.WATER_BUCKET, (byte) 0);
	}
}
