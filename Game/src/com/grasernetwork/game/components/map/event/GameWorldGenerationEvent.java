package com.grasernetwork.game.components.map.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Teddeh on 31/03/2016.
 */
public class GameWorldGenerationEvent extends Event
{
	private String mapName;

	public GameWorldGenerationEvent(String mapName)
	{
		this.mapName = mapName;
	}

	public String getMapName()
	{
		return mapName;
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
