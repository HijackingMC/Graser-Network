package com.grasernetwork.game.components.game.countdown;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Teddeh on 08/03/2016.
 */
public class CountdownEvent extends Event
{
	private int tick;
	private boolean finished;

	public CountdownEvent(int tick, boolean finished) {
		this.tick = tick;
		this.finished = finished;
	}

	public int getTick()
	{
		return tick;
	}

	public boolean isFinished()
	{
		return finished;
	}

	private static final HandlerList handlers = new HandlerList();

	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}

	public static HandlerList getHandlerList()
	{
		return handlers;
	}
}
