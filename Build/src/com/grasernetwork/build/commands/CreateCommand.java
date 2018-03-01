 package com.grasernetwork.build.commands;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;

import com.grasernetwork.build.utils.MapUtil;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.grasernetwork.build.BuildManager;
import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.rank.Rank;

public class CreateCommand extends CommandBase
{
	private BuildManager _manager;
	
	public CreateCommand(BuildManager manager)
	{
		super("create", Rank.BUILDER, "/create <map name>", "Create an empty new world");
		
		_manager = manager;
	}

	@Override
	public void execute(final Player sender, final String[] args)
	{
		if(args.length < 1)
		{
			sender.teleport(new Location(Bukkit.getWorld("ParkourTest"), 0, 20, 0));
			sendArgs(sender);
			return;
		}
		
		File theDir = null;
		try
		{
			theDir = new File(_manager.getPlugin().getDataFolder().getCanonicalPath() + "/../../" + args[0]);
			if(!theDir.exists())
			{
				theDir.mkdir();
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sender.sendMessage("World files loading..");
		MapUtil.loadWorld(_manager.getPlugin(), args[0], "/../../../../maps/BUILDER/DefaultBase");

		sender.sendMessage("World terrain loading..");
		new BukkitRunnable()
		{
			public void run()
			{
				World world = Bukkit.getWorld(args[0]);
		        if (world == null)
		        {
		            WorldCreator creator = new WorldCreator(args[0]);
		            creator.environment(World.Environment.NORMAL);
		            creator.generateStructures(true);
		            world = creator.createWorld();
		            world.setAutoSave(false);
		            world.setSpawnLocation(0, 6, 0);

		            _manager.getPlugin().getLogger().info("Build World generated, " + args[0]);
		        }

				sender.sendMessage("Teleporting to " + args[0]);
		        sender.teleport(world.getSpawnLocation().add(0, 10, 0));
			}
		}.runTaskLater(_manager.getPlugin(), 20L);
	}
}
