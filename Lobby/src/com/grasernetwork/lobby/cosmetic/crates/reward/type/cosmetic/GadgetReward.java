package com.grasernetwork.lobby.cosmetic.crates.reward.type.cosmetic;

import com.grasernetwork.lobby.cosmetic.crates.reward.Reward;
import com.grasernetwork.lobby.cosmetic.crates.reward.RewardType;

public class GadgetReward extends Reward implements ICosmeticReward
{
	public GadgetReward(int gadgetId, double rarity)
	{
		super(RewardType.GADGET, gadgetId, rarity);
	}

	@Override
	public int getCosmeticId()
	{
		return getRewardId();
	}
}
