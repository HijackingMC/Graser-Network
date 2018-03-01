package com.grasernetwork.lobby.cosmetic.pet.data.food;

public enum FoodType
{
	HUMAN_FLESH("Human Flesh"),
	RAW_BEEF("Raw Steak"),
	ROTTEN_FLESH("Rotten Flesh"),
	RAW_FISH("Raw Fish"),
	CLOWNFISH("Clownfish"),
	COOKED_SALMON("Cooked Salmon"),
	COOKED_FISH("Cooked Fish"),
	
	POTATO("Potato"),
	CARROT("Carrot"),
	BREAD("Bread"),
	APPLE("Apple"),
	WHEAT("Wheat"),
	COOKIE("Cookie"),
	CHOCOLATE("Chocolate"),
	
	WATER("Water"),
	MILK("Milk"),
	GOLDEN_APPLE("Golden Apple"),
	INSTANT_HEALTH_POTION("Instant Health"),
	INSTANT_DAMAGE_POTION("Instant Damage");
	
	private String _name;
	
	FoodType(String name)
	{
		_name = name;
	}
	
	public String getName()
	{
		return _name;
	}
}
