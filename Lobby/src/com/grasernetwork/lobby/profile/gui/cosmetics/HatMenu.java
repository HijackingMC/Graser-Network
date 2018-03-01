package com.grasernetwork.lobby.profile.gui.cosmetics;

import java.text.NumberFormat;
import java.util.Locale;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.grasernetwork.lobby.cosmetic.gadget.GadgetManager;
import com.grasernetwork.lobby.cosmetic.hat.HatData;
import com.grasernetwork.lobby.cosmetic.hat.HatList;
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

public class HatMenu extends Menu
{	
	public HatMenu(Player player, final GadgetManager gadgetManager, final HatManager hatManager, final MorphManager morphManager, final PetManager petManager, final ProfileManager profileManager)
	{
		super("Hat Menu", 6);
		
		final ItemBuilder back = new ItemBuilder(Material.ARROW).setName(C.Red + "Back");
		final ItemBuilder blank = new ItemBuilder(Material.STAINED_GLASS_PANE).setName(C.Yellow + "COMING SOON!");
		
		int[] slots = new int[]{10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,38,39,40,41,42};
		
		for (int i = 0; i < slots.length; i++)
			this.addMenuItem(new MenuItem(blank.build()), slots[i]);
		
		for (int i = 0; i < HatList.values().length; i++)
		{
			this.removeMenuItem(slots[i]);

			final Profile profile = profileManager.getProfile(player);
			final HatList hat = HatList.values()[i];
			final ItemStack hatItem;
			
			final boolean hasHat = hasHatType(hat, profile);
			
			
			if (hasHat)
				hatItem = new ItemBuilder(Material.SKULL_ITEM)
					.setDurability((short)3)
					.setName(C.Green + hat.getHatClass().getName())
					.setLore(
							"",
							C.White + "Rarity: " + hat.getHatClass().getRarityType().getColour() + hat.getHatClass().getRarityType().getName(),
							"",
							C.Gray + "Gadget Description..",
							C.Gray + "More Gadget Description..",
							"",
							C.Green + " ▶ " + C.GrayI + "Left Click to set Hat!"
							)
					.setCustomSkull(hat.getHatClass().getDataURL());
			else
				hatItem = new ItemBuilder(Material.CLAY_BALL)
					.setDurability((short)0)
					.setName(C.Red + hat.getHatClass().getName())
					.setLore(
							C.Gray + "Price" + C.White + ": " + C.Aqua + NumberFormat.getNumberInstance(Locale.US).format(Long.valueOf(hat.getHatClass().getPrice())) + " Crystals",
							"",
							C.White + "Rarity: " + hat.getHatClass().getRarityType().getColour() + hat.getHatClass().getRarityType().getName(),
							"",
							C.Gray + "Gadget Description..",
							C.Gray + "More Gadget Description..",
							"",
							C.Red + " ▶ " + C.GrayI + "Right Click to Purchase Hat!"
							)
					.build();
			
			this.addMenuItem(new MenuItem(hatItem)
			{
				@Override
				public void onClick(Player player, InventoryClickType clickType)
				{
					if(clickType == InventoryClickType.RIGHT)
					{
						if (!hasHat)
						{
							if(profile.crystals < hat.getHatClass().getPrice())
							{
								PlayerUtil.message(player, "You dont have enough Crystals to purchase Hat.", ChatType.ERROR);
								return;
							}

							profile.crystals -= hat.getHatClass().getPrice();
//							profile.cosmeticData.addHat(new HatData(hat));
							PlayerUtil.message(player, "Hat '%s' purchased for " + C.Aqua + hat.getHatClass().getPrice() + " Crystals", new String[] { hat.getHatClass().getName() }, ChatType.SUCCESS);
							MenuAPI.openMenu(player, new HatMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager), false);
							return;
						}
					}
					else if(clickType == InventoryClickType.LEFT)
					{
						if (hasHat)
						{
							hatManager.giveHat(player, new ItemBuilder(Material.SKULL_ITEM).setDurability((short)3).setCustomSkull(hat.getHatClass().getDataURL()));
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
	
	private boolean hasHatType(HatList hatType, Profile profile)
	{
//		if(profile.cosmeticData == null)
//			return false;
//
//		if(profile.cosmeticData.getHatData() == null || profile.cosmeticData.getHatData().isEmpty())
//			return false;
//
//		for(HatData hatData : profile.cosmeticData.getHatData())
//		{
//			if(hatType != hatData.getHatType())
//				continue;
//
//			return true;
//		}
		return false;
	}
}