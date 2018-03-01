package com.grasernetwork.game.components.player.inventory;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.Component;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Teddeh on 10/03/2016.
 */
public class InventoryClickComponent extends Component
{
	private boolean allow;
	private ItemStack[] allowItems;
	private Material[] allowTypes;
	private Inventory[] allowInventories;

	public InventoryClickComponent(GamePlugin plugin, boolean selfRegister, boolean allow, ItemStack... allowItems)
	{
		super(plugin, selfRegister);

		this.allow = allow;
		this.allowItems = allowItems;

		this.allowTypes = null;
	}

	public InventoryClickComponent(GamePlugin plugin, boolean selfRegister, boolean allow, Material... allowTypes)
	{
		super(plugin, selfRegister);

		this.allow = allow;
		this.allowTypes = allowTypes;

		this.allowItems = null;
	}

	public InventoryClickComponent(GamePlugin plugin, boolean selfRegister, boolean allow, Inventory... allowInventories)
	{
		super(plugin, selfRegister);

		this.allow = allow;
		this.allowInventories = allowInventories;

		this.allowTypes = null;
		this.allowItems = null;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		if(event.getCurrentItem() == null)
			return;

		if(event.getCurrentItem().getType() == Material.AIR)
			return;


		boolean found = false;
		if(allowItems != null)
		{
			for(ItemStack item : allowItems)
			{
				if (!event.getCurrentItem().equals(item)) continue;

				found = true;
				event.setCancelled(!allow);
				break;
			}
		}

		if(allowTypes != null)
		{
			for(Material item : allowTypes)
			{
				if(event.getCurrentItem().getType() != item)
					continue;

				found = true;
				event.setCancelled(!allow);
				break;
			}
		}

		if(allowInventories != null)
		{
			for(Inventory inv : allowInventories)
			{
				if(!event.getInventory().equals(inv))
					continue;

				found = true;
				event.setCancelled(!allow);
				break;
			}
		}

		if(!found)
			event.setCancelled(allow);
	}
}
