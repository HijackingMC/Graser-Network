package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteCookedSalmon extends Appertite
{

	public AppertiteCookedSalmon(String name, int[] value)
	{
		super(FoodType.COOKED_SALMON, name, AppertiteType.FOOD, value, Material.COOKED_FISH, (byte) 1);
	}
}
