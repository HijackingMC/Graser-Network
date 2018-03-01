package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteClownfish extends Appertite
{

	public AppertiteClownfish(String name, int[] value)
	{
		super(FoodType.CLOWNFISH, name, AppertiteType.FOOD, value, Material.RAW_FISH, (byte) 2);
	}
}
