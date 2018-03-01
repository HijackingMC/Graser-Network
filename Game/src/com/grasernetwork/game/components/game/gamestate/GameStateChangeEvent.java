package com.grasernetwork.game.components.game.gamestate;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Teddeh on 09/03/2016.
 */
public class GameStateChangeEvent extends Event
{
	private GameState from;
	private GameState to;

	public GameStateChangeEvent(GameState from, GameState to) {
		this.from = from;
		this.to = to;
	}

	public GameState getFrom()
	{
		return from;
	}

	public GameState getTo()
	{
		return to;
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
