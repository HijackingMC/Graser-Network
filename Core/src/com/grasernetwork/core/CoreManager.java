package com.grasernetwork.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.grasernetwork.common.database.Database;
import com.grasernetwork.common.database.Mongo;
import com.grasernetwork.core.chat.ChatManager;
import com.grasernetwork.core.command.CommandManager;
import com.grasernetwork.core.monitor.MonitorManager;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.core.profile.friends.FriendManager;
import com.grasernetwork.core.punish.PunishManager;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ServerUtil;
import com.grasernetwork.util.inventory.MenuAPI;

public class CoreManager
{
	private Core _plugin;
	private Database _database = null;//TODO: Remove
	private ProfileManager _profileManager;
	private FriendManager _friendManager;
	private PunishManager _punishManager;
	private Mongo _mongo;

	public CoreManager(Core plugin)
	{
		_plugin = plugin;
		loadDependency("selenium");
		loadDependency("security");
		loadDependency("mongodb-driver-3.2.2");
		loadDependency("bson-3.2.2");
		loadDependency("mongo-java-driver-3.2.2");
		
		_mongo = new Mongo("test");
		_friendManager = new FriendManager(this);
		_punishManager = new PunishManager(this, _database);
		_profileManager = new ProfileManager(this, _friendManager, _database, _punishManager);
		new ChatManager(this);
		new CommandManager(this, _profileManager, _punishManager);
		new MonitorManager();
		
		MenuAPI.onEnable(_plugin);
	}

	public void shutdown(boolean manualShutdown)
	{
		HandlerList.unregisterAll(_profileManager);
		for(Player player : Bukkit.getOnlinePlayers())
		{
			_profileManager.saveProfile(player, true);
			player.kickPlayer(C.WhiteB + ServerUtil.SERVER + "\n- This server is restarting -");
		}

		if(manualShutdown)
		{
			Bukkit.getServer().shutdown();
		}

	}
	
	private void loadDependency(String dependicy)
	{
		URL external = null;
		try
		{
			external = new File(getPlugin().getDataFolder().getCanonicalFile() + "/../../../../Dependency/" + dependicy + ".jar").toURI().toURL();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		final Class<URLClassLoader> sysclass = URLClassLoader.class;
		java.lang.reflect.Method method = null;
		try
		{
			method = sysclass.getDeclaredMethod("addURL", URL.class);
		} catch (NoSuchMethodException | SecurityException e1)
		{
			e1.printStackTrace();
		}
		method.setAccessible(true);
		try
		{
			method.invoke(loader, new Object[] { external });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}

	public Core getPlugin()
	{
		return _plugin;
	}

	public Database getDatabase()
	{
		return _database;
	}

	public ProfileManager getProfileManager()
	{
		return _profileManager;
	}
	
	public Mongo getMongo()
	{
		return _mongo;
	}

	public FriendManager getFriendManager()
	{
		return _friendManager;
	}
}
