package com.grasernetwork.lobby.cosmetic.morph.type;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.grasernetwork.lobby.cosmetic.morph.Morph;

public class MorphMagmaCube extends Morph
{
	public MorphMagmaCube()
	{
		super("Magma Cube", EntityType.MAGMA_CUBE);
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
