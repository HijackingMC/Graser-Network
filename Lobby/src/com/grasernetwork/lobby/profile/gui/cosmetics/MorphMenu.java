package com.grasernetwork.lobby.profile.gui.cosmetics;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.grasernetwork.lobby.cosmetic.gadget.GadgetManager;
import com.grasernetwork.lobby.cosmetic.hat.HatManager;
import com.grasernetwork.lobby.cosmetic.morph.MorphData;
import com.grasernetwork.lobby.cosmetic.morph.MorphList;
import com.grasernetwork.lobby.cosmetic.morph.MorphManager;
import com.grasernetwork.lobby.cosmetic.pet.PetManager;
import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ItemBuilder;
import com.grasernetwork.util.inventory.InventoryClickType;
import com.grasernetwork.util.inventory.Menu;
import com.grasernetwork.util.inventory.MenuAPI;
import com.grasernetwork.util.inventory.MenuItem;

public class MorphMenu extends Menu
{	
	public MorphMenu(Player player, final GadgetManager gadgetManager, final HatManager hatManager, final MorphManager morphManager, final PetManager petManager, final ProfileManager profileManager)
	{
		super("Morph Menu", 6);
		
		final ItemBuilder back = new ItemBuilder(Material.ARROW).setName(C.Red + "Back");
		final ItemBuilder blank = new ItemBuilder(Material.STAINED_GLASS_PANE).setName(C.Yellow + "COMING SOON!");
		
		int[] slots = new int[]{10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,38,39,40,41,42};
		
		for (int i = 0; i < slots.length; i++)
			this.addMenuItem(new MenuItem(blank.build()), slots[i]);

		for (int i = 0; i < MorphList.values().length; i++)
		{
			this.removeMenuItem(slots[i]);
			
			final Profile profile = profileManager.getProfile(player);
			final MorphList morph = MorphList.values()[i];
			ItemStack morphItem = null;
			
			final boolean hasMorph = hasMorphType(morph, profile);
			
			if(hasMorph)
			{
				try
				{
					morphItem = new ItemBuilder(Material.SKULL_ITEM)
						.setDurability((short) 3)
						.setName(C.Green + morph.getMorph().newInstance().getName())
						.setLore(
								C.White + "[ HERE WILL BE RARITY TYPE ]",
								C.Green + " ▶ " + C.GrayI + "Click to Set Morph!"
								)
						.setCustomSkull(morph.getData());
				}
				catch (InstantiationException | IllegalAccessException e1)
				{
					e1.printStackTrace();
				}
			}
			else
			{
				try
				{
					morphItem = new ItemBuilder(Material.CLAY_BALL)
						.setDurability((short)0)
						.setName(C.Red + morph.getMorph().newInstance().getName() + " " + C.White + "(" + C.Red + "Locked" + C.White + ")")
						.setLore(
								C.White + "[ HERE WILL BE RARITY TYPE ]",
								C.Gold + "✗ Buy Now ✗"
								)
						.build();
				} catch (InstantiationException | IllegalAccessException e2)
				{
					e2.printStackTrace();
				}
			}
			
			this.addMenuItem(new MenuItem(morphItem)
			{
				@Override
				public void onClick(Player player, InventoryClickType clickType)
				{
					if (!hasMorph)
					{
						player.sendMessage("YOU DONT HAVE ACCESS TO THIS MORPH");
						return;
					}
					
					try
					{
						morphManager.morphPlayerToAll(player, morph.getMorph().newInstance());
					}
					catch (InstantiationException | IllegalAccessException e)
					{
						e.printStackTrace();
					}
					player.closeInventory();
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
	
	private boolean hasMorphType(MorphList morphType, Profile profile)
	{
//		if(profile.cosmeticData == null)
//			return false;
//
//		if(profile.cosmeticData.getHatData() == null || profile.cosmeticData.getHatData().isEmpty())
//			return false;
//
//		for(MorphData morphData : profile.cosmeticData.getMorphData())
//		{
//			if(morphType != morphData.getMorphType())
//				continue;
//
//			return true;
//		}
		return false;
	}
}