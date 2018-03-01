package com.grasernetwork.lobby.cosmetic.gadget;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.lobby.cosmetic.gadget.misc.TriggerType;
import com.grasernetwork.lobby.cosmetic.misc.RarityType;

public abstract class Gadget implements Listener
{
	public Integer RunnableTask;
	private String _name;
	private Material _material;
	private TriggerType _triggerType;
	private RarityType _rarityType;
	private Integer _price;

	public Gadget(String name, Material material, TriggerType triggerType, RarityType rarityType, int price)
	{
		_name = name;
		_material = material;
		_triggerType = triggerType;
		_rarityType = rarityType;
		_price = price;
	}

	public CoreManager getManager()
	{
		return GadgetManager.getManager();
	}

	public void unRegister()
	{
		HandlerList.unregisterAll(this);
	}

	public abstract void trigger(Player player);

	public String getName()
	{
		return _name;
	}

	public Material getMaterial()
	{
		return _material;
	}

	public TriggerType getTriggerType()
	{
		return _triggerType;
	}

	public RarityType getRarityType()
	{
		return _rarityType;
	}
	
	public int getPrice()
	{
		return _price;
	}
}
