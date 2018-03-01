package com.grasernetwork.core.command.general;

import org.bukkit.entity.Player;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.rank.Rank;

public class StopCommand extends CommandBase
{
	private CoreManager _manager;
	public StopCommand(CoreManager manager)
	{
		super("stop", Rank.ADMIN, "/stop", "Shuts down the server.");
		
		_manager = manager;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		_manager.shutdown(true);
	}
}
