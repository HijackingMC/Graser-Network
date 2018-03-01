package com.grasernetwork.lobby.cosmetic.crates.reward.type.rank;

import com.grasernetwork.lobby.cosmetic.crates.reward.Reward;
import com.grasernetwork.lobby.cosmetic.crates.reward.RewardType;
import com.grasernetwork.core.rank.Rank;

public class RankReward extends Reward implements IRankReward
{
	public RankReward(String rankName, double rarity)
	{
		super(RewardType.RANK, rankName, rarity);
	}

	@Override
	public Rank getRank()
	{
		return Rank.valueOf(getRewardName().toUpperCase());
	}
}
