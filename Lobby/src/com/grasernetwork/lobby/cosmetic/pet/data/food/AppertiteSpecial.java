package com.grasernetwork.lobby.cosmetic.pet.data.food;

import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;
import org.bukkit.Material;

public abstract class AppertiteSpecial extends Appertite
{

	public AppertiteSpecial(FoodType foodType, String name, int[] value, Material material, byte data)
	{
		super(foodType, name, AppertiteType.SPECIAL, value, material, data);
	}
}
