package com.grasernetwork.lobby.profile.friend.command;

import org.bukkit.entity.Player;

import com.grasernetwork.core.command.type.PlayerCommand;
import com.grasernetwork.core.profile.friends.FriendManager;

/**
 * Created by TeddehDev on 17/01/2016.
 */
public class FriendCommand extends PlayerCommand
{
	private FriendManager _friendManager;

	public FriendCommand(FriendManager friendManager)
	{
		super("friend", "/friend <add / remove> <target>", "Add or Remove Friends.");

		_friendManager = friendManager;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		if(args.length < 2)
		{
			sendArgs(sender);
			return;
		}

		//code
	}
}
