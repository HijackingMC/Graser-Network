package com.grasernetwork.game.games.zombies.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Teddeh on 31/03/2016.
 */
public class PlayerEnterRoomEvent extends Event
{
	//TODO

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