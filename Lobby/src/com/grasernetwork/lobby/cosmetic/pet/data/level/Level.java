package com.grasernetwork.lobby.cosmetic.pet.data.level;

import com.grasernetwork.util.TextUtil;

public class Level
{
	public static final int MINIMUM_LEVEL = 1;
	public static final int MAXIMUM_LEVEL = 81;
	
	public static int calculateNeededExp(int level)
	{
		int calc = 0;
		if(level == 1)
			calc = 0;
		else
			calc = (level-1) * 50;
		
		return calc;
	}
	
	public static boolean canRankUp(int level, int exp)
	{
		return exp >= calculateNeededExp(level);
	}
	
	public static String getExpBar(String done, String todo, int level, int exp)
	{
		return TextUtil.getBar(done, todo, 0, calculateNeededExp(level+1), "|", 20);
	}
}
