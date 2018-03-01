package com.grasernetwork.game.components.map;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.game.Game;
import com.grasernetwork.game.components.map.event.GameWorldGenerationEvent;
import com.grasernetwork.game.components.map.event.MapLoadEvent;
import com.grasernetwork.util.ServerUtil;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Teddeh on 08/03/2016.
 */
public class MapLoader
{
	private GamePlugin plugin;
	private Game game;

	public MapLoader(GamePlugin plugin, Game game)
	{
		this.plugin = plugin;
		this.game = game;
	}

	public void generateWorld(String worldName)
	{
		World world = Bukkit.getWorld(worldName);
		if (world == null)
		{
			WorldCreator creator = new WorldCreator(worldName);
			creator.environment(World.Environment.NORMAL);
			creator.generateStructures(true);
			world = creator.createWorld();
			world.setAutoSave(false);
			world.setTime(0);

			ServerUtil.Log(Level.INFO, worldName + " generated");
			GameWorldGenerationEvent event = new GameWorldGenerationEvent(worldName);
			Bukkit.getPluginManager().callEvent(event);
		}
	}

	/**
	 * Load map file for the selected game.
	 *
	 * @param mapName Name of the map.
	 */
	public void loadMap(String mapName)
	{
		try
		{
			loadMap(mapName, game.getName(), false);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private String loadMap(String mapName, String game, boolean fallback) throws IOException
	{
		plugin.getLogger().info("Copying template to game world.");
		File templateWorld = new File(plugin.getDataFolder().getCanonicalPath() + "/../../../../Maps/" + game.toUpperCase() + "/" + mapName);
		if (!templateWorld.exists() || !templateWorld.isDirectory())
		{
			plugin.getLogger().severe("Template world is not a directory or does not exist.");
			return null;
		}

		String gameWorldName = mapName;
		File gameWorld = new File(gameWorldName);
		if (gameWorld.exists())
		{
			try
			{
				FileUtils.deleteDirectory(gameWorld);
				if (gameWorld.exists() && gameWorld.isDirectory()) throw new IOException("gameWorld directory was not deleted.");
			}
			catch (IOException e)
			{
				plugin.getLogger().log(Level.SEVERE, "Unable to delete game world directory", e);
				if (fallback)
				{
					plugin.getLogger().warning("Failed fallback maps load: stopping");
					return null;
				}
				else
				{
					return loadMap(mapName, game, true);
				}
			}
		}

		try
		{
			FileUtils.copyDirectory(templateWorld, gameWorld);
		}
		catch (IOException e)
		{
			plugin.getLogger().log(Level.SEVERE, "Unable to copy template to game world", e);
			return null;
		}

		plugin.getLogger().info("Finished copying template to game world.");

		MapLoadEvent event = new MapLoadEvent(null, mapName);
		Bukkit.getPluginManager().callEvent(event);

		return gameWorldName;
	}

	/**
	 * @param map    Name of the map
	 * @param delete Remove the world files from the server.
	 */
	public void unloadWorldFile(String map, boolean delete)
	{
		final World gameWorld = Bukkit.getWorld(map);
		if (gameWorld != null)
		{
			if (Bukkit.unloadWorld(gameWorld, false))
			{
				plugin.getLogger().info(gameWorld.getName() + " successfully unloaded.");
				if (delete)
				{
					Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable()
					{
						private int tries = 0;

						public void run()
						{
							try
							{
								FileUtils.deleteDirectory(new File(gameWorld.getName()));
								plugin.getLogger().info("World folder deleted");
							}
							catch (IOException e)
							{
								plugin.getLogger().log(Level.SEVERE, "Unable to delete world directory (try: " + tries + ")", e);
								if (tries < 2)
								{
									tries++;
									plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, this, (tries + 1) * 20L);
								}
							}
						}
					}, 5L);
				}
			}
			else
				plugin.getLogger().severe("Unable to unload world" + gameWorld.getName());
		}
	}
}
