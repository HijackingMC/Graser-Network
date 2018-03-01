package com.grasernetwork.lobby.profile.gui.cosmetics;

import java.text.NumberFormat;
import java.util.Locale;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.grasernetwork.lobby.cosmetic.gadget.GadgetManager;
import com.grasernetwork.lobby.cosmetic.hat.HatManager;
import com.grasernetwork.lobby.cosmetic.morph.MorphManager;
import com.grasernetwork.lobby.cosmetic.pet.PetManager;
import com.grasernetwork.lobby.cosmetic.pet.PetType;
import com.grasernetwork.lobby.cosmetic.pet.data.PetData;
import com.grasernetwork.lobby.cosmetic.pet.data.emotion.EmotionData;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodData;
import com.grasernetwork.lobby.cosmetic.pet.data.level.Level;
import com.grasernetwork.lobby.cosmetic.pet.data.level.LevelData;
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

public class PetMenu extends Menu
{	
	public PetMenu(Player player, final GadgetManager gadgetManager, final HatManager hatManager, final MorphManager morphManager, final PetManager petManager, final ProfileManager profileManager)
	{
		super("Pet Menu", 6);
		
		final ItemBuilder back = new ItemBuilder(Material.ARROW).setName(C.Red + "Back");
		final ItemBuilder blank = new ItemBuilder(Material.STAINED_GLASS_PANE).setName(C.Yellow + "COMING SOON!");
		
		int[] slots = new int[]{10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,38,39,40,41,42};
		
		for (int i = 0; i < slots.length; i++)
			this.addMenuItem(new MenuItem(blank.build()), slots[i]);

		for (int i = 0; i < PetType.values().length; i++)
		{
			this.removeMenuItem(slots[i]);
			
			final PetType pet = PetType.values()[i];
			final Profile profile = profileManager.getProfile(player);
			ItemBuilder petItem = null;
			final boolean hasPet = hasPetType(pet, profile);
			PetData data = null;
//			for(PetData petData : profile.cosmeticData.getPetData())
//			{
//				if(pet != petData.getPetType())
//					continue;
//
//				data = petData;
//			}
			
			if(hasPet)
				petItem = new ItemBuilder(Material.SKULL_ITEM)
					.setDurability((short)3)
					.setName(C.Green + pet.getPetName())
					.setLore(
							"",
							C.White + "Level: " + C.Green + data.getLevelData().getFormattedLevel() + C.White + " ["
								+ C.Green + Level.getExpBar(C.Green, C.Gray, data.getLevelData().getLevel(), data.getLevelData().getExp())
								+ C.White + "] " + C.Red + (data.getLevelData().getFormattedLevel(1)),
							"",
							C.White + "Status: " + data.getStatus(),
							"",
							C.White + "Food: " + C.Aqua + data.getFoodData().getFoodLevel() + C.White + "/" + C.Aqua + "100",
							C.White + "Thirst: " + C.Aqua + data.getFoodData().getThurstLevel() + C.White + "/" + C.Aqua + "100",
							"",
							C.Gray + "Looking after your pet will",
							C.Gray + "increase your overall pet level,",
							"",
							C.Gray + "Rewards can be earned from",
							C.Gray + "tendering your pet!",
							"",
							C.Green + " ▶ " + C.GrayI + "Click to Spawn Pet!"
							);
			else
				petItem = new ItemBuilder(Material.CLAY_BALL)
					.setDurability((short)0)
					.setName(C.Red + pet.getPetName())
					.setLore(
							C.Gray + "Price" + C.White + ": " + C.Aqua + NumberFormat.getNumberInstance(Locale.US).format(Long.valueOf(pet.getPrice())) + " Crystals",
							"",
							C.White + "Rarity: " + pet.getRarityType().getColour() + pet.getRarityType().getName(),
							"",
							C.Gray + "Gadget Description..",
							C.Gray + "More Gadget Description..",
							"",
							C.Red + " ▶ " + C.GrayI + "Right Click to Purchase Pet!"
							);
			
			this.addMenuItem(new MenuItem(hasPetType(pet, profile) ? petItem.setCustomSkull(pet.getPetData()) : petItem.build())
			{
				@Override
				public void onClick(Player player, InventoryClickType clickType)
				{
					if(clickType == InventoryClickType.RIGHT)
					{
						if (!hasPet)
						{
							if(profile.crystals < pet.getPrice())
							{
								PlayerUtil.message(player, "You dont have enough Crystals to purchase Hat.", ChatType.ERROR);
								return;
							}

							profile.crystals -= pet.getPrice();
//							profile.cosmeticData.addPet(new PetData(pet, pet.getPetName(), new EmotionData(), new LevelData(player.getName(), 1, 0), new FoodData(pet, 25, 25)));
							PlayerUtil.message(player, "Hat '%s' purchased for " + C.Aqua + pet.getPrice() + " Crystals", new String[] { pet.getPetName() }, ChatType.SUCCESS);
							MenuAPI.openMenu(player, new PetMenu(player, gadgetManager, hatManager, morphManager, petManager, profileManager), false);
							return;
						}
					}
					else if(clickType == InventoryClickType.LEFT)
					{
						if (hasPet)
						{
							PetData data = null;
//							for(PetData petData : profile.cosmeticData.getPetData())
//							{
//								if(pet != petData.getPetType())
//									continue;
//
//								data = petData;
//							}
							
							petManager.givePet(player, data);
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
	
	private boolean hasPetType(PetType petType, Profile profile)
	{
//		if(profile.cosmeticData == null)
//			return false;
//
//		if(profile.cosmeticData.getPetData() == null || profile.cosmeticData.getPetData().isEmpty())
//			return false;
//
//		for(PetData petData : profile.cosmeticData.getPetData())
//		{
//			if(petType != petData.getPetType())
//				continue;
//
//			return true;
//		}
		return false;
	}
}