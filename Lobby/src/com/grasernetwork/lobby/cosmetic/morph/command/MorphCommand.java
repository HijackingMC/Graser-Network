package com.grasernetwork.lobby.cosmetic.morph.command;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.lobby.cosmetic.morph.Morph;
import com.grasernetwork.lobby.cosmetic.morph.MorphManager;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.C;

public class MorphCommand extends CommandBase
{
	private MorphManager _morphManager;
	
	public MorphCommand(MorphManager morphManager)
	{
		super("morph", new String[]{"disguise", "d"}, Rank.ALL, "/morph <MOB TYPE>", "Disguise yourself as a mob type.");
		
		_morphManager = morphManager;
	}
	
	@Override
	public void execute(Player sender, String[] args)
	{
		Morph morph = _morphManager.getMorphFromType(EntityType.DROPPED_ITEM);
		_morphManager.morphPlayerToAll(sender, morph);
		sender.sendMessage("Morphed into a zombie [" + C.Red + "IN DEV" + C.Reset + "]");
	}
}
