package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteSpecial;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteInstantHealth extends AppertiteSpecial
{

	public AppertiteInstantHealth(String name, int[] value)
	{
		super(FoodType.INSTANT_HEALTH_POTION, name, value, Material.POTION, (byte) 8261);
	}
}
