package com.grasernetwork.build.commands;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.bukkit.entity.Player;

import com.grasernetwork.build.BuildManager;
import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.PlayerUtil;
import com.grasernetwork.util.json.ClickEvent;
import com.grasernetwork.util.json.HoverEvent;
import com.grasernetwork.util.json.JsonBuilder;

public class MapsCommand extends CommandBase
{
	private BuildManager _manager;
	
	public MapsCommand(BuildManager manager)
	{
		super("maps", Rank.BUILDER, "/maps <gametype>", "List all maps for the selected game type.");
		
		_manager = manager;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		if(args.length < 1)
		{
			sendArgs(sender);
			return;
		}
		
		LinkedList<File> maps = new LinkedList<File>();
		
		File theDir = null;
		try
		{
			theDir = new File(_manager.getPlugin().getDataFolder().getCanonicalPath() + "/../../../maps/" + args[0].toUpperCase());
			if(!theDir.exists())
			{
				PlayerUtil.message(sender, "The gamemode directory does not exist, use %s to create %s directory.", new String[]{"/create <map name>", args[0]}, ChatType.ERROR);
				return;
			}
		}
		catch (IOException e)
		{
			PlayerUtil.message(sender, "An error has occurred, Try again..", ChatType.ERROR);
			e.printStackTrace();
		}
		
		for(File file : theDir.listFiles())
			maps.add(file);
		
		PlayerUtil.message(sender, C.Aqua + "Listing all maps (" + args[0] + "):");
		
		for(File file : maps)
		{
			PlayerUtil.messageJson(sender, new JsonBuilder(C.Green + file.getName())
					.hover(HoverEvent.SHOW_TEXT.toString(), "this is a hover message..")
					.click(ClickEvent.SUGGEST_COMMAND.toString(), "/" + file.getName()));
		}
	}
}
