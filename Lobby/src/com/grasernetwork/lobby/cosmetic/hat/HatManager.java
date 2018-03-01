package com.grasernetwork.lobby.cosmetic.hat;

import java.util.Collection;
import java.util.LinkedList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.grasernetwork.core.CoreManager;

public class HatManager implements Listener
{
	private LinkedList<Hat> _hats;

	private static CoreManager _manager;

	public HatManager(CoreManager manager)
	{
		Bukkit.getPluginManager().registerEvents(this, manager.getPlugin());

		_manager = manager;
		_hats = new LinkedList<Hat>();

		for(HatList hat : HatList.values())
		{
			Hat h = hat.getHatClass();
			_hats.add(h);
		}
	}

	public static CoreManager getManager()
	{
		return _manager;
	}

	public Collection<Hat> getHat()
	{
		return _hats;
	}

	public void giveHat(Player player, ItemStack item)
	{
		player.getInventory().setHelmet(item);
		player.updateInventory();
	}
}
