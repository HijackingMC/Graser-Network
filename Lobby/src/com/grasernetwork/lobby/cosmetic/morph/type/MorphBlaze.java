package com.grasernetwork.lobby.cosmetic.morph.type;

import com.grasernetwork.lobby.cosmetic.morph.Morph;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class MorphBlaze extends Morph
{

	public MorphBlaze()
	{
		super("Blaze", EntityType.BLAZE);
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
