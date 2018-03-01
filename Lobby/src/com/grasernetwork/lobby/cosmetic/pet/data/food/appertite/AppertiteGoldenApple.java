package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteSpecial;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;
import org.bukkit.Material;

public class AppertiteGoldenApple extends AppertiteSpecial
{

	public AppertiteGoldenApple(String name, int[] value)
	{
		super(FoodType.GOLDEN_APPLE, name, value, Material.GOLDEN_APPLE, (byte) 0);
	}
}
