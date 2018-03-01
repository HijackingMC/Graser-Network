package com.grasernetwork.util.capes;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

//import com.grasernetwork.security.CapeType;

public class CapeLoadedEvent extends Event
{
	private Player _player;
	private String _skin;
	private String _signature;
//	private CapeType _type;
	private boolean _save;

	public CapeLoadedEvent(Player player, String skin, String signature, boolean save)
	{
		_player = player;
		_skin = skin;
		_signature = signature;
		_save = save;
	}

	public Player getPlayer()
	{
		return _player;
	}

	public String getSkin()
	{
		return _skin;
	}
	
	public String getSignature()
	{
		return _signature;
	}
	
	public boolean isSaving()
	{
		return _save;
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
