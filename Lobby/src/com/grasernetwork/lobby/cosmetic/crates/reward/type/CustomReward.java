package com.grasernetwork.lobby.cosmetic.crates.reward.type;

import com.grasernetwork.lobby.cosmetic.crates.reward.Reward;
import com.grasernetwork.lobby.cosmetic.crates.reward.RewardType;

public class CustomReward extends Reward
{
	public CustomReward(RewardType rewardType, int rewardId, double rarity)
	{
		super(rewardType, rewardId, rarity);
	}
	
	public CustomReward(RewardType rewardType, String rewardName, double rarity)
	{
		super(rewardType, rewardName, rarity);
	}
}
