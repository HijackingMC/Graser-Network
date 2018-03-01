package com.grasernetwork.game.components.map.event;

import com.grasernetwork.game.components.game.Game;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Teddeh on 31/03/2016.
 */
public class MapLoadEvent extends Event
{
	private Game currentGame;
	private String mapName;

	public MapLoadEvent(Game currentGame, String mapName)
	{
		this.currentGame = currentGame;
		this.mapName = mapName;
	}

	public Game getFinishedGame()
	{
		return currentGame;
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
