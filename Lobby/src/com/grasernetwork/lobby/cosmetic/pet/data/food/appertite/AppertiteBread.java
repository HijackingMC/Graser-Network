package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteBread extends Appertite
{

	public AppertiteBread(String name, int[] value)
	{
		super(FoodType.BREAD, name, AppertiteType.FOOD, value, Material.BREAD, (byte) 0);
	}
}
