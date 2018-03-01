package com.grasernetwork.lobby.cosmetic.crates;

import com.grasernetwork.lobby.cosmetic.crates.reward.Reward;
import com.grasernetwork.lobby.cosmetic.crates.reward.type.cosmetic.GadgetReward;
import com.grasernetwork.lobby.cosmetic.crates.reward.type.cosmetic.HatReward;
import com.grasernetwork.lobby.cosmetic.crates.reward.type.cosmetic.MorphReward;
import com.grasernetwork.lobby.cosmetic.crates.reward.type.cosmetic.PetReward;
import com.grasernetwork.lobby.cosmetic.crates.reward.type.eco.CrystalsReward;

public enum CrateRating
{
	UNRATED(
			new Reward[]
				{
					//Hats (30.0)
					new HatReward(1, 8.0),
					new HatReward(2, 8.0),
					new HatReward(3, 7.0),
					new HatReward(4, 7.0),
					
					//Gadgets (20.0)
					new GadgetReward(1, 6.0),
					new GadgetReward(2, 5.0),
					new GadgetReward(3, 5.0),
					new GadgetReward(4, 4.0),
					
					//morph (15.0)
					new MorphReward(1, 6.0),
					new MorphReward(2, 5.0),
					new MorphReward(2, 4.0),
					
					//Particles (0.0)
					//null
					
					//Pets (10.0)
					new PetReward(1, 5.0),
					new PetReward(2, 5.0),
					
					//Crystals (25.0)
					new CrystalsReward(5000, 15.0),
					new CrystalsReward(7500, 10.0),
				}
	),
	
	COMMON(
			new Reward[]
				{
					
				}
	),
	
	RARE(
			new Reward[]
				{
					
				}
	),
	
	EPIC(
			new Reward[]
				{
					
				}
	),
	
	LEGENDARY(
			new Reward[]
				{
					
				}
	);
	
	private Reward[] _availableRewards;
	
	CrateRating(Reward[] rewards)
	{
		_availableRewards = rewards;
	}
	
	public Reward[] getAvailableRewards()
	{
		return _availableRewards;
	}
}
