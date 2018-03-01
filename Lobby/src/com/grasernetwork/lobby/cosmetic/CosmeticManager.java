package com.grasernetwork.lobby.cosmetic;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.lobby.cosmetic.gadget.GadgetManager;
import com.grasernetwork.lobby.cosmetic.hat.HatManager;
import com.grasernetwork.lobby.cosmetic.morph.MorphManager;
import com.grasernetwork.lobby.cosmetic.pet.PetManager;
import com.grasernetwork.core.profile.ProfileManager;

public class CosmeticManager
{
	private GadgetManager _gadgetManager;
	private HatManager _hatManager;
	private MorphManager _morphManager;
	private PetManager _petManager;

	public CosmeticManager(CoreManager manager, ProfileManager profileManager)
	{
		_gadgetManager = new GadgetManager(manager);
		_hatManager = new HatManager(manager);
		_morphManager = new MorphManager(manager.getPlugin(), profileManager);
		_petManager = new PetManager(manager.getPlugin(), profileManager);
	}

	public GadgetManager getGadgetManager()
	{
		return _gadgetManager;
	}
	
	public HatManager getHatManager()
	{
		return _hatManager;
	}
	
	public MorphManager getMorphManager()
	{
		return _morphManager;
	}
	
	public PetManager getPetManager()
	{
		return _petManager;
	}
}
