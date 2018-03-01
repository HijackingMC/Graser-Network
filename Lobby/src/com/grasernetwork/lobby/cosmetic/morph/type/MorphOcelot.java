package com.grasernetwork.lobby.cosmetic.morph.type;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.grasernetwork.lobby.cosmetic.morph.Morph;

public class MorphOcelot extends Morph
{

	public MorphOcelot()
	{
		super("Ocelot", EntityType.OCELOT);
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
