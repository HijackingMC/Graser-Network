package com.grasernetwork.game.games.zombies;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.game.Game;
import com.grasernetwork.game.components.game.GameType;
import com.grasernetwork.game.components.game.event.GameTeleportEvent;
import com.grasernetwork.game.games.zombies.weapon.gun.Gun;
import com.grasernetwork.game.games.zombies.weapon.gun.Guns;
import com.grasernetwork.game.games.zombies.weapon.gun.StandardGun;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Teddeh on 30/03/2016.
 */
public abstract class Zombies extends Game
{
	private HashMap<Player, Gun> temp;

	public Zombies(GamePlugin plugin, int playerCap)
	{
		super(plugin, 2, "Zombies", playerCap, GameType.STANDALONE);

		temp = new HashMap<>();
	}

	@EventHandler
	public void onTeleport(GameTeleportEvent event)
	{
		//TODO: TEMP
		for(Player all : Bukkit.getOnlinePlayers())
		{
			Gun gun = Guns.values()[new Random().nextInt(Guns.values().length)].getGun();

			all.sendMessage("Your starting gun is " + gun.getName());

			all.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_PICKAXE).setName(C.RedB + gun.getName())
					.setDurability((short) gun.getId()).build());

			temp.put(all, gun);

			all.updateInventory();
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event)
	{
		if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		if(item == null || item.getType() == Material.AIR)
			return;

		if(item.getType() != Material.DIAMOND_PICKAXE)
			return;

		Gun gun = temp.get(player);
		if(gun == null) return;

		if(!(gun instanceof StandardGun))
			return;

		StandardGun standardGun = (StandardGun) gun;
		standardGun.shoot(player);
	}
}
