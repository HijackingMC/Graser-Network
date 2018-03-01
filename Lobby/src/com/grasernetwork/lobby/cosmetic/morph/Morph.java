package com.grasernetwork.lobby.cosmetic.morph;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public abstract class Morph
{
	private String _name;
	private EntityType _entityType;
	
	public Morph(String name, EntityType entityType)
	{
		_name = name;
		_entityType = entityType;
	}
	
	public abstract void onMorph(Player player);
	
	public abstract void unMorph(Player player);
	
	public String getName()
	{
		return _name;
	}
	
	public EntityType getEntityType()
	{
		return _entityType;
	}
}
