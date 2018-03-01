package com.grasernetwork.lobby.cosmetic.gadget;

import com.grasernetwork.lobby.cosmetic.gadget.type.GadgetChickenBlaster;
import com.grasernetwork.lobby.cosmetic.gadget.type.GadgetPaintball;

public enum GadgetList
{
	PAINTBALL(new GadgetPaintball(), 1),
	CHICKEN_BLASTER(new GadgetChickenBlaster(), 2);

	private Gadget _gadget;
	private int _gadgetId;

	GadgetList(Gadget gadget, int gadgetId)
	{
		_gadget = gadget;
		_gadgetId = gadgetId;
	}

	public Gadget getGadgetClass()
	{
		return _gadget;
	}
	
	public int getGadgetId()
	{
		return _gadgetId;
	}
	
	public static GadgetList getById(int id)
	{
		for(GadgetList gadget : GadgetList.values())
		{
			if(gadget.getGadgetId() != id)
				continue;
			
			return gadget;
		}
		
		return null;
	}
}
