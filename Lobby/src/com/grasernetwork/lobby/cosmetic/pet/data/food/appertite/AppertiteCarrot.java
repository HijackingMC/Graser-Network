package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteCarrot extends Appertite
{

	public AppertiteCarrot(String name, int[] value)
	{
		super(FoodType.CARROT, name, AppertiteType.FOOD, value, Material.CARROT_ITEM, (byte) 0);
	}
}
