package com.grasernetwork.lobby.cosmetic.crates.reward.type.cosmetic;

import com.grasernetwork.lobby.cosmetic.crates.reward.Reward;
import com.grasernetwork.lobby.cosmetic.crates.reward.RewardType;

public class ParticleReward extends Reward implements ICosmeticReward
{
	public ParticleReward(int rewardId, double rarity)
	{
		super(RewardType.HAT, rewardId, rarity);
	}

	@Override
	public int getCosmeticId()
	{
		return getRewardId();
	}
}
