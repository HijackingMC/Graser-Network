 package com.grasernetwork.lobby.profile.gui.cosmetics;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.grasernetwork.lobby.cosmetic.gadget.GadgetManager;
import com.grasernetwork.lobby.cosmetic.hat.HatManager;
import com.grasernetwork.lobby.cosmetic.morph.MorphManager;
import com.grasernetwork.lobby.cosmetic.pet.PetManager;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.lobby.profile.gui.ProfileMenu;
import com.grasernetwork.util.ItemBuilder;
import com.grasernetwork.util.inventory.InventoryClickType;
import com.grasernetwork.util.inventory.Menu;
import com.grasernetwork.util.inventory.MenuAPI;
import com.grasernetwork.util.inventory.MenuItem;

public class CosmeticsPortalMenu extends Menu
{
	public CosmeticsPortalMenu(Player player, final GadgetManager gadgetManager, final HatManager hatManager, final MorphManager morphManager, final PetManager petManager, final ProfileManager profileManager)
	{
		super("Cosmetic Menu", 6);
		
		final ItemBuilder blank = new ItemBuilder(Material.STAINED_GLASS_PANE);
		final ItemBuilder particles = new ItemBuilder(Material.NETHER_STAR).setName("&eParticles");
		final ItemBuilder hats = new ItemBuilder(Material.SKULL_ITEM).setDurability((byte)4).setName("&eHats");
		final ItemBuilder wardrobe = new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("&eWardrobe");
		final ItemBuilder morphs = new ItemBuilder(Material.PORK).setName("&eMorphs");
		final ItemBuilder rides = new ItemBuilder(Material.MINECART).setName("&eRides");
		final ItemBuilder gadgets = new ItemBuilder(Material.CHEST).setName("&eGadgets");
		final ItemBuilder pets = new ItemBuilder(Material.MONSTER_EGG).setName("&ePets").setDurability((short)98);
		final ItemBuilder back = new ItemBuilder(Material.ARROW).setName("&cBack");
		
		this.addMenuItem(new MenuItem(blank.setDurability((byte)14).build()), 0);
		this.addMenuItem(new MenuItem(blank.setDurability((byte)1).build()), 1);
		this.addMenuItem(new MenuItem(blank.setDurability((byte)4).build()), 2);
		this.addMenuItem(new MenuItem(blank.setDurability((byte)5).build()), 3);
		this.addMenuItem(new MenuItem(blank.setDurability((byte)3).build()), 4);
		this.addMenuItem(new MenuItem(blank.setDurability((byte)11).build()), 5);
		this.addMenuItem(new MenuItem(blank.setDurability((byte)10).build()), 6);
		this.addMenuItem(new MenuItem(blank.setDurability((byte)2).build()), 7);
		this.addMenuItem(new MenuItem(blank.setDurability((byte)6).build()), 8);
		
		this.addMenuItem(new MenuItem(particles.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new ParticleMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager));
			}
		}, 19);
		
		this.addMenuItem(new MenuItem(hats.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new HatMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager));
			}
		}, 21);
		
		this.addMenuItem(new MenuItem(wardrobe.build()), 23);
		
		this.addMenuItem(new MenuItem(morphs.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new MorphMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager));
			}
		}, 25);
		
		this.addMenuItem(new MenuItem(rides.build()), 38);
		
		this.addMenuItem(new MenuItem(gadgets.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new GadgetMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager));
			}
		}, 40);
		
		this.addMenuItem(new MenuItem(pets.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new PetMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager));
			}
		}, 42);
		
		this.addMenuItem(new MenuItem(back.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new ProfileMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager));
			}
		}, 45);
	}
}