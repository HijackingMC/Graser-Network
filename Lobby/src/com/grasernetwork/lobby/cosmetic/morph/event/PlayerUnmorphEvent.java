package com.grasernetwork.lobby.cosmetic.morph.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerUnmorphEvent extends Event
{
	private Player _player;

	public PlayerUnmorphEvent(Player player) {
		_player = player;
	}

	public Player getPlayer() {
		return _player;
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
