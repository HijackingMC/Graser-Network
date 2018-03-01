 package com.grasernetwork.core.heads.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.grasernetwork.util.ItemBuilder;
import com.grasernetwork.util.inventory.InventoryClickType;
import com.grasernetwork.util.inventory.Menu;
import com.grasernetwork.util.inventory.MenuAPI;
import com.grasernetwork.util.inventory.MenuItem;

public class HeadPortalMenu extends Menu
{
	public HeadPortalMenu(Player player)
	{
		super("Head Menu", 5);
		
		final ItemBuilder blocks = new ItemBuilder(Material.GRASS);
		final ItemBuilder decoration = new ItemBuilder(Material.FLOWER_POT_ITEM);
		final ItemBuilder devices = new ItemBuilder(Material.REDSTONE_COMPARATOR);
		final ItemBuilder mobs = new ItemBuilder(Material.SKULL_ITEM);
		final ItemBuilder characters = new ItemBuilder(Material.ARMOR_STAND);
		final ItemBuilder food = new ItemBuilder(Material.COOKED_CHICKEN);
		final ItemBuilder alphabetical = new ItemBuilder(Material.BOOK_AND_QUILL);
		final ItemBuilder colours = new ItemBuilder(Material.INK_SACK);
		
		this.addMenuItem(new MenuItem(blocks.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new com.grasernetwork.core.heads.gui.blocks.MenuOne(player));
			}
		}, 10);
		
		this.addMenuItem(new MenuItem(decoration.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new com.grasernetwork.core.heads.gui.decoration.MenuOne(player));
			}
		}, 12);
		
		this.addMenuItem(new MenuItem(devices.build()), 14);
		
		this.addMenuItem(new MenuItem(mobs.build()), 16);
		
		this.addMenuItem(new MenuItem(characters.build()), 28);
		
		this.addMenuItem(new MenuItem(food.build()), 30);
		
		this.addMenuItem(new MenuItem(alphabetical.build()), 32);
		
		this.addMenuItem(new MenuItem(colours.build()), 34);
	}
}