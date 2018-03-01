package com.grasernetwork.game.components.map;

import com.grasernetwork.game.GamePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddeh on 08/03/2016.
 */
public class MapData
{
	private File file = null;
	private FileConfiguration fileConfiguration = null;

	public MapData(GamePlugin plugin, String game, String mapName)
	{
		try
		{
			file = new File(plugin.getDataFolder().getCanonicalFile() + "/../../../../Maps/" + game.toUpperCase() + "/" + mapName + "/mapdata.yml");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		fileConfiguration = YamlConfiguration.loadConfiguration(file);
	}

	public List<Location> getDataLocations(String color)
	{
		if(fileConfiguration == null)
			return null;

		if(!fileConfiguration.contains("Data." + color))
			return null;

		List<Location> temp = new ArrayList<Location>();
		fileConfiguration.getStringList("Data." + color).forEach(str -> temp.add(getLocationFromString(str)));

		return temp;
	}

	public List<Location> getSpawnLocations(String color)
	{
		if(fileConfiguration == null)
			return null;

		if(!fileConfiguration.contains("Spawns." + color))
			return null;

		List<Location> temp = new ArrayList<Location>();
		fileConfiguration.getStringList("Spawns." + color).forEach(str -> temp.add(getLocationFromString(str)));

		return temp;
	}

	public List<ChatColor> getTeams()
	{
		List<ChatColor> temp = new ArrayList<ChatColor>();
		if(fileConfiguration.contains("Spawns.ORANGE"))
			temp.add(ChatColor.GOLD);

		else if(fileConfiguration.contains("Spawns.PURPLE"))
			temp.add(ChatColor.LIGHT_PURPLE);

		else if(fileConfiguration.contains("Spawns.AQUA"))
			temp.add(ChatColor.AQUA);

		else if(fileConfiguration.contains("Spawns.YELLOW"))
			temp.add(ChatColor.YELLOW);

		else if(fileConfiguration.contains("Spawns.LIME"))
			temp.add(ChatColor.GREEN);

		else if(fileConfiguration.contains("Spawns.DARK_GRAY"))
			temp.add(ChatColor.DARK_GRAY);

		else if(fileConfiguration.contains("Spawns.GRAY"))
			temp.add(ChatColor.GRAY);

		else if(fileConfiguration.contains("Spawns.CYAN"))
			temp.add(ChatColor.BLUE);

		else if(fileConfiguration.contains("Spawns.DARK_PURPLE"))
			temp.add(ChatColor.DARK_PURPLE);

		else if(fileConfiguration.contains("Spawns.DARK_BLUE"))
			temp.add(ChatColor.DARK_BLUE);

		else if(fileConfiguration.contains("Spawns.DARK_GREEN"))
			temp.add(ChatColor.DARK_GREEN);

		else if(fileConfiguration.contains("Spawns.RED"))
			temp.add(ChatColor.RED);

		else if(fileConfiguration.contains("Spawns.BLACK"))
			temp.add(ChatColor.BLACK);

		else if(temp.isEmpty())
			return null;

		return temp;
	}

	public Location[] getBoarder()
	{
		if(fileConfiguration == null)
			return null;

		if(!fileConfiguration.contains("Boarder"))
			return null;

		List<String> boarder = fileConfiguration.getStringList("Boarder");
		return new Location[] { getLocationFromString(boarder.get(0)), getLocationFromString(boarder.get(1)) };
	}

	public Location getLocationFromString(String str)
	{
		if(str == null)
			return null;

		return new Location(Bukkit.getWorld(str.split(";")[0]), Integer.parseInt(str.split(";")[1]),
				Integer.parseInt(str.split(";")[2]), Integer.parseInt(str.split(";")[3]));
	}
}
