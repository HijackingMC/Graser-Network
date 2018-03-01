package com.grasernetwork.lobby.cosmetic.pet.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.grasernetwork.lobby.cosmetic.pet.data.PetData;
import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodType;
import com.grasernetwork.lobby.cosmetic.pet.data.level.Level;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ItemBuilder;
import com.grasernetwork.util.inventory.InventoryClickType;
import com.grasernetwork.util.inventory.Menu;
import com.grasernetwork.util.inventory.MenuAPI;
import com.grasernetwork.util.inventory.MenuItem;

public class PetMenu extends Menu
{

	public PetMenu(final PetData petData)
	{
		super(petData.entity.getCustomName(), 6);
		
		final ItemBuilder close = new ItemBuilder(Material.BARRIER).setName(C.Red + "Close");
		final ItemBuilder head = new ItemBuilder(Material.SKULL_ITEM)
			.setDurability((short) 3)
			.setName(petData.entity.getCustomName())
			.setLore(
					"",
					C.White + "Level: " + C.Green + petData.getLevelData().getFormattedLevel() + C.White + " ["
							+ C.Green + Level.getExpBar(C.Green, C.Gray, petData.getLevelData().getLevel(), petData.getLevelData().getExp())
							+ C.White + "] " + C.Red + (petData.getLevelData().getFormattedLevel(1)),
					"",
					C.White + "Status: " + petData.getStatus(),
					"",
					C.White + "Food: " + C.Aqua + petData.getFoodData().getFoodLevel() + C.White + "/" + C.Aqua + "100",
					C.White + "Thirst: " + C.Aqua + petData.getFoodData().getThurstLevel() + C.White + "/" + C.Aqua + "100",
					"",
					C.Gray + "Looking after your pet will",
					C.Gray + "increase your overall pet level,",
					"",
					C.Gray + "Rewards can be earned from",
					C.Gray + "tendering your pet!"
					);
		
		int[] slots = new int[]{19,20,21,22,23,24,25,28,29,30,31,32,33,34,38,39,40,41,42};
		
		for(int i = 0; i < 9; i++)
			this.addMenuItem(new MenuItem(petData.calculateStatusItems()[i]), i);
		
		for (int i = 0; i < petData.getPetType().getFood().getAppertites().length; i++)
		{
			final Appertite appertite = petData.getPetType().getFood().getAppertites()[i];
			
			ItemBuilder item = new ItemBuilder(appertite.getMaterial()).setDurability(appertite.getData()).setName(C.Green + appertite.getName());
			
			this.addMenuItem(new MenuItem(item.build())
			{
				@Override
				public void onClick(Player player, InventoryClickType clickType)
				{
					if(appertite.getFoodType() == FoodType.INSTANT_DAMAGE_POTION)
					{
						player.sendMessage("IN DEV");
					}
					
					else if(appertite.getFoodType() == FoodType.INSTANT_HEALTH_POTION)
					{
						player.sendMessage("IN DEV");
					}
					
					else
					{
						int food = petData.getPetType().getFood().getFoodGeneration(appertite.getFoodType());
						int thurst = petData.getPetType().getFood().getThurstGeneration(appertite.getFoodType());
						
						if(food > 0)
							petData.getFoodData().addFoodLevel(food);
						
						if(thurst > 0)
							petData.getFoodData().addThurstLevel(thurst);
					}
					
					MenuAPI.openMenu(player, new PetMenu(petData), false);
				}
			}, slots[i]);
		}
		
		this.addMenuItem(new MenuItem(close.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				player.closeInventory();
			}
		}, 45);
		
		this.addMenuItem(new MenuItem(head.setCustomSkull(petData.getPetType().getPetData())), 53);
		
		this.setBypassMenuCloseBehaviour(true);
	}

}
