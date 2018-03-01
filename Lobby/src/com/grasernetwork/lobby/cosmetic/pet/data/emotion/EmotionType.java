package com.grasernetwork.lobby.cosmetic.pet.data.emotion;

import com.grasernetwork.util.C;

public enum EmotionType
{
	VERY_HAPPY(C.Aqua, "Very Happy"),
	HAPPY(C.Green, "Happy"),
	SAD(C.Yellow, "Sad"),
	ANGRY(C.Gold, "Angry"),
	VERY_ANGRY(C.Red, "Very Angry"),
	DEAD(C.DarkRed, "Dead");
	
	private String _color;
	private String _name;
	
	EmotionType(String color, String name)
	{
		_color = color;
		_name = name;
	}
	
	public String getColor()
	{
		return _color;
	}
	
	public String getName()
	{
		return _name;
	}
}
