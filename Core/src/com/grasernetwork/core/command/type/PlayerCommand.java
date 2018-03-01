package com.grasernetwork.core.command.type;

import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.rank.Rank;

/**
 * Created by TeddehDev on 14/01/2016.
 */
public abstract class PlayerCommand extends CommandBase
{

	public PlayerCommand(String command, String usage, String description)
	{
		super(command, Rank.ALL, usage, description);
	}
}
