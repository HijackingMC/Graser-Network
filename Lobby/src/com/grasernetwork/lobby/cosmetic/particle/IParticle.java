package com.grasernetwork.lobby.cosmetic.particle;

import org.bukkit.entity.Player;

public interface IParticle<Particle>
{
	void triggerStill(Player player);
	void triggerMoving(Player player);
}
