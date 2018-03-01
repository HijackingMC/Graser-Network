package com.grasernetwork.lobby.cosmetic.pet.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PetStatusChangeEvent extends Event
{
	private Player _player;

	public PetStatusChangeEvent(Player player) {
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
