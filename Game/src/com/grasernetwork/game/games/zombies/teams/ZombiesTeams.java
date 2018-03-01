package com.grasernetwork.game.games.zombies.teams;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.game.Teams;
import com.grasernetwork.game.games.zombies.Zombies;

/**
 * Created by Teddeh on 30/03/2016.
 */
@Teams
public class ZombiesTeams extends Zombies
{
	public ZombiesTeams(GamePlugin plugin)
	{
		super(plugin, 24);
	}

	@Override
	public void initialize()
	{

	}
}
