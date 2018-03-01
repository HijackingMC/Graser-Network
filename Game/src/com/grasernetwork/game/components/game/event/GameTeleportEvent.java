package com.grasernetwork.game.components.game.event;

import com.grasernetwork.game.components.game.Game;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Teddeh on 02/04/2016.
 */
public class GameTeleportEvent extends Event
{
	private Game finished;

	public GameTeleportEvent(Game finished) {
		this.finished = finished;
	}

	public Game getFinishedGame()
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
