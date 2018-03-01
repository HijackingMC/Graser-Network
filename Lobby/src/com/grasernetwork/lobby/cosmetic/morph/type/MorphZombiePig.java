package com.grasernetwork.lobby.cosmetic.morph.type;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.grasernetwork.lobby.cosmetic.morph.Morph;

public class MorphZombiePig extends Morph
{

	public MorphZombiePig()
	{
		super("Zombie Pig", EntityType.PIG_ZOMBIE);
	}

	@Override
	public void onMorph(Player player)
	{
		player.setMaxHealth(20.0);
		player.setHealth(player.getMaxHealth());
	}

	@Override
	public void unMorph(Player player)
	{
		player.setMaxHealth(20.0);
		player.setHealth(player.getMaxHealth());
	}
}
