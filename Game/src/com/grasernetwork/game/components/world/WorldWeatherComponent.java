package com.grasernetwork.game.components.world;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.Component;
import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.World;

/**
 * Created by Teddeh on 08/03/2016.
 */
public class WorldWeatherComponent extends Component
{
	public WorldWeatherComponent(GamePlugin plugin, String world, WeatherType weather)
	{
		super(plugin, true);

		if(Bukkit.getWorld(world) != null)
		{
			World w = Bukkit.getWorld(world);
			w.setWeatherDuration(99999);
			if(weather == WeatherType.CLEAR)
			{
				w.setStorm(false);
				w.setThundering(false);
			}
			else
			{
				w.setStorm(true);
				w.setThundering(true);
			}

			w.setWeatherDuration(99999);
		}
	}
}
