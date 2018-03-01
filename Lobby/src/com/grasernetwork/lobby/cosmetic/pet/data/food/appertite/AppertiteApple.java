package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteApple extends Appertite
{

	public AppertiteApple(String name, int[] value)
	{
		super(FoodType.APPLE, name, AppertiteType.FOOD, value, Material.APPLE, (byte) 0);
	}

}
