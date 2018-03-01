package com.grasernetwork.lobby.cosmetic.crates.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.grasernetwork.lobby.cosmetic.crates.CrateRating;
import com.grasernetwork.lobby.cosmetic.crates.CratesManager;
import com.grasernetwork.lobby.cosmetic.crates.reward.Reward;
import com.grasernetwork.lobby.cosmetic.crates.reward.RewardType;
import com.grasernetwork.lobby.cosmetic.crates.reward.type.cosmetic.ICosmeticReward;
import com.grasernetwork.lobby.cosmetic.crates.reward.type.eco.IEcoReward;
import com.grasernetwork.lobby.cosmetic.crates.reward.type.rank.IRankReward;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ItemBuilder;
import com.grasernetwork.util.inventory.InventoryClickType;
import com.grasernetwork.util.inventory.Menu;
import com.grasernetwork.util.inventory.MenuItem;

public class CrateMenu extends Menu
{

	public CrateMenu(final CratesManager manager, final Block clickedBlock)
	{
		super("Crates", 6);
		
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(C.Green + "Rewards: ");
		for(Reward reward : CrateRating.UNRATED.getAvailableRewards())
		{
			if(reward instanceof ICosmeticReward)
			{
				if(reward.getRewardType() == RewardType.GADGET)
					lore.add(C.Aqua + " Gadget id:" + ((ICosmeticReward) reward).getCosmeticId());
				else if(reward.getRewardType() == RewardType.HAT)
					lore.add(C.Aqua + " Hat id:" + ((ICosmeticReward) reward).getCosmeticId());
				else if(reward.getRewardType() == RewardType.MORPH)
					lore.add(C.Aqua + " Morph id:" + ((ICosmeticReward) reward).getCosmeticId());
				else if(reward.getRewardType() == RewardType.PARTICLE)
					lore.add(C.Aqua + " Particle id:" + ((ICosmeticReward) reward).getCosmeticId());
				else if(reward.getRewardType() == RewardType.PET)
					lore.add(C.Aqua + " Pet id:" + ((ICosmeticReward) reward).getCosmeticId());
			}
			else if(reward instanceof IEcoReward)
				lore.add(C.Aqua + " Crystals: " + ((IEcoReward) reward).getAmount());
			else if(reward instanceof IRankReward)
				lore.add(C.Aqua + " Rank: " + ((IRankReward) reward).getRank().toString());
		}
		lore.add("");
		lore.add(C.Green + " ▶ " + C.GrayI + "Click to redeen Crate!");
		
		ItemStack testItem = new ItemBuilder(Material.CHEST)
				.setName(C.Aqua + "Crate")
				.setLoreArray(lore)
				.build();
		
		addMenuItem(new MenuItem(testItem)
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				manager.start(player, clickedBlock);
			}
		}, 0);
	}
}
