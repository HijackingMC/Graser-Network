package com.grasernetwork.build;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.grasernetwork.build.commands.BuildCommand;
import com.grasernetwork.build.commands.MapsCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.grasernetwork.build.commands.CreateCommand;

public class BuildManager
{
	private Build _build;
	private File file = null;
	private FileConfiguration fileConfiguration = null;
	
	public BuildManager(Build build)
	{
		_build = build;
		
		new CreateCommand(this);
		new BuildCommand(this);
		new MapsCommand(this);

	}
	
	public Build getPlugin()
	{
		return _build;
	}
	
	public void compressMap(final Player player, final World world, final String gameMode, final int xRadius, final int zRadius)
	{
		if(world == null)
			return;
		
		if(world.getName() == null)
			return;
		
//		if(world.getName().equalsIgnoreCase("world"))
//		{
//			player.sendMessage("cannot compess world");
//			return;
//		}
		
		final String mapname = world.getName();
		world.save();
		
		boolean canPass = false;
		new BukkitRunnable()
		{
			public void run()
			{
				file = new File(_build.getDataFolder() + "/../../../maps/" + gameMode + "/" + mapname + "/mapdata.yml");
				fileConfiguration = YamlConfiguration.loadConfiguration(file);
				
				fileConfiguration.set("Details.Author", player.getName());
				fileConfiguration.set("Details.AuthorUUID", player.getUniqueId().toString());
				
				Location loc = null;
				for(int x = -xRadius; x < xRadius; x++)
				{
					for(int y = 0; y < 250; y++)
					{
						for(int z = -zRadius; z < zRadius; z++)
						{
							loc = new Location(world, x, y, z);
							if(loc.getBlock() == null)
								continue;
							
							if(loc.getBlock().getType() != Material.WOOL)
								continue;
							
							Block block = loc.getBlock();
							String l = loc.getWorld().getName() + ";" + x + ";" + y + ";" + z;
							switch (block.getData())
							{
							case 0:
								addLocation(fileConfiguration, l, "WHITE", block);
								break;
								
							case 1:
								addLocation(fileConfiguration, l, "ORANGE", block);
								break;
								
							case 2:
								addLocation(fileConfiguration, l, "PURPLE", block);
								break;
								
							case 3:
								addLocation(fileConfiguration, l, "AQUA", block);
								break;
								
							case 4:
								addLocation(fileConfiguration, l, "YELLOW", block);
								break;
								
							case 5:
								addLocation(fileConfiguration, l, "LIME", block);
								break;
								
							case 6:
								addLocation(fileConfiguration, l, "PINK", block);
								break;
								
							case 7:
								addLocation(fileConfiguration, l, "DARK_GRAY", block);
								break;
								
							case 8:
								addLocation(fileConfiguration, l, "GRAY", block);
								break;
								
							case 9:
								addLocation(fileConfiguration, l, "CYAN", block);
								break;
								
							case 10:
								addLocation(fileConfiguration, l, "DARK_PURPLE", block);
								break;
								
							case 11:
								addLocation(fileConfiguration, l, "DARK_BLUE", block);
								break;
								
							case 12:
								addLocation(fileConfiguration, l, "BROWN", block);
								break;
								
							case 13:
								addLocation(fileConfiguration, l, "DARK_GREEN", block);
								break;
								
							case 14:
								addLocation(fileConfiguration, l, "RED", block);
								break;
								
							case 15:
								addLocation(fileConfiguration, l, "BLACK", block);
								break;
							}
						}
					}
				}
				
				try
				{
					player.sendMessage("COMPLETE");
					fileConfiguration.save(file);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.runTask(_build);
	}
	
	public void addLocation(FileConfiguration config, String location, String color, Block block)
	{
		if(block.getRelative(BlockFace.UP).getType() == Material.GOLD_PLATE)
		{
			List<String> spawns = new ArrayList<String>();
			if(config.contains("Spawns." + color))
			{
				spawns = config.getStringList("Spawns." + color);
			}
			
			spawns.add(location);
			config.set("Spawns." + color, spawns);
		}
		
		if(block.getRelative(BlockFace.UP).getType() == Material.IRON_PLATE)
		{
			List<String> locs = new ArrayList<String>();
			if(color.equalsIgnoreCase("WHITE"))
			{
				if(config.contains("Boarder"))
				{
					locs = config.getStringList("Boarder");
				}
				
				locs.add(location);
				config.set("Boarder", locs);
				return;
			}
			
			if(config.contains("Data." + color))
			{
				locs = config.getStringList("Data." + color);
			}
			
			locs.add(location);
			config.set("Data." + color, locs);
		}
		
		block.getRelative(BlockFace.UP).setType(Material.AIR);
//		block.setType(Material.AIR);
	}
	
	public List<Location> getDataLocations(String color)
	{
		if(fileConfiguration == null)
			return null;
		
		if(!fileConfiguration.contains("Data." + color))
			return null;
		
		List<Location> temp = new ArrayList<Location>();
		for(String str : fileConfiguration.getStringList("Data." + color))
		{
			temp.add(getLocationFromString(str));
		}
		
		return temp;
	}
	
	public List<Location> getSpawnLocations(String color)
	{
		if(fileConfiguration == null)
			return null;
		
		if(!fileConfiguration.contains("Spawns." + color))
			return null;
		
		List<Location> temp = new ArrayList<Location>();
		for(String str : fileConfiguration.getStringList("Spawns." + color))
		{
			temp.add(getLocationFromString(str));
		}
		
		return temp;
	}
	
	public List<ChatColor> getTeams()
	{
		List<ChatColor> temp = new ArrayList<ChatColor>();
		if(fileConfiguration.contains("Spawns.ORANGE"))
			temp.add(ChatColor.GOLD);

		if(fileConfiguration.contains("Spawns.PURPLE"))
			temp.add(ChatColor.LIGHT_PURPLE);

		if(fileConfiguration.contains("Spawns.AQUA"))
			temp.add(ChatColor.AQUA);

		if(fileConfiguration.contains("Spawns.YELLOW"))
			temp.add(ChatColor.YELLOW);

		if(fileConfiguration.contains("Spawns.LIME"))
			temp.add(ChatColor.GREEN);

		if(fileConfiguration.contains("Spawns.DARK_GRAY"))
			temp.add(ChatColor.DARK_GRAY);

		if(fileConfiguration.contains("Spawns.GRAY"))
			temp.add(ChatColor.GRAY);

		if(fileConfiguration.contains("Spawns.CYAN"))
			temp.add(ChatColor.BLUE);

		if(fileConfiguration.contains("Spawns.DARK_PURPLE"))
			temp.add(ChatColor.DARK_PURPLE);

		if(fileConfiguration.contains("Spawns.DARK_BLUE"))
			temp.add(ChatColor.DARK_BLUE);

		if(fileConfiguration.contains("Spawns.DARK_GREEN"))
			temp.add(ChatColor.DARK_GREEN);

		if(fileConfiguration.contains("Spawns.RED"))
			temp.add(ChatColor.RED);

		if(fileConfiguration.contains("Spawns.BLACK"))
			temp.add(ChatColor.BLACK);
		
		if(temp.isEmpty())
			return null;
		
		return temp;
	}
	
	public Location[] getBoarder(String color)
	{
		if(fileConfiguration == null)
			return null;
		
		if(!fileConfiguration.contains("Spawns." + color))
			return null;
		
		List<String> boarder = fileConfiguration.getStringList("Data." + color);
		return new Location[] { getLocationFromString(boarder.get(0)), getLocationFromString(boarder.get(1)) };
	}
	
	public Location getLocationFromString(String str)
	{
		if(str == null)
			return null;
		
		if(str.isEmpty())
			return null;
		
		return new Location(Bukkit.getWorld(str.split(";")[0]), Double.parseDouble(str.split(";")[1]), 
				Double.parseDouble(str.split(";")[2]), Double.parseDouble(str.split(";")[3]));
	}
}
