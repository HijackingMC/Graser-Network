package com.grasernetwork.core.heads.gui.decoration;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.grasernetwork.core.heads.HeadType;
import com.grasernetwork.core.heads.gui.HeadPortalMenu;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ItemBuilder;
import com.grasernetwork.util.inventory.InventoryClickType;
import com.grasernetwork.util.inventory.Menu;
import com.grasernetwork.util.inventory.MenuAPI;
import com.grasernetwork.util.inventory.MenuItem;

public class MenuTwo extends Menu
{
	public MenuTwo(Player player)
	{
		super("Decorations Menu", 5);
		int count = 0;
		
		for (final HeadType head : HeadType.values())
		{
			if (head.getCategory().equalsIgnoreCase("DECORATION 2"))
			{
				final ItemBuilder headItem = new ItemBuilder(Material.SKULL_ITEM).setName(head.getName()).setDurability((short)3);
				
				this.addMenuItem(new MenuItem(headItem.setCustomSkull(head.getURL()))
				{
					@Override
					public void onClick(Player player, InventoryClickType clickType)
					{
						player.getInventory().addItem(headItem.setCustomSkull(head.getURL()));
					}
				}, count);
				
				count++;
			}
		}
		
		final ItemBuilder backItem = new ItemBuilder(Material.ARROW).setName(C.Red + "Back");
		final ItemBuilder previousPage = new ItemBuilder(Material.SKULL_ITEM).setName(C.Red + "Previous Page").setDurability((short)3);
		final ItemBuilder nextPage = new ItemBuilder(Material.SKULL_ITEM).setName(C.Red + "Next page").setDurability((short)3);
		
		
		this.addMenuItem(new MenuItem(backItem.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new HeadPortalMenu(player));
			}
		}, 36);
		
		this.addMenuItem(new MenuItem(previousPage.setCustomSkull("M2ViZjkwNzQ5NGE5MzVlOTU1YmZjYWRhYjgxYmVhZmI5MGZiOWJlNDljNzAyNmJhOTdkNzk4ZDVmMWEyMyJ9fX0="))
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new MenuOne(player));
			}
		}, 39);
		
		this.addMenuItem(new MenuItem(nextPage.setCustomSkull("MWI2ZjFhMjViNmJjMTk5OTQ2NDcyYWVkYjM3MDUyMjU4NGZmNmY0ZTgzMjIxZTU5NDZiZDJlNDFiNWNhMTNiIn19fQ=="))
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new MenuThree(player));
			}
		}, 41);
	}
}
