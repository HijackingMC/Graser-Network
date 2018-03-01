package com.grasernetwork.build.commands;

import org.bukkit.entity.Player;

import com.grasernetwork.build.BuildManager;
import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.C;
import com.grasernetwork.util.PlayerUtil;

public class BuildCommand extends CommandBase
{
	private BuildManager _manager;
	
	public BuildCommand(BuildManager manager)
	{
		super("build", Rank.BUILDER, "/build", "Compile and save a finished build.");
		
		_manager = manager;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		if (args.length != 1)
			sendArgs(sender);

		_manager.compressMap(sender, sender.getWorld(), args[0], 100, 100);//TODO: 100x100
		PlayerUtil.message(sender, C.Aqua + "compression started.");
	}
}
