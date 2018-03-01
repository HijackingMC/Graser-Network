package com.grasernetwork.lobby.cosmetic;

import java.util.List;

import com.grasernetwork.lobby.cosmetic.gadget.GadgetData;
import com.grasernetwork.lobby.cosmetic.hat.HatData;
import com.grasernetwork.lobby.cosmetic.morph.MorphData;
import com.grasernetwork.lobby.cosmetic.pet.data.PetData;

public class CosmeticData
{
	//private CostumeData _costumeData;
	private List<GadgetData> _gadgetData;
	private List<HatData> _hatData;
	private List<MorphData> _morphData;
	//private ParticleData _particleData
	private List<PetData> _petData;
	
	public CosmeticData(List<GadgetData> gadgetData, List<HatData> hatData, List<MorphData> morphData, List<PetData> petData)
	{
		_gadgetData = gadgetData;
		_hatData = hatData;
		_morphData = morphData;
		_petData = petData;
	}
	
//	public CostumeData getCostumeData()
//	{
//		return _costumeData;
//	}
	
	public List<GadgetData> getGadgetData()
	{
		return _gadgetData;
	}

	public List<HatData> getHatData()
	{
		return _hatData;
	}

	public List<MorphData> getMorphData()
	{
		return _morphData;
	}
	
//	public ParticleData getParticleData()
//	{
//		return _particleData;
//	}

	public List<PetData> getPetData()
	{
		return _petData;
	}
	
	public void addGadget(GadgetData gadgetData)
	{
		_gadgetData.add(gadgetData);
	}
	
	public void addHat(HatData hatData)
	{
		_hatData.add(hatData);
	}
	
	public void addPet(PetData petData)
	{
		_petData.add(petData);
	}
}
