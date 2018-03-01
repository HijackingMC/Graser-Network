package com.grasernetwork.lobby.cosmetic.crates.reward.type.cosmetic;

import com.grasernetwork.lobby.cosmetic.crates.reward.Reward;
import com.grasernetwork.lobby.cosmetic.crates.reward.RewardType;

public class MorphReward extends Reward implements ICosmeticReward
{
	public MorphReward(int rewardId, double rarity)
	{
		super(RewardType.MORPH, rewardId, rarity);
	}

	@Override
	public int getCosmeticId()
	{
		return getRewardId();
	}
}
