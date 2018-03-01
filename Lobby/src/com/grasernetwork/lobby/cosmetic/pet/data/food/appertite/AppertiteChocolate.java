package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteChocolate extends Appertite
{

	public AppertiteChocolate(String name, int[] value)
	{
		super(FoodType.CHOCOLATE, name, AppertiteType.FOOD, value, Material.INK_SACK, (byte) 3);
	}
}
