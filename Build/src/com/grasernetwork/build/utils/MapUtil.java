package com.grasernetwork.build.utils;

import org.apache.commons.io.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by Teddeh on 11/03/2016.
 */
public class MapUtil
{
	public static void loadWorld(JavaPlugin plugin, String mapName, String path)
	{
		try
		{
			_loadMap(plugin, mapName, false, path);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static String _loadMap(JavaPlugin plugin, String mapName, boolean fallback, String path)
			throws IOException
	{
		plugin.getLogger().info("Copying template to game world.");
		File templateWorld = new File(plugin.getDataFolder().getCanonicalPath() + path);
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
				if (gameWorld.exists() && gameWorld.isDirectory())
					throw new IOException("gameWorld directory was not deleted.");
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
					return _loadMap(plugin, mapName, true, path);
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
		return gameWorldName;
	}
}
