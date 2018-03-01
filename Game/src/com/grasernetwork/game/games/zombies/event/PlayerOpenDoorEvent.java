package com.grasernetwork.game.games.zombies.event;

import com.grasernetwork.game.games.zombies.door.Door;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Teddeh on 31/03/2016.
 */
public class PlayerOpenDoorEvent extends Event
{
	private Door opened;
	private Player player;

	public PlayerOpenDoorEvent(Player player, Door opened)
	{
		this.player = player;
		this.opened = opened;
	}

	public Door getOpened()
	{
		return opened;
	}

	public Player getPlayer()
	{
		return player;
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
