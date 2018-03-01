package com.grasernetwork.lobby.cosmetic.pet.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LevelUpgradeEvent extends Event
{
	private Player _player;
	private int _from;
	private int _to;

	public LevelUpgradeEvent(Player player, int from, int to) {
		_player = player;
		_from = from;
		_to = to;
	}

	public Player getPlayer() {
		return _player;
	}
	
	public int getFrom()
	{
		return _from;
	}
	
	public int getTo()
	{
		return _to;
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
