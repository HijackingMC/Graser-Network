package com.grasernetwork.lobby.profile.gui.cosmetics;

import java.text.NumberFormat;
import java.util.Locale;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.grasernetwork.lobby.cosmetic.gadget.GadgetData;
import com.grasernetwork.lobby.cosmetic.gadget.GadgetList;
import com.grasernetwork.lobby.cosmetic.gadget.GadgetManager;
import com.grasernetwork.lobby.cosmetic.hat.HatManager;
import com.grasernetwork.lobby.cosmetic.morph.MorphManager;
import com.grasernetwork.lobby.cosmetic.pet.PetManager;
import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.ItemBuilder;
import com.grasernetwork.util.PlayerUtil;
import com.grasernetwork.util.inventory.InventoryClickType;
import com.grasernetwork.util.inventory.Menu;
import com.grasernetwork.util.inventory.MenuAPI;
import com.grasernetwork.util.inventory.MenuItem;

public class GadgetMenu extends Menu
{	
	public GadgetMenu(Player player, final GadgetManager gadgetManager, final HatManager hatManager, final MorphManager morphManager, final PetManager petManager, final ProfileManager profileManager)
	{
		super("Gadget Menu", 6);
		
		final ItemBuilder back = new ItemBuilder(Material.ARROW).setName(C.Red + "Back");
		final ItemBuilder blank = new ItemBuilder(Material.CLAY_BALL).setName(C.Yellow + "COMING SOON!");
		
		int[] slots = new int[]{10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,38,39,40,41,42};
		
		for (int i = 0; i < slots.length; i++)
			this.addMenuItem(new MenuItem(blank.build()), slots[i]);
		
		for (int i = 0; i < GadgetList.values().length; i++)
		{
			this.removeMenuItem(slots[i]);
			
			final Profile profile = profileManager.getProfile(player);
			final GadgetList gadget = GadgetList.values()[i];
			ItemStack gadgetItem;
			
			final boolean hasGadget = hasGadgetType(gadget, profile);
			
			if (hasGadget)
				gadgetItem = new ItemBuilder(gadget.getGadgetClass().getMaterial())
					.setName(C.Green + gadget.getGadgetClass().getName())
					.setLore(
							"",
							C.White + "Rarity: " + gadget.getGadgetClass().getRarityType().getColour() + gadget.getGadgetClass().getRarityType().getName(),
							"",
							C.Gray + "Gadget Description..",
							C.Gray + "More Gadget Description..",
							"",
							C.Green + " ▶ " + C.GrayI + "Left Click to use Gadget!"
							)
					.build();
			else
				gadgetItem = new ItemBuilder(Material.CLAY_BALL)
					.setDurability((short)0)
					.setName(C.Red + gadget.getGadgetClass().getName())
					.setLore(
							C.Gray + "Price" + C.White + ": " + C.Aqua + NumberFormat.getNumberInstance(Locale.US).format(Long.valueOf(gadget.getGadgetClass().getPrice())) + " Crystals",
							"",
							C.White + "Rarity: " + gadget.getGadgetClass().getRarityType().getColour() + gadget.getGadgetClass().getRarityType().getName(),
							"",
							C.Gray + "Gadget Description..",
							C.Gray + "More Gadget Description..",
							"",
							C.Red + " ▶ " + C.GrayI + "Right Click to Purchase Gadget!"
							)
					.build();
			
			this.addMenuItem(new MenuItem(gadgetItem)
			{
				@Override
				public void onClick(Player player, InventoryClickType clickType)
				{
					if(clickType == InventoryClickType.RIGHT)
					{
						if (!hasGadget)
						{
							if(profile.crystals < gadget.getGadgetClass().getPrice())
							{
								PlayerUtil.message(player, "You dont have enough Crystals to purchase Gadget.", ChatType.ERROR);
								return;
							}
							
							profile.crystals -= gadget.getGadgetClass().getPrice();
//							profile.cosmeticData.addGadget(new GadgetData(gadget));TODO
							PlayerUtil.message(player, "Gadget '%s' purchased for " + C.Aqua + gadget.getGadgetClass().getPrice() + " Crystals", new String[] {gadget.getGadgetClass().getName()}, ChatType.SUCCESS);
							MenuAPI.openMenu(player, new GadgetMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager), false);
							return;
						}
					}
					else if(clickType == InventoryClickType.LEFT)
					{
						if (hasGadget)
						{
							gadgetManager.giveGadget(player, gadget);
							player.closeInventory();
						}
					}
				}
			}, slots[i]);
		}
		
		this.addMenuItem(new MenuItem(back.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				MenuAPI.openMenu(player, new CosmeticsPortalMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager));
			}
		}, 45);
		
		this.setBypassMenuCloseBehaviour(true);
	}
	
	private boolean hasGadgetType(GadgetList gadgetType, Profile profile)
	{
//		if(profile.cosmeticData == null)
//			return false;
//
//		if(profile.cosmeticData.getMorphData() == null || profile.cosmeticData.getMorphData().isEmpty())
//			return false;
//
//		for(GadgetData gadgetData : profile.cosmeticData.getGadgetData())
//		{
//			if(gadgetType != gadgetData.getGadgetType())
//				continue;
//
//			return true;
//		}
		return false;
	}
}