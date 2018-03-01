package com.grasernetwork.lobby.cosmetic.crates.reward.type.eco;

import com.grasernetwork.lobby.cosmetic.crates.reward.Reward;
import com.grasernetwork.lobby.cosmetic.crates.reward.RewardType;

public abstract class IEcoReward extends Reward
{
	private int _amount;
	
	public IEcoReward(RewardType rewardType, int amount, double rarity)
	{
		super(rewardType, null, rarity);
		
		_amount = amount;
	}
	
	public int getAmount()
	{
		return _amount;
	}
}
