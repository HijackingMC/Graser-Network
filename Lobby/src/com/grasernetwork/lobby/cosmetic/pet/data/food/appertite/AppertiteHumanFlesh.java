package com.grasernetwork.lobby.cosmetic.pet.data.food.appertite;

import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;
import org.bukkit.Material;

public class AppertiteHumanFlesh extends Appertite
{

	public AppertiteHumanFlesh(String name, int[] value)
	{
		super(FoodType.HUMAN_FLESH, name, AppertiteType.FOOD, value, Material.PORK, (byte) 0);
	}
}
