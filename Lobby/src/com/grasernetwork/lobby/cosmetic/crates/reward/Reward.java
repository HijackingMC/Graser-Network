package com.grasernetwork.lobby.cosmetic.crates.reward;


public abstract class Reward
{
	private RewardType _rewardType;
	private int _rewardId;
	private String _rewardName;
	private double _rarity;
	
	public Reward(RewardType rewardType, int rewardId, double rarity)
	{
		_rewardType = rewardType;
		_rewardId = rewardId;
		_rewardName = null;
		_rarity = rarity;
	}
	
	public Reward(RewardType rewardType, String rewardName, double rarity)
	{
		_rewardType = rewardType;
		_rewardName = rewardName;
		_rewardId = 0;
		_rarity = rarity;
	}
	
	public RewardType getRewardType()
	{
		return _rewardType;
	}
	
	public int getRewardId()
	{
		return _rewardId;
	}
	
	public String getRewardName()
	{
		return _rewardName;
	}
	
	public double getRarity()
	{
		return _rarity;
	}
}
