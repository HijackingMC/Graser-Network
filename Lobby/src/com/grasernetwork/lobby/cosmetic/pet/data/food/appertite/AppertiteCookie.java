package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertiteCookie extends Appertite
{

	public AppertiteCookie(String name, int[] value)
	{
		super(FoodType.COOKIE, name, AppertiteType.FOOD, value, Material.COOKIE, (byte) 0);
	}
}
