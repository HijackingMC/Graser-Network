package com.grasernetwork.lobby.cosmetic.morph;

public class MorphData
{
	private MorphList _morph;
	
	public MorphData(MorphList morphType)
	{
		_morph = morphType;
	}
	
	public MorphList getMorphType()
	{
		return _morph;
	}
}
