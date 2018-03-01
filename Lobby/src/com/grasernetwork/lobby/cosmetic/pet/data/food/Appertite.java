package com.grasernetwork.lobby.cosmetic.pet.data.food;

import com.grasernetwork.lobby.cosmetic.pet.data.food.AppertiteType;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;
import org.bukkit.Material;

public abstract class Appertite
{
	private FoodType _foodType;
	private String _name;
	private AppertiteType _appertiteType;
	private int[] _value;
	private Material _material;
	private byte _data;
	
	public Appertite(FoodType foodType, String name, AppertiteType appertiteType, int[] value, Material material, byte data)
	{
		_foodType = foodType;
		_name = name;
		_appertiteType = appertiteType;
		_value = value;
		_material = material;
		_data = data;
	}
	
	public FoodType getFoodType()
	{
		return _foodType;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public AppertiteType getAppertiteType()
	{
		return _appertiteType;
	}
	
	public int[] getValue()
	{
		return _value;
	}
	
	public Material getMaterial()
	{
		return _material;
	}
	
	public byte getData()
	{
		return _data;
	}
}
