package com.grasernetwork.lobby.cosmetic.particle.type;

import org.bukkit.entity.Player;

import com.grasernetwork.lobby.cosmetic.misc.RarityType;
import com.grasernetwork.lobby.cosmetic.particle.IParticle;
import com.grasernetwork.lobby.cosmetic.particle.Particle;

public class TestParticle extends Particle implements IParticle<Particle>
{
	public TestParticle()
	{
		super("test particle", 1000, RarityType.COMMON, new String[]{"desc1", "desc2"});
	}

	@Override
	public void triggerStill(Player player)
	{
		double x = player.getLocation().getX();
		double y = player.getLocation().getY() + 3.0;
		double z = player.getLocation().getZ();
		player.spawnParticle(org.bukkit.Particle.CLOUD, x, y, z, 3, 0.5, 0, 0.5, 0);
		player.spawnParticle(org.bukkit.Particle.WATER_DROP, x, y, z, 5, 0.5, 0, 0.5, 0);
	}
	
	@Override
	public void triggerMoving(Player player)
	{
		double x = player.getLocation().getX();
		double y = player.getLocation().getY() + 0.5;
		double z = player.getLocation().getZ();
		player.spawnParticle(org.bukkit.Particle.FLAME, x, y, z, 10, 1, 0, 1, 0);
	}
}
