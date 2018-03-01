package com.grasernetwork.core.command.general;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.PlayerUtil;

public class TeleportCommand extends CommandBase
{
	public TeleportCommand()
	{
		super("teleport", Rank.MODERATOR, "/teleport <player> <player>", "Update your gamemode.");
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		if (args.length == 1)
		{
			Player target1 = Bukkit.getPlayer(args[0]);
			
			if (target1 == null)
			{
				PlayerUtil.message(sender, "%s is not online!", new String[]{ args[0] }, ChatType.ERROR);
				return;
			}
			
			sender.teleport(target1);
			PlayerUtil.message(sender, "You have been teleported to %s!", new String[]{ args[0] }, ChatType.ERROR);
			return;
		}
		
		else if (args.length == 2)
		{
			Player target1 = Bukkit.getPlayer(args[0]);
			Player target2 = Bukkit.getPlayer(args[1]);
			
			if (target1 == null)
			{
				PlayerUtil.message(sender, "%s is not online!", new String[]{ args[0] }, ChatType.ERROR);
				return;
			}
			
			else if (target2 == null)
			{
				PlayerUtil.message(sender, "%s is not online!", new String[]{ args[1] }, ChatType.ERROR);
				return;
			}
			
			target1.teleport(target2);
			return;
		}
		
		sendArgs(sender);
	}
}
