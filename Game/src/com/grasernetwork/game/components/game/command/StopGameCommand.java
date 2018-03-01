package com.grasernetwork.game.components.game.command;

import com.grasernetwork.core.command.type.AdminCommand;
import com.grasernetwork.game.components.game.GameManager;
import com.grasernetwork.game.components.game.gamestate.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Teddeh on 24/03/2016.
 */
public class StopGameCommand extends AdminCommand
{
	private GameManager manager;

	public StopGameCommand(GameManager manager)
	{
		super("game", "/game stop", "Force stop the current game.");

		this.manager = manager;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		if(args.length < 1)
		{
			sendArgs(sender);
			return;
		}

//		if(!args[0].equalsIgnoreCase("stop"))
//			return;

		manager.getCurrentGame().setGameState(GameState.DEAD);
		Bukkit.broadcastMessage("GAME STOPPING, K M8?");
	}
}
