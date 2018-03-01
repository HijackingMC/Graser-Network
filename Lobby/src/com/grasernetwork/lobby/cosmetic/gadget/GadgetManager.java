package com.grasernetwork.lobby.cosmetic.gadget;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.lobby.cosmetic.gadget.misc.TriggerType;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ItemBuilder;

public class GadgetManager implements Listener
{
	private ConcurrentHashMap<Player, Gadget> _selectedGadget = new ConcurrentHashMap<Player, Gadget>();
	private LinkedList<Gadget> _gadgets;

	private static CoreManager _manager;

	public GadgetManager(CoreManager manager)
	{
		Bukkit.getPluginManager().registerEvents(this, manager.getPlugin());

		_manager = manager;
		_gadgets = new LinkedList<Gadget>();

		for(GadgetList gadget : GadgetList.values())
		{
			Gadget g = gadget.getGadgetClass();
			Bukkit.getPluginManager().registerEvents(g, _manager.getPlugin());
			_gadgets.add(g);
		}
	}

	public static CoreManager getManager()
	{
		return _manager;
	}

	public Collection<Gadget> getGadgets()
	{
		return _gadgets;
	}
	
	public ConcurrentHashMap<Player, Gadget> getGadgetMap()
	{
		return _selectedGadget;
	}

	public void giveGadget(Player player, GadgetList gadgetType)
	{
//		if(_selectedGadget.containsKey(player))
//			_selectedGadget.get(player).unRegister();

		Gadget gadget = gadgetType.getGadgetClass();
//		Bukkit.getPluginManager().registerEvents(gadget, _manager.getPlugin());

		_selectedGadget.put(player, gadget);
		
		ItemStack gadgetItem = new ItemBuilder(gadget.getMaterial())
				.setName(gadget.getRarityType().getColour() + gadget.getName())
				.setLore("", C.White + "&7Desc.")
				.build();

		player.getInventory().setItem(4, gadgetItem);
		player.updateInventory();
	}

	@EventHandler
	public void trigger(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();

		if(!_selectedGadget.containsKey(player))
			return;

		Gadget gadget = _selectedGadget.get(player);

		if(player.getItemInHand() == null)
			return;

		if(gadget.getMaterial() != player.getItemInHand().getType())
			return;

		if(gadget.getTriggerType() != TriggerType.LEFT_CLICK && gadget.getTriggerType() != TriggerType.RIGHT_CLICK
				&& gadget.getTriggerType() != TriggerType.LEFT_RIGHT_CLICK)
			return;

		

		if(gadget.getTriggerType() == TriggerType.LEFT_CLICK)
		{
			if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR)
			{
				gadget.trigger(player);
				return;
			}
		}

		if(gadget.getTriggerType() == TriggerType.RIGHT_CLICK)
		{
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)
			{
				gadget.trigger(player);
				return;
			}
		}

		if(gadget.getTriggerType() == TriggerType.LEFT_RIGHT_CLICK)
		{
			gadget.trigger(player);
		}
	}

	@EventHandler
	public void trigger(PlayerInteractAtEntityEvent event)
	{
		if(event.isCancelled())
			return;

		Player player = event.getPlayer();

		if(!_selectedGadget.containsKey(player))
			return;

		Gadget gadget = _selectedGadget.get(player);

		if(gadget.getTriggerType() != TriggerType.LEFT_CLICK
				&& gadget.getTriggerType() != TriggerType.LEFT_CLICK_ENTITY
				&& gadget.getTriggerType() != TriggerType.LEFT_RIGHT_CLICK_ENTITY)
			return;

		if(player.getItemInHand() == null)
			return;

		if(gadget.getMaterial() != player.getItemInHand().getType())
			return;

		if(gadget.getTriggerType() == TriggerType.LEFT_CLICK)
		{
			gadget.trigger(player);
			return;
		}

		if(gadget.getTriggerType() == TriggerType.LEFT_CLICK_ENTITY)
		{
			gadget.trigger(player);
			return;
		}

		if(gadget.getTriggerType() == TriggerType.LEFT_RIGHT_CLICK_ENTITY)
		{
			gadget.trigger(player);
			return;
		}
	}

	@EventHandler
	public void trigger(EntityDamageByEntityEvent event)
	{
		if(event.isCancelled())
			return;

		if(!(event.getDamager() instanceof Player))
			return;

		Player player = (Player) event.getDamager();

		if(!_selectedGadget.containsKey(player))
			return;

		Gadget gadget = _selectedGadget.get(player);

		if(gadget.getTriggerType() != TriggerType.RIGHT_CLICK
				&& gadget.getTriggerType() != TriggerType.RIGHT_CLICK_ENTITY
				&& gadget.getTriggerType() != TriggerType.LEFT_RIGHT_CLICK_ENTITY)
			return;

		if(player.getItemInHand() == null)
			return;

		if(gadget.getMaterial() != player.getItemInHand().getType())
			return;

		if(gadget.getTriggerType() == TriggerType.RIGHT_CLICK)
		{
			gadget.trigger(player);
			return;
		}

		if(gadget.getTriggerType() == TriggerType.RIGHT_CLICK_ENTITY)
		{
			gadget.trigger(player);
			return;
		}

		if(gadget.getTriggerType() == TriggerType.LEFT_RIGHT_CLICK_ENTITY)
		{
			gadget.trigger(player);
			return;
		}
	}

	@EventHandler
	public void trigger(PlayerToggleSneakEvent event)
	{
		if(event.isCancelled())
			return;

		Player player = event.getPlayer();

		if(player.isSneaking())
			return;

		if(!_selectedGadget.containsKey(player))
			return;

		Gadget gadget = _selectedGadget.get(player);

		if(gadget.getTriggerType() != TriggerType.CROUCH)
			return;

		if(player.getItemInHand() == null)
			return;

		if(gadget.getMaterial() != player.getItemInHand().getType())
			return;

		gadget.trigger(player);
	}

	@EventHandler
	public void trigger(EntityShootBowEvent event)
	{
		if(event.isCancelled())
			return;

		if(!(event.getEntity() instanceof Player))
			return;

		Player player = (Player) event.getEntity();

		if(!_selectedGadget.containsKey(player))
			return;

		Gadget gadget = _selectedGadget.get(player);

		if(gadget.getTriggerType() != TriggerType.BOW)
			return;

		if(player.getItemInHand() == null)
			return;

		if(gadget.getMaterial() != player.getItemInHand().getType())
			return;

		gadget.trigger(player);
		event.setCancelled(true);
	}
}
