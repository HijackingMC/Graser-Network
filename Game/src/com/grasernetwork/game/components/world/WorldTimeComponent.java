package com.grasernetwork.game.components.world;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;

/**
 * Created by Teddeh on 08/03/2016.
 */
public class WorldTimeComponent extends Component
{
	public WorldTimeComponent(GamePlugin plugin, int time, String world)
	{
		super(plugin, true);

		if(Bukkit.getWorld(world) != null)
		{
			World w = Bukkit.getWorld(world);
			w.setTime(time);
			w.setGameRuleValue("doDaylightCycle", "false");
		}
	}
}
