package com.grasernetwork.core.command.general;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.command.CommandManager;
import com.grasernetwork.core.command.type.PlayerCommand;
import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.util.C;
import com.grasernetwork.util.PlayerUtil;
import com.grasernetwork.util.json.ClickEvent;
import com.grasernetwork.util.json.HoverEvent;
import com.grasernetwork.util.json.JsonBuilder;

public class HelpCommand extends PlayerCommand
{
	private ProfileManager _profileManager;
	
	public HelpCommand(ProfileManager profileManager)
	{
		super("help", "/help <page>", "See the servers help");
		
		_profileManager = profileManager;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		Profile profile = _profileManager.getProfile(sender);
		
		PlayerUtil.message(sender, "Listing all commands you have access to:");
		
		List<String> displayed = new ArrayList<String>();
		for(CommandBase command : CommandManager.Commands.values())
		{
			if(displayed.contains(command.getCommand()))
				continue;
			
			if(command.hasPermission(profile))
			{
				PlayerUtil.messageJson(sender, new JsonBuilder(C.Green + command.getCommand() + C.White + " - " + C.GrayI + command.getDescription())
					.hover(HoverEvent.SHOW_TEXT.toString(), "this is a hover message..")
					.click(ClickEvent.SUGGEST_COMMAND.toString(), "/" + command.getCommand()));
				
				displayed.add(command.getCommand());
			}
		}
		
//		PlayerUtil.messageJson(sender, );
		displayed.clear();
	}
}
