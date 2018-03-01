package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteRawBeef extends Appertite
{

	public AppertiteRawBeef(String name, int[] value)
	{
		super(FoodType.RAW_BEEF, name, AppertiteType.FOOD, value, Material.RAW_BEEF, (byte) 0);
	}
}
