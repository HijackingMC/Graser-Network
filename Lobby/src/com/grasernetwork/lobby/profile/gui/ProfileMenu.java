package com.grasernetwork.lobby.profile.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.grasernetwork.lobby.cosmetic.gadget.GadgetManager;
import com.grasernetwork.lobby.cosmetic.hat.HatManager;
import com.grasernetwork.lobby.cosmetic.morph.MorphManager;
import com.grasernetwork.lobby.cosmetic.pet.PetManager;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.util.ItemBuilder;
import com.grasernetwork.util.inventory.InventoryClickType;
import com.grasernetwork.util.inventory.Menu;
import com.grasernetwork.util.inventory.MenuItem;

public class ProfileMenu extends Menu
{
	public ProfileMenu(Player player, final GadgetManager gadgetManager, final HatManager hatManager, final MorphManager morphManager, final PetManager petManager, final ProfileManager profileManager)
	{
		super("PROFILE", 3);
		
		final ItemBuilder achievements = new ItemBuilder(Material.DIAMOND).setName("&eAchievements");
		final ItemBuilder cosmetics = new ItemBuilder(Material.ENDER_CHEST).setName("&eCosmetics");
		final ItemBuilder playerSkull = new ItemBuilder(Material.SKULL_ITEM).setName("&e" + player.getName()).setDurability((short)3).setSkullOwner(player);
		final ItemBuilder recentPunishments = new ItemBuilder(Material.BOOK).setName("&eRecent Punishments");
		final ItemBuilder settings = new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("&ePlayer Settings");
		
		this.addMenuItem(new MenuItem(achievements.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
//				MenuAPI.openMenu(player, new AchievementPortalMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager));
			}
		}, 10);
		
		this.addMenuItem(new MenuItem(cosmetics.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
//				MenuAPI.openMenu(player, new CosmeticsPortalMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager));
			}
		}, 11);
		
		this.addMenuItem(new MenuItem(playerSkull.build()), 13);
		
		this.addMenuItem(new MenuItem(recentPunishments.build()), 15);
		
		this.addMenuItem(new MenuItem(settings.build()), 16);
	}
}
