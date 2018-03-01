package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteMilk extends Appertite
{

	public AppertiteMilk(String name, int[] value)
	{
		super(FoodType.MILK, name, AppertiteType.FOOD, value, Material.MILK_BUCKET, (byte) 0);
	}
}
