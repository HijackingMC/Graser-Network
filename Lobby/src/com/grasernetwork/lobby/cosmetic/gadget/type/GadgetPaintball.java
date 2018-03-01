package com.grasernetwork.lobby.cosmetic.gadget.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.grasernetwork.lobby.cosmetic.gadget.Gadget;
import com.grasernetwork.lobby.cosmetic.gadget.data.PaintballData;
import com.grasernetwork.lobby.cosmetic.gadget.misc.Ammo;
import com.grasernetwork.lobby.cosmetic.gadget.misc.Cooldown;
import com.grasernetwork.lobby.cosmetic.gadget.misc.TriggerType;
import com.grasernetwork.lobby.cosmetic.misc.RarityType;
import com.grasernetwork.lobby.cosmetic.misc.Unlockable;

public class GadgetPaintball extends Gadget implements Unlockable, Ammo, Cooldown
{
	private List<Material> _ignore = new ArrayList<Material>();

	public GadgetPaintball()
	{
		super(
				"Paintball",
				Material.IRON_BARDING,
				TriggerType.LEFT_CLICK,
				RarityType.LEGENDARY,
				5000
		);

		_ignore = Arrays.asList(
				Material.STONE_SLAB2,
				Material.ACACIA_STAIRS,
				Material.BIRCH_WOOD_STAIRS,
				Material.BRICK_STAIRS,
				Material.COBBLESTONE_STAIRS,
				Material.DARK_OAK_STAIRS,
				Material.JUNGLE_WOOD_STAIRS,
				Material.NETHER_BRICK_STAIRS,
				Material.QUARTZ_STAIRS,
				Material.RED_SANDSTONE_STAIRS,
				Material.SANDSTONE_STAIRS,
				Material.SMOOTH_STAIRS,
				Material.SPRUCE_WOOD_STAIRS,
				Material.WOOD_STAIRS,
				Material.COBBLE_WALL,
				Material.WALL_BANNER,
				Material.SIGN,
				Material.SIGN_POST,
				Material.WALL_SIGN,
				Material.SAPLING,
				Material.WEB,
				Material.LONG_GRASS,
				Material.DEAD_BUSH,
				Material.YELLOW_FLOWER,
				Material.RED_ROSE,
				Material.FLOWER_POT,
				Material.RED_MUSHROOM,
				Material.BROWN_MUSHROOM,
				Material.TORCH,
				Material.CHEST,
				Material.LADDER,
				Material.SNOW,
				Material.SNOW_BLOCK,
				Material.CACTUS,
				Material.FENCE,
				Material.FENCE_GATE,
				Material.ACACIA_FENCE,
				Material.BIRCH_FENCE,
				Material.DARK_OAK_FENCE,
				Material.IRON_FENCE,
				Material.JUNGLE_FENCE,
				Material.NETHER_FENCE,
				Material.SPRUCE_FENCE,
				Material.ACACIA_FENCE_GATE,
				Material.BIRCH_FENCE_GATE,
				Material.DARK_OAK_FENCE_GATE,
				Material.JUNGLE_FENCE_GATE,
				Material.SPRUCE_FENCE_GATE,
				Material.VINE,
				Material.WATER_LILY,
				Material.ENCHANTMENT_TABLE,
				Material.ENDER_PORTAL_FRAME,
				Material.ENDER_PORTAL,
				Material.ENDER_CHEST,
				Material.ANVIL,
				Material.TRAPPED_CHEST,
				Material.STAINED_GLASS_PANE,
				Material.SLIME_BLOCK,
				Material.CARPET,
				Material.DOUBLE_PLANT,
				Material.ITEM_FRAME,
				Material.BED,
				Material.PAINTING,
				Material.SKULL,
				Material.STONE_BUTTON,
				Material.WOOD_BUTTON,
				Material.REDSTONE,
				Material.TRAP_DOOR,
				Material.REDSTONE_TORCH_ON,
				Material.REDSTONE_TORCH_OFF,
				Material.LEVER,
				Material.GOLD_PLATE,
				Material.IRON_PLATE,
				Material.STONE_PLATE,
				Material.WOOD_PLATE,
				Material.HOPPER,
				Material.DAYLIGHT_DETECTOR,
				Material.DAYLIGHT_DETECTOR_INVERTED,
				Material.FURNACE,
				Material.WORKBENCH,
				Material.JUKEBOX,
				Material.NOTE_BLOCK,
				Material.TNT,
				Material.ACACIA_DOOR,
				Material.BIRCH_DOOR,
				Material.DARK_OAK_DOOR,
				Material.IRON_DOOR,
				Material.JUNGLE_DOOR,
				Material.SPRUCE_DOOR,
				Material.WOOD_DOOR,
				Material.WOODEN_DOOR,
				Material.IRON_TRAPDOOR,
				Material.RAILS,
				Material.ACTIVATOR_RAIL,
				Material.DETECTOR_RAIL,
				Material.POWERED_RAIL,
				Material.BEACON,
				Material.DIAMOND_BLOCK,
				Material.EMERALD_BLOCK,
				Material.IRON_BLOCK,
				Material.GOLD_BLOCK,
				Material.REDSTONE_BLOCK,
				Material.BREWING_STAND,
				Material.CAULDRON,
				Material.ICE,
				Material.PACKED_ICE,
				Material.STAINED_GLASS,
				Material.GLOWSTONE,
				Material.JACK_O_LANTERN,
				Material.DIAMOND_ORE,
				Material.GOLD_ORE,
				Material.IRON_ORE,
				Material.REDSTONE_ORE,
				Material.COAL_ORE,
				Material.EMERALD_ORE,
				Material.MELON_STEM,
				Material.PUMPKIN_STEM,
				Material.CROPS,
				Material.CARROT,
				Material.POTATO,
				Material.COCOA,
				Material.CAKE_BLOCK,
				Material.DISPENSER,
				Material.DROPPER,
				Material.LAPIS_BLOCK,
				Material.LAPIS_ORE,
				Material.SPONGE,
				Material.BOOKSHELF,
				Material.SOUL_SAND,
				Material.SAND,
				Material.WOOL,
				Material.GRAVEL
		);
	}

