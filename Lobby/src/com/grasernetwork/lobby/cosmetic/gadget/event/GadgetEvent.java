package com.grasernetwork.lobby.cosmetic.gadget.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.grasernetwork.lobby.cosmetic.gadget.Gadget;

public class GadgetEvent extends Event implements Cancellable
{
	private Player _player;
	private Gadget _gadget;

	public GadgetEvent(Player player, Gadget gadget) {
		_player = player;
		_gadget = gadget;
	}

	public Player getPlayer() {
		return _player;
	}

	public Gadget getGadget()
	{
		return _gadget;
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

	@Override
	public boolean isCancelled()
	{
		return false;
	}

	@Override
	public void setCancelled(boolean b)
	{
	}
}
