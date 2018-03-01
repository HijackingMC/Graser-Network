package com.grasernetwork.lobby.elements;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.grasernetwork.util.C;
import com.grasernetwork.util.ItemBuilder;

public class InventoryManager implements Listener
{
	private void giveItems(Player player)
	{
		player.getInventory().clear();
		
		ItemStack serverSelector = new ItemBuilder(Material.COMPASS)
								.setName(C.LightPurpleB + "SERVER SELECTOR")
								.setLore(
										C.Gray + "Use this item to navigate around the different",
										C.Gray + "gamemodes"
										)
								.build();
		
		ItemStack magicClockOn = new ItemBuilder(Material.INK_SACK)
								.setName(C.AquaB + "PLAYER VISABILITY " + C.WhiteB + ":" + C.Green + "Enabled")
								.setLore(
										C.Gray + "Use this item to toggle whether players are",
										C.Gray + "visable in lobbies."
										)
								.build();
		
		ItemStack magicClockOff = new ItemBuilder(Material.INK_SACK)
								.setName(C.AquaB + "PLAYER VISABILITY " + C.WhiteB + ":" + C.Red + "Disabled")
								.setLore(
										C.Gray + "Use this item to toggle whether players are",
										C.Gray + "visable in lobbies."
										)
								.build();
		
		player.getInventory().setItem(0, serverSelector);
		player.getInventory().setItem(1, magicClockOn);
	}
}
