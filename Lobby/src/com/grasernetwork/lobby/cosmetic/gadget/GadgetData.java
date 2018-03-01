package com.grasernetwork.lobby.cosmetic.gadget;

public class GadgetData
{
	private GadgetList _gadget;
	
	public GadgetData(GadgetList gadgetType)
	{
		_gadget = gadgetType;
	}
	
	public GadgetList getGadgetType()
	{
		return _gadget;
	}
}
