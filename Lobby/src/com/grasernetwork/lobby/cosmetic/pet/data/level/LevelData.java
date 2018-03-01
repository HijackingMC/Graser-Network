package com.grasernetwork.lobby.cosmetic.pet.data.level;

import org.bukkit.Bukkit;

import com.grasernetwork.lobby.cosmetic.pet.event.ExpUpgradeEvent;
import com.grasernetwork.lobby.cosmetic.pet.event.LevelUpgradeEvent;

public class LevelData
{
	private int _prestige = 0;
	private int _level = 0;
	private int _exp = 0;
	
	private String _playerName;
	
	public LevelData(String playerName, int level, int exp)
	{
		_playerName = playerName;
		
		_level = level;
		_exp = exp;
	}
	
	public int getLevel()
	{
		return _level;
	}
	
	public String getFormattedLevel()
	{
		if(String.valueOf(_level).toCharArray().length < 2)
		{
			return "0" + _level;
		}
		
		return "" + _level;
	}
	
	public String getFormattedLevel(int add)
	{
		if(String.valueOf(Integer.valueOf(_level+add)).toCharArray().length < 2)
		{
			return "0" + Integer.valueOf(_level+add);
		}
		
		return "" + Integer.valueOf(_level+add);
	}
	
	public int getExp()
	{
		return _exp;
	}
	
	public int getPrestige()
	{
		return _prestige;
	}
	
	public void addLevel()
	{
		LevelUpgradeEvent event = new LevelUpgradeEvent(Bukkit.getPlayer(_playerName), _level, _level+1);
		Bukkit.getPluginManager().callEvent(event);
		
		_level += 1;
	}
	
	public void addExp(int exp)
	{
		ExpUpgradeEvent event = new ExpUpgradeEvent(Bukkit.getPlayer(_playerName), _exp, _exp+=exp);
		Bukkit.getPluginManager().callEvent(event);
		
		_exp = (_exp + exp < 0 ? 0 : _exp + exp);
	}
	
	public void setLevel(int level)
	{
		LevelUpgradeEvent event = new LevelUpgradeEvent(Bukkit.getPlayer(_playerName), _level, level);
		Bukkit.getPluginManager().callEvent(event);
		
		_level = level;
	}
	
	public void setExp(int exp)
	{
		ExpUpgradeEvent event = new ExpUpgradeEvent(Bukkit.getPlayer(_playerName), _exp, exp);
		Bukkit.getPluginManager().callEvent(event);
		
		_exp = exp;
	}
}
