package com.grasernetwork.lobby.cosmetic.pet;

import com.grasernetwork.lobby.cosmetic.misc.RarityType;

public interface Pet
{
	String getPetName();
	RarityType getRarityType();
	void onSpawn();
	void onDestroy();
}
