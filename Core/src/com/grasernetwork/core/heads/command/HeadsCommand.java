package com.grasernetwork.core.heads.command;

import org.bukkit.entity.Player;

import com.grasernetwork.core.command.type.PlayerCommand;
import com.grasernetwork.core.heads.gui.HeadPortalMenu;
import com.grasernetwork.util.inventory.MenuAPI;

public class HeadsCommand extends PlayerCommand
{
	public HeadsCommand()
	{
		super("heads", "/heads", "Open up a GUI of different heads.");
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		MenuAPI.openMenu(sender, new HeadPortalMenu(sender));
	}
}