	@Override
	public int getMaxAmmo()
	{
		return 9999;
	}

	@Override
	public int getAmmoPerUse()
	{
		return 1;
	}

	@Override
	public double getRarity()
	{
		return 0.1;
	}

	@Override
	public double getCooldown()
	{
		return 2.0;
	}

	@Override
	public void trigger(Player player)
	{
		player.launchProjectile(Snowball.class);
	}

	@SuppressWarnings("all")
	@EventHandler
	public void paintballHit(ProjectileHitEvent event)
	{
		if (!(event.getEntity() instanceof Snowball))
			return;

		Snowball snowball = (Snowball) event.getEntity();

		if (event.getEntity().getShooter() == null)
			return;

		if (!(event.getEntity().getShooter() instanceof Player))
			return;

		final HashMap<Location, PaintballData> _effected = new HashMap<Location, PaintballData>();

		Block block = snowball.getLocation().getBlock();
		List<Location> blockLocs = circle(block.getLocation(), 3, 3, false, true, 0);
		int data = 1 + (new Random().nextInt(10));
		for (Location location : blockLocs)
		{
			boolean canPass = true;
			for(Material m : _ignore)
			{
				if (location.getBlock().getType() == m)
				{
					canPass = false;
					break;
				}
			}

			if(!canPass)
				break;

			_effected.put(location, new PaintballData(location.getBlock().getType(), Material.STAINED_CLAY, location.getBlock().getData(), (byte) data));
			location.getBlock().setType(Material.STAINED_CLAY);
			location.getBlock().setData((byte) data);
		}

		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				if (_effected.isEmpty())
				{
					cancel();
					return;
				}

				for (Location loc : _effected.keySet())
				{
					loc.getBlock().setType(_effected.get(loc).getOriginalMaterial());
					loc.getBlock().setData(_effected.get(loc).getOriginalData());
					_effected.remove(loc);
					break;
				}
			}
		}.runTaskTimer(getManager().getPlugin(), 100, 1);
	}

	private List<Location> circle(Location loc, Integer r, Integer h, Boolean hollow, Boolean sphere, int plus_y)
	{
		List<Location> circleblocks = new ArrayList<Location>();
		int cx = loc.getBlockX();
		int cy = loc.getBlockY();
		int cz = loc.getBlockZ();
		for (int x = cx - r; x <= cx + r; x++)
		{
			for (int z = cz - r; z <= cz + r; z++)
			{
				for (int y = (sphere ? cy - r : cy); y < (sphere ? cy + r : cy + h); y++)
				{
					double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
					if (dist < r * r && !(hollow && dist < (r - 1) * (r - 1)))
					{
						Location l = new Location(loc.getWorld(), x, y + plus_y, z);
						if (!circleblocks.contains(l.getBlock().getLocation()))
						{
							if (l.getBlock().getType().isSolid() && l.getBlock().getType() != Material.STAINED_CLAY)
							{
								circleblocks.add(l.getBlock().getLocation());
							}
						}
					}
				}
			}
		}

		return circleblocks;
	}
}
