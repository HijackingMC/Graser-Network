package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteRottenFlesh extends Appertite
{

	public AppertiteRottenFlesh(String name, int[] value)
	{
		super(FoodType.ROTTEN_FLESH, name, AppertiteType.FOOD, value, Material.ROTTEN_FLESH, (byte) 0);
	}
}
