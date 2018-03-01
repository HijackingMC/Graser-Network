package com.grasernetwork.game.games;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.game.Game;
import com.grasernetwork.game.components.game.GameType;
import com.grasernetwork.game.components.game.gamestate.GameState;
import com.grasernetwork.game.components.player.inventory.ItemPickupComponent;

/**
 * Created by Teddeh on 08/03/2016.
 */
public class TestGame extends Game
{
	private GamePlugin plugin;

	public TestGame(GamePlugin plugin)
	{
		super(
				plugin,                  // Plugin
				1,                       // Game ID
				"Test Game",             // Game Name
				12,                      // Game player cap
				GameType.STANDALONE);    // Game type

		this.plugin = plugin;
	}

	@Override
	public void initialize()
	{
		ItemPickupComponent itemPickup = new ItemPickupComponent(plugin, true, false);
		addComponent(GameState.WAITING, itemPickup);
	}
}
