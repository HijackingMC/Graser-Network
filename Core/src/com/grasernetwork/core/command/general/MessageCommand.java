package com.grasernetwork.core.command.general;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.grasernetwork.core.command.type.PlayerCommand;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.PlayerUtil;

public class MessageCommand extends PlayerCommand
{
	public MessageCommand()
	{
		super("msg", "/msg <username> <message>", "Message another user on the server.");
	}

	@Override
	public void execute(final Player sender, String[] args)
	{
		if (args.length < 2)
		{
			sendArgs(sender);
			return;
		}

		Player target = Bukkit.getPlayerExact(args[0]);
		if (target == null)
		{
			PlayerUtil.message(sender, "Player '%s' cannot be found.",
					new String[] { args[0] }, ChatType.ERROR);

			return;
		}

		if (target == sender)
		{
			PlayerUtil.message(sender,
					"You cannot send messages to yourself silly",
					ChatType.COMMAND);
			return;
		}

		String message = C.WhiteB;
		for (int i = 1; i < args.length; i++)
		{
			message += args[i] + (i == args.length ? "" : " ");
		}

		PlayerUtil.message(sender, C.Aqua + "[" + C.Red + "me" + C.Aqua + " @ " + C.Red + target.getName() + C.Aqua + "]" + C.Reset + " " + message);
		PlayerUtil.message(target, C.Aqua + "[" + C.Red + sender.getName() + C.Aqua + " @ " + C.Red + "me" + C.Aqua + "]" + C.Reset + " " + message);
	}
}

