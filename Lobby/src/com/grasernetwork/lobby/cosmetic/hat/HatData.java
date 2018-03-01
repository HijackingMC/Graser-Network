package com.grasernetwork.lobby.cosmetic.hat;

public class HatData
{
	private HatList _hat;
	
	public HatData(HatList hatType)
	{
		_hat = hatType;
	}
	
	public HatList getHatType()
	{
		return _hat;
	}
}
