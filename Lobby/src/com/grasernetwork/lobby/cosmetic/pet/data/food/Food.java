package com.grasernetwork.lobby.cosmetic.pet.data.food;

import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;

public class Food
{
	private Appertite[] _appertite;
	
	public Food(Appertite[] appertite)
	{
		_appertite = appertite;
	}
	
	public Appertite[] getAppertites()
	{
		return _appertite;
	}
	
	public int getFoodGeneration(FoodType foodType)
	{
		for(Appertite a : _appertite)
		{
			if(a.getFoodType() != foodType)
				continue;
			
			return a.getValue()[0];
		}
		
		return 0;
	}
	
	public int getThurstGeneration(FoodType foodType)
	{
		for(Appertite a : _appertite)
		{
			if(a.getFoodType() != foodType)
				continue;
			
			return a.getValue()[1];
		}
		
		return 0;
	}
	
	public int getHealth(FoodType foodType)
	{
		for(Appertite a : _appertite)
		{
			if(a.getFoodType() != foodType)
				continue;
			
			return a.getValue()[2];
		}
		
		return 0;
	}
	
	public int getDamage(FoodType foodType)
	{
		for(Appertite a : _appertite)
		{
			if(a.getFoodType() != foodType)
				continue;
			
			return a.getValue()[3];
		}
		
		return 0;
	}
}
