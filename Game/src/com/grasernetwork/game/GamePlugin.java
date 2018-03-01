package com.grasernetwork.game;

import com.grasernetwork.core.Core;
import com.grasernetwork.game.components.game.GameManager;
import com.grasernetwork.game.components.game.command.StopGameCommand;

public class GamePlugin extends Core
{

	private GameManager gameManager;

	@Override
	public void enable()
	{
		gameManager = new GameManager(this);
		gameManager.initialize(GameManager.Gamemode.ZOMBIES);

		new StopGameCommand(gameManager);
	}

	public GameManager getGameManager()
	{
		return gameManager;
	}
}
