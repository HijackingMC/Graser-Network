package com.grasernetwork.game.components.game.countdown;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.util.ServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.logging.Level;

/**
 * Created by Teddeh on 08/03/2016.
 */
public class Countdown
{
	private GamePlugin plugin;
	private int tick, givenTick;
	private int[] modulers;
	private String[] messages;

	private BukkitTask task;

	public Countdown(GamePlugin plugin, int ticks, int[] modulers, String[] messages)
	{
		this.plugin = plugin;
		this.tick = ticks;
		this.givenTick = ticks;
		this.modulers = modulers;
		this.messages = messages;
	}

	public void start()
	{
		if(task != null)
		{
			ServerUtil.Log(Level.INFO, "Failed to start countdown, task already in progress!");
			return;
		}

		task = new BukkitRunnable()
		{
			public void run()
			{
				for(Integer i : modulers)
				{
					if(i != tick)
						continue;

					Bukkit.broadcastMessage(String.format(messages[0], tick));
					CountdownEvent event = new CountdownEvent(tick, false);
					Bukkit.getPluginManager().callEvent(event);
					break;
				}

				if(tick <= 0)
				{
					Bukkit.broadcastMessage(messages[1]);
					CountdownEvent event = new CountdownEvent(tick, true);
					Bukkit.getPluginManager().callEvent(event);
					this.cancel();
					return;
				}

				tick--;
			}
		}.runTaskTimer(plugin, 0, 20);
	}

	public void stop(boolean resetTimer)
	{
		if(task == null)
			return;

		task.cancel();
		task = null;

		if(resetTimer)
		{
			tick = givenTick;
		}
	}
}
