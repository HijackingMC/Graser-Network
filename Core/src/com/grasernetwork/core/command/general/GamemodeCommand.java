package com.grasernetwork.core.command.general;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.PlayerUtil;

public class GamemodeCommand extends CommandBase
{
	public GamemodeCommand()
	{
		super("gamemode", Rank.DEVELOPER, "/gamemode <gamemode type>", "Update your gamemode.");
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		if(args.length < 1)
		{
			if(sender.getGameMode() != GameMode.CREATIVE)
				sender.setGameMode(GameMode.CREATIVE);
			else
				sender.setGameMode(GameMode.SURVIVAL);

			PlayerUtil.message(sender, "Your Gamemode has been updated to %s.", new String[]{sender.getGameMode().toString()}, ChatType.SUCCESS);
			return;
		}

		if(args.length >= 1)
		{
			String arg = args[0];
			if(arg.equalsIgnoreCase("S") || arg.equalsIgnoreCase("SURVIVAL") || arg.equalsIgnoreCase("0"))
				sender.setGameMode(GameMode.SURVIVAL);
			else if(arg.equalsIgnoreCase("C") || arg.equalsIgnoreCase("CREATIVE") || arg.equalsIgnoreCase("1"))
				sender.setGameMode(GameMode.CREATIVE);
			else if(arg.equalsIgnoreCase("A") || arg.equalsIgnoreCase("ADVENTURE") || arg.equalsIgnoreCase("2"))
				sender.setGameMode(GameMode.ADVENTURE);
			else if(arg.equalsIgnoreCase("SPEC") || arg.equalsIgnoreCase("SPECTATOR") || arg.equalsIgnoreCase("3"))
				sender.setGameMode(GameMode.SPECTATOR);
			else
				sender.setGameMode(GameMode.CREATIVE);

			PlayerUtil.message(sender, "Your Gamemode has been updated to %s.", new String[]{sender.getGameMode().toString()}, ChatType.SUCCESS);
		}
	}
}
