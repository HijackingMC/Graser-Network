package com.grasernetwork.core.punish.gui;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.punish.PunishManager;
import com.grasernetwork.core.punish.Punishments;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.ItemBuilder;
import com.grasernetwork.util.PlayerUtil;
import com.grasernetwork.util.inventory.InventoryClickType;
import com.grasernetwork.util.inventory.Menu;
import com.grasernetwork.util.inventory.MenuItem;

public class PunishMenu extends Menu
{
	public PunishMenu(Player player, final PunishManager punishManager, final Profile profile, final OfflinePlayer target)
	{
		super("Punishment: " + new Random().nextInt(999999), 6);
		
		final ItemBuilder skullItem = new ItemBuilder(Material.SKULL_ITEM)
				.setDurability((short)3)
				.setSkullOwner(target.getName())
				.setLore(C.Gray + target.getUniqueId().toString())
				.setName(C.WhiteB + target.getName());
		
		final ItemBuilder bucketItem = new ItemBuilder(Material.BUCKET)
				.setName(C.WhiteB + "Clear History")
				.setLore(C.Gray + "This will clear the history for the selected player player");
		
		final ItemBuilder lavaBucketItem = new ItemBuilder(Material.LAVA_BUCKET)
				.setName(C.RedB + "Permanent Ban")
				.setLore(C.Gray + "This will permanently ban the user from the server.");
		
		final ItemBuilder bookItem = new ItemBuilder(Material.BOOK)
				.setName(C.WhiteB + "Recent Punishments")
				.setLore(C.Gray + "Click to open");
		
		final ItemBuilder inksackItem = new ItemBuilder(Material.INK_SACK);
		final ItemBuilder stainedClayItem = new ItemBuilder(Material.STAINED_CLAY);
		final ItemBuilder stainedGlassItem = new ItemBuilder(Material.STAINED_GLASS);
		
		this.addMenuItem(new MenuItem(skullItem.build()), 0);
		this.addMenuItem(new MenuItem(inksackItem.setName("&9&lHELPER SECTION").setLore("&7The punishment system for the HELPERS within Graser Networks").setDurability((short)12).build()), 2);
		this.addMenuItem(new MenuItem(inksackItem.setName("&9&lHELPER SECTION").setLore("&7The punishment system for the HELPERS within Graser Networks").setDurability((short)12).build()), 3);
		this.addMenuItem(new MenuItem(inksackItem.setName("&6&lMODERATOR SECTION").setLore("&7The punishment system for the MODERATORS within Graser Networks").setDurability((short)11).build()), 5);
		this.addMenuItem(new MenuItem(inksackItem.setName("&6&lMODERATOR SECTION").setLore("&7The punishment system for the MODERATORS within Graser Networks").setDurability((short)11).build()), 6);
		this.addMenuItem(new MenuItem(inksackItem.setName("&c&lADMINISTRATOR SECTION").setLore("&7The punishment system for the ADMINISTRATORS within Graser Networks").setDurability((short)1).build()), 8);
		this.addMenuItem(new MenuItem(bookItem.build()), 45);
		
		this.addMenuItem(new MenuItem(bucketItem.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				if (profile.getRank().hasPermission(Rank.ADMIN))
				{
					punishManager.clearPunishments(target, player);
					player.closeInventory();
				}
			}
		}, 27);
		this.addMenuItem(new MenuItem(lavaBucketItem.build())
		{
			@Override
			public void onClick(Player player, InventoryClickType clickType)
			{
				if (profile.getRank().hasPermission(Rank.ADMIN))
				{
					punishManager.ban(target.getName(), player, 100,  315360000000L);
					player.closeInventory();
				}
			}
		}, 18);
		
