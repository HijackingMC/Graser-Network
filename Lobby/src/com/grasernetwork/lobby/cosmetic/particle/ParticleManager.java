package com.grasernetwork.lobby.cosmetic.particle;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.grasernetwork.lobby.cosmetic.particle.type.TestParticle;

public class ParticleManager implements Listener
{
	private TestParticle test = null;
	private List<Player> still = new ArrayList<Player>();
	
	public ParticleManager(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
		test = new TestParticle();
		
		new BukkitRunnable()
		{
			
			@Override
			public void run()
			{
				for(Player online : Bukkit.getOnlinePlayers())
				{
					if(still.contains(online))
						test.triggerStill(online);
					else
						test.triggerMoving(online);
				}
			}
		}.runTaskTimer(plugin, 10, 5);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		if ((event.getFrom().getX() == event.getTo().getX()) && (event.getFrom().getY() == event.getTo().getY()) && (event.getFrom().getZ() == event.getTo().getZ()))
		{
			if(!still.contains(event.getPlayer()))
				still.add(event.getPlayer());
			return;
		}
		
		if(still.contains(event.getPlayer()))
			still.remove(event.getPlayer());
	}
}
