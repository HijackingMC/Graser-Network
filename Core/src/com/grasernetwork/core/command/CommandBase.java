package com.grasernetwork.core.command;

import org.bukkit.entity.Player;

import com.grasernetwork.core.profile.Profile;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.C;
import com.grasernetwork.util.PlayerUtil;
import com.grasernetwork.util.json.ClickEvent;
import com.grasernetwork.util.json.HoverEvent;
import com.grasernetwork.util.json.JsonBuilder;

public abstract class CommandBase implements ICommand
{
	private String _command;
	private String[] _aliases;
	private String[] _args;
	private Rank _requiredRank;
	private String _usage;
	private String _description;

	public CommandBase(String command, Rank requiredRank, String usage, String description)
	{
		_command = command;
		_aliases = null;
		_args = getArgs();
		_requiredRank = requiredRank;
		_usage = usage;
		_description = description;

		CommandManager.Commands.put(_command, this);
	}

	public CommandBase(String command, String[] aliases, Rank requiredRank, String usage, String description)
	{
		_command = command;
		_aliases = aliases;
		_args = getArgs();
		_requiredRank = requiredRank;
		_usage = usage;
		_description = description;

		CommandManager.Commands.put(_command, this);
		for(String str : _aliases)
		{
			CommandManager.Commands.put(str, this);
		}
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		_args = args;
	}

	public String getCommand()
	{
		return _command;
	}

	public String[] getAliases()
	{
		return _aliases;
	}

	public String[] getArgs()
	{
		return _args;
	}

	public Rank getRequiredRank()
	{
		return _requiredRank;
	}
	
	public String getUsage()
	{
		return _usage;
	}
	
	public String getDescription()
	{
		return _description;
	}

	public boolean hasPermission(Profile profile)
	{
		return profile.getRank().getPriority() >= _requiredRank.getPriority();
	}
	
	public void sendArgs(Player player)
	{
		PlayerUtil.messageJson(player, new JsonBuilder(C.Red + "Insufficient arguments, Click here for an example.")
				.hover(HoverEvent.SHOW_TEXT.toString(), C.GrayI + "Click here for a " + _command + " example")
				.click(ClickEvent.SUGGEST_COMMAND.toString(), _usage));
	}
}
