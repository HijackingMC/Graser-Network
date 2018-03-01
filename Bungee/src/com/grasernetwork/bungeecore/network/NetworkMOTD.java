package com.grasernetwork.bungeecore.network;

import net.md_5.bungee.api.ChatColor;

public enum NetworkMOTD
{
	NORMAL(new String[]
			{
			"" + ChatColor.GREEN + ChatColor.BOLD + "Network",
			"" + ChatColor.WHITE + ChatColor.ITALIC + "In Development",
			}
	),

	SCHEDULED_MAINTENENCE(new String[]
			{
			"" + ChatColor.DARK_RED + ChatColor.BOLD + "Scheduled Maintenence",
			"" + ChatColor.WHITE + ChatColor.ITALIC + "This is a planned maintenence",
			}
	),
	
	UNSCHEDULED_MAINTENENCE(new String[]
			{
			"" + ChatColor.DARK_RED + ChatColor.BOLD + "Unscheduled Maintenence",
			"" + ChatColor.WHITE + ChatColor.ITALIC + "More information can be found on the forums",
			}
	),
	
	
	EASTER(new String[]
			{
			"" + ChatColor.GREEN + ChatColor.BOLD + "Network",
			"" + ChatColor.YELLOW + ChatColor.BOLD + "HAPPY EASTER "
			}
	);
	
	private String[] _motd;
	
	NetworkMOTD(String[] motd)
	{
		_motd = motd;
	}
	
	public String getMotd()
	{
		String lines = _motd[0] +"\n" + _motd[1];
		return lines;
	}
	
	
	
}
