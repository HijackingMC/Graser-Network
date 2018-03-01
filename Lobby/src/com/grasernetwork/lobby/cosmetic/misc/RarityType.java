package com.grasernetwork.lobby.cosmetic.misc;

import com.grasernetwork.util.C;

public enum RarityType
{
	COMMON("Common", C.WhiteB, 1),
	RARE("Rare", C.BlueB, 2),
	EPIC("Epic", C.DarkPurpleB, 3),
	LEGENDARY("Legendary", C.GoldB, 4);

	private String _name;
	private String _colour;
	private int _id;

	RarityType(String name, String colour, int id)
	{
		_name = name;
		_colour = colour;
		_id = id;
	}

	public String getName()
	{
		return _name;
	}

	public String getColour()
	{
		return _colour;
	}

	public int getId()
	{
		return _id;
	}
}
