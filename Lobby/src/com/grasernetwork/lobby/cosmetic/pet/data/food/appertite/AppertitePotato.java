package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import org.bukkit.Material;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class AppertitePotato extends Appertite
{

	public AppertitePotato(String name, int[] value)
	{
		super(FoodType.POTATO, name, AppertiteType.FOOD, value, Material.POTATO_ITEM, (byte) 0);
	}
}
