package com.grasernetwork.core.command.general;

import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.core.rank.Rank;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CapeCommand extends CommandBase
{
	private JavaPlugin _plugin;
	private ProfileManager _profileManager;
	
	public CapeCommand(JavaPlugin plugin, ProfileManager profileManager)
	{
		super("cape", Rank.ALL, "/cape <?>", "Give yourself a cape.");
		
		_plugin = plugin;
		_profileManager = profileManager;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		if(args.length < 1)
		{
			sendArgs(sender);
			return;
		}
	}
}