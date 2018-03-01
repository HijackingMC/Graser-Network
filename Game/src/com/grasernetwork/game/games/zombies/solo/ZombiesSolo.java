package com.grasernetwork.game.games.zombies.solo;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.map.MapData;
import com.grasernetwork.game.games.zombies.Zombies;
import com.grasernetwork.game.games.zombies.door.Door;
import com.grasernetwork.game.games.zombies.door.DoorManager;
import com.grasernetwork.game.games.zombies.door.DoorType;
import com.grasernetwork.util.ServerUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Teddeh on 30/03/2016.
 */
public class ZombiesSolo extends Zombies
{
	private GamePlugin plugin;
	private DoorManager doorManager;

	public ZombiesSolo(GamePlugin plugin)
	{
		super(plugin, 10);

		this.plugin = plugin;
		this.doorManager = new DoorManager(plugin);
	}

	@Override
	public void initialize()
	{

	}

	@Override
	public void initDataPoints()
	{
		MapData mapData = plugin.getGameManager().getMapData();
		System.out.println(mapData.getDataLocations("RED").size());
		System.out.println(mapData.getDataLocations("AQUA").size());
		System.out.println(mapData.getDataLocations("ORANGE").size());

		//Door initialisation
		for (Location location : mapData.getDataLocations("RED"))
		{
			BlockFace face = getCorrectFace(location);
			if (face == null) continue;

			Sign sign = (Sign) location.getBlock().getRelative(face).getState();
			if (sign == null) continue;

			//Collect data located on sign. (price, size, id, roomIds)
			int price = Integer.parseInt(sign.getLine(0));
			int[] size = new int[]{Integer.parseInt(sign.getLine(1).split("x")[0]), Integer.parseInt(sign.getLine(1).split("x")[1])};
			int id = Integer.parseInt(sign.getLine(2));
			List<Integer> roomIdTemp = new ArrayList<>();
			Arrays.asList(sign.getLine(3).split(",")).forEach(d -> roomIdTemp.add(Integer.parseInt(d)));
			int[] roomIds = new int[roomIdTemp.size()];
			for (int i = 0; i < roomIdTemp.size(); i++) roomIds[i] = roomIdTemp.get(i);

			//Remove sign.
			sign.getBlock().setType(Material.AIR);

			Door door = new Door(id, price, roomIds, size, face, DoorType.WOODEN_FENCE);//TODO DoorType
			doorManager.addDoor(door);
			location.getBlock().setType(door.getDoorType().getDoorMaterial());

			/**
			 * north (x = -) (y = +)
			 * east (z = -) (y = +)
			 * south (x = +) (y = +)
			 * west (z = +) (y = +)
			 */
			Location doorLoc = null;
			for (int y = 0; y < door.getSize()[0]; y++)
			{
				for (int i = 0; i < door.getSize()[1]; i++)
				{
					if (face == BlockFace.NORTH) doorLoc = location.add(-i, y, 0);
					else if (face == BlockFace.EAST) doorLoc = location.add(0, y, -i);
					else if (face == BlockFace.SOUTH) doorLoc = location.add(i, y, 0);
					else if (face == BlockFace.WEST) doorLoc = location.add(0, y, i);

					if (doorLoc == null) continue;
					if (doorLoc.getBlock().getType() == Material.AIR)
						doorLoc.getBlock().setType(door.getDoorType().getDoorMaterial());

					door.addDoorBlock(doorLoc.getBlock().getLocation());
					ServerUtil.Log(Level.INFO, doorLoc.getX() + " " + doorLoc.getY() + " " + doorLoc.getZ());

					if (face == BlockFace.NORTH) doorLoc = location.subtract(-i, y, 0);
					else if (face == BlockFace.EAST) doorLoc = location.subtract(0, y, -i);
					else if (face == BlockFace.SOUTH) doorLoc = location.subtract(i, y, 0);
					else if (face == BlockFace.WEST) doorLoc = location.subtract(0, y, +i);
				}
			}
		}

		for (Location location : mapData.getDataLocations("AQUA"))
		{
			//guns
		}

		for (Location location : mapData.getDataLocations("ORANGE"))
		{
			//zombie spawns
		}

//		ServerUtil.Log(Level.INFO, " ");
//		ServerUtil.Log(Level.INFO, "Listing all doors:");
//		doors.forEach(door -> ServerUtil.Log(Level.INFO, "Door: " + door.getId()));
//		ServerUtil.Log(Level.INFO, " ");

		plugin.getGameManager().cleanDataPoints(new String[]{});
	}

	public BlockFace getCorrectFace(Location location)
	{
		for (BlockFace face : new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST})
		{
			Block block = location.getBlock();
			if (block.getRelative(face) == null || block.getRelative(face).getType() == Material.AIR) continue;

			if (!(block.getRelative(face).getState() instanceof Sign)) continue;

//			return ((Sign) block.getRelative(face).getState());
			return face;
		}

		return null;
	}
}
