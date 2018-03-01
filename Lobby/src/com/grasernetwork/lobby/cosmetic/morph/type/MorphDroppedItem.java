package com.grasernetwork.lobby.cosmetic.morph.type;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.grasernetwork.lobby.cosmetic.morph.Morph;

public class MorphDroppedItem extends Morph
{

	public MorphDroppedItem()
	{
		super("Dropped Item", EntityType.DROPPED_ITEM);
	}

	@Override
	public void onMorph(Player player)
	{
		
	}

	@Override
	public void unMorph(Player player)
	{
		
	}

}
