package com.grasernetwork.util;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class ServerUtil
{

	public static final String SERVER = "Graser Network";
	public static final String WEBSITE = "www.grasernetwork.com/forums";
	public static final String STORE = "www.store.grasernetwork.com";

	public static void Log(Level level, String message)
	{
		Bukkit.getServer().getLogger().log(level, "[" + SERVER + "] " + message);
	}
}