		if (profile.getRank().hasPermission(Rank.HELPER))
		{
			for (final Punishments.Helper helper : Punishments.Helper.values())
			{
				this.addMenuItem(new MenuItem(stainedClayItem
						.setDurability(helper.getData())
						.setName(C.BlueB + helper.getPunishTitle())
						.setLoreArray(Arrays.asList(C.Gray + helper.getPunishDescription()))
						.build())
							{
								@Override
								public void onClick(Player player, InventoryClickType clickType)
								{
									if(helper.getType() == Punishments.Type.BAN)
										punishManager.ban(target.getName(), player, helper.getId(), helper.getTime());
									else if(helper.getType() == Punishments.Type.MUTE)
										punishManager.mute(target.getName(), player, helper.getId(), helper.getTime());
									else if(helper.getType() == Punishments.Type.WARN)
										punishManager.warn(target.getName(), player, helper.getReason());
									player.closeInventory();
								}
							}
				, helper.getSlot());
			}
			
			for (final Punishments.Moderator moderator : Punishments.Moderator.values())
			{
				this.addMenuItem(new MenuItem(stainedGlassItem
						.setDurability(moderator.getData())
						.setName(C.BlueB + moderator.getPunishTitle())
						.setLoreArray(Arrays.asList(C.Gray + moderator.getPunishDescription()))
						.build())
							{
								@Override
								public void onClick(Player player, InventoryClickType clickType)
								{
									PlayerUtil.message(Bukkit.getPlayer(profile.getUUID()), "You do not have permission for this!", ChatType.PERMISSION);
									player.closeInventory();
								}
							}
				, moderator.getSlot());
			}
			
			for (final Punishments.Admin admin : Punishments.Admin.values())
			{
				this.addMenuItem(new MenuItem(stainedGlassItem
						.setDurability(admin.getData())
						.setName(C.BlueB + admin.getPunishTitle())
						.setLoreArray(Arrays.asList(C.Gray + admin.getPunishDescription()))
						.build())
							{
								@Override
								public void onClick(Player player, InventoryClickType clickType)
								{
									PlayerUtil.message(Bukkit.getPlayer(profile.getUUID()), "You do not have permission for this!", ChatType.PERMISSION);
									player.closeInventory();
								}
							}
				, admin.getSlot());
			}
		}
		
		if (profile.getRank().hasPermission(Rank.MODERATOR))
		{
			for (final Punishments.Moderator moderator : Punishments.Moderator.values())
			{
				this.removeMenuItem(moderator.getSlot());
				this.addMenuItem(new MenuItem(stainedClayItem
						.setDurability(moderator.getData())
						.setName(C.GoldB + moderator.getPunishTitle())
						.setLoreArray(Arrays.asList(C.Gray + moderator.getPunishDescription()))
						.build())
							{
								@Override
								public void onClick(Player player, InventoryClickType clickType)
								{
									if(moderator.getType() == Punishments.Type.BAN)
										punishManager.ban(target.getName(), player, moderator.getId(), moderator.getTime());
									else if(moderator.getType() == Punishments.Type.MUTE)
										punishManager.mute(target.getName(), player, moderator.getId(), moderator.getTime());
									else if(moderator.getType() == Punishments.Type.WARN)
										punishManager.warn(target.getName(), player, moderator.getReason());
									player.closeInventory();
								}
							}
				, moderator.getSlot());
			}
		}
		
		if (profile.getRank().hasPermission(Rank.ADMIN))
		{
			for (final Punishments.Admin admin : Punishments.Admin.values())
			{
				this.removeMenuItem(admin.getSlot());
				this.addMenuItem(new MenuItem(stainedClayItem
						.setDurability(admin.getData())
						.setName(C.RedB + admin.getPunishTitle())
						.setLoreArray(Arrays.asList(C.Gray + admin.getPunishDescription()))
						.build())
							{
								@Override
								public void onClick(Player player, InventoryClickType clickType)
								{
									if(admin.getType() == Punishments.Type.BAN)
										punishManager.ban(target.getName(), player, admin.getId(), admin.getTime());
									else if(admin.getType() == Punishments.Type.MUTE)
										punishManager.mute(target.getName(), player, admin.getId(), admin.getTime());
									else if(admin.getType() == Punishments.Type.WARN)
										punishManager.warn(target.getName(), player, admin.getReason());
									player.closeInventory();
								}
							}
				, admin.getSlot());
			}
		}
	}

}
