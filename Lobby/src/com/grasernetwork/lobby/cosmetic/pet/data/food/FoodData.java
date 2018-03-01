package com.grasernetwork.lobby.cosmetic.pet.data.food;

import com.grasernetwork.lobby.cosmetic.pet.PetType;

public class FoodData
{
	private PetType _petType;
	
	private int _foodLevel = 0;
	private int _thurstLevel = 0;
	
	public FoodData(PetType petType, int foodLevel, int thurstLevel)
	{
		_petType = petType;
		
		_foodLevel = foodLevel;
		_thurstLevel = thurstLevel;
	}
	
	public PetType getPetType()
	{
		return _petType;
	}
	
	public int getFoodLevel()
	{
		return _foodLevel;
	}
	
	public int getThurstLevel()
	{
		return _thurstLevel;
	}
	
	public void setFoodLevel(int i)
	{
		_foodLevel = i;
	}
	
	public void setThurstLevel(int i)
	{
		_thurstLevel = i;
	}
	
	public void addFoodLevel(int i)
	{
		_foodLevel = (_foodLevel + i < 0 ? 0 : _foodLevel + i);
	}
	
	public void addThurstLevel(int i)
	{
		_thurstLevel = (_thurstLevel + i < 0 ? 0 : _thurstLevel + i);
	}
}
