package com.grasernetwork.lobby.cosmetic.particle;

import com.grasernetwork.lobby.cosmetic.misc.RarityType;

public abstract class Particle
{
	private Integer _price;
	private RarityType _rarityType;
	private String _name;
	private String[] _description;
	
	public Particle(String name, int price, RarityType rarityType, String[] description)
	{
		_name = name;
		_price = price;
		_rarityType = rarityType;
		_description = description;
	}
	
	public Integer getPrice()
	{
		return _price;
	}

	public RarityType getRarityType()
	{
		return _rarityType;
	}

	public String getName()
	{
		return _name;
	}

	public String[] getDescription()
	{
		return _description;
	}
}
