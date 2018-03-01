package com.grasernetwork.core.command.general;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.rank.Rank;

public class TempCommand extends CommandBase
{
	private JavaPlugin _plugin;

	public TempCommand(JavaPlugin plugin)
	{
		super("test", Rank.ADMIN, "/temp", "Will take you too Yemn's build.");

		_plugin = plugin;
	}

	@Override
	public void execute(Player sender, String[] args)
	{
		Location start = sender.getEyeLocation();
		Vector increase = start.getDirection();
		for (int counter = 0; counter < 100; counter++)
		{
			Location point = start.add(increase);
			sender.spawnParticle(Particle.FIREWORKS_SPARK, point.getX(),
					point.getY(), point.getZ(), 1, 0, 0, 0, 0);

			boolean entFound = false;
			for (Entity ent : point.getWorld().getEntities())
			{
				if (!(ent instanceof Player))
					continue;

				Player target = ((Player) ent);
				if (target.equals(sender))
					continue;

				if (ent.getLocation().distance(point) < 2)
				{
					sender.sendMessage(target.getName() + " was in sight!");
					entFound = true;
					break;
				}
			}

			if (entFound)
				break;
		}
	}
}
