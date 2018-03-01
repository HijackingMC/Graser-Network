package com.grasernetwork.lobby.cosmetic.morph;

import org.bukkit.entity.Player;

public interface IMorph
{
	void morphPlayer(Player player, Player target, Morph morph);
	void morphPlayerToAll(Player player, Morph morph);
	
	void unMorphPlayer(Player player);
}
