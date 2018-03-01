package com.grasernetwork.lobby.cosmetic.hat;

import com.grasernetwork.lobby.cosmetic.misc.RarityType;

public abstract class Hat
{
	private String _name;
	private String _dataURL;
	private RarityType _rarityType;
	private Integer _price;

	public Hat(String name, String dataURL, RarityType rarityType, int price)
	{
		_name = name;
		_dataURL = dataURL;
		_rarityType = rarityType;
		_price = price;
	}

	public String getName()
	{
		return _name;
	}

	public String getDataURL()
	{
		return _dataURL;
	}

	public RarityType getRarityType()
	{
		return _rarityType;
	}
	
	public int getPrice()
	{
		return _price;
	}
}
