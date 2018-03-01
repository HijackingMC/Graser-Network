package com.grasernetwork.lobby.cosmetic.crates.reward.type.cosmetic;

import com.grasernetwork.lobby.cosmetic.crates.reward.Reward;
import com.grasernetwork.lobby.cosmetic.crates.reward.RewardType;

public class PetReward extends Reward implements ICosmeticReward
{
	public PetReward(int rewardId, double rarity)
	{
		super(RewardType.PET, rewardId, rarity);
	}

	@Override
	public int getCosmeticId()
	{
		return getRewardId();
	}
}
