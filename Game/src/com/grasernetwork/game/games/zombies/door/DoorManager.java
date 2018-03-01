package com.grasernetwork.game.games.zombies.door;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.games.zombies.event.PlayerOpenDoorEvent;
import com.grasernetwork.util.ServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Teddeh on 31/03/2016.
 */
public class DoorManager implements Listener
{
	private List<Door> doors;

	public DoorManager(GamePlugin plugin)
	{
		doors = new ArrayList<>();

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	public void addDoor(Door door)
	{
		doors.add(door);
	}

	public Door getDoorFromLocation(Location location)
	{
		for (Door door : doors)
		{
			for (Location loc : door.getDoorBlockLocations())
			{
				if (!location.getBlock().getLocation().equals(loc.getBlock().getLocation())) continue;

				return door;
			}
		}

		return null;
	}

	public Door getDoorById(int id)
	{
		for (Door door : doors)
		{
			if (door.getId() != id) continue;

			return door;
		}

		return null;
	}

	public void openDoor(Player player, Door door)
	{
		door.setOpened(true);
		playOpenAnimation(door);

		PlayerOpenDoorEvent event = new PlayerOpenDoorEvent(player, door);
		Bukkit.getPluginManager().callEvent(event);
	}

	public void playOpenAnimation(Door door)
	{

		ServerUtil.Log(Level.INFO, " ");
		for (Location location : door.getDoorBlockLocations())
		{
			location.getBlock().setType(Material.AIR);
			FallingBlock fallingBlock = location.getWorld().spawnFallingBlock(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ()), door.getDoorType().getDoorMaterial(), (byte) 0);
			fallingBlock.setDropItem(false);
			fallingBlock.setHurtEntities(false);
			for (Player player : Bukkit.getOnlinePlayers())
				player.spawnParticle(Particle.FIREWORKS_SPARK, location.getX(), location.getY(), location.getZ(), 10, 1, .6, .6, .6);
		}

		ServerUtil.Log(Level.INFO, " ");
	}

	@EventHandler
	public void fallingBlockForm(EntityChangeBlockEvent event)
	{

		ServerUtil.Log(Level.INFO, "FALLING BLOCK FORMED");

		if (event.getEntity().getType() != EntityType.FALLING_BLOCK) return;

		ServerUtil.Log(Level.INFO, "ENTITY = FALLING BLOCK");

		FallingBlock fallingBlock = (FallingBlock) event.getEntity();
		event.setCancelled(true);
		fallingBlock.remove();
	}

	@EventHandler
	public void doorInteraction(PlayerInteractEvent event)
	{
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.LEFT_CLICK_BLOCK) return;
		ServerUtil.Log(Level.INFO, "DEBUG 1");

		Player player = event.getPlayer();
		if (!player.isSneaking()) return;
		ServerUtil.Log(Level.INFO, "2");

		Block block = event.getClickedBlock();
		if (block == null || block.getType() == Material.AIR) return;
		ServerUtil.Log(Level.INFO, "3");

		for (DoorType doorType : DoorType.values())
		{
			if (block.getType() != doorType.getDoorMaterial()) continue;
			ServerUtil.Log(Level.INFO, "4");

			Door door = getDoorFromLocation(block.getLocation());
			if (door == null) continue;
			ServerUtil.Log(Level.INFO, "5");

			openDoor(player, door);
		}
	}

	@EventHandler
	public void doorOpen(PlayerOpenDoorEvent event)
	{
		ServerUtil.Log(Level.INFO, "Door opened!");
	}
}
