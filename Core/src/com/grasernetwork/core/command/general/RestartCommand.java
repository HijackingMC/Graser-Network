package com.grasernetwork.core.command.general;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.core.command.CommandBase;
import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.C;
import com.grasernetwork.util.TextUtil;
import com.grasernetwork.util.packet.TitleMiddle;

public class RestartCommand extends CommandBase
{
	private JavaPlugin _plugin;
	private CoreManager _manager;
	
	public RestartCommand(JavaPlugin plugin, CoreManager manager)
	{
		super("restart", new String[]{"stop"}, Rank.DEVELOPER, "/restart", "Restart the current server.");
		
		_plugin = plugin;
		_manager = manager;
	}
	
	@Override
	public void execute(final Player sender, String[] args)
	{
		if(args.length > 0)
		{
			sendArgs(sender);
			return;
		}
		
		new BukkitRunnable()
		{
			int i = 0;
			@Override
			public void run()
			{
				i++;
				
				for(Player all : Bukkit.getOnlinePlayers())
					TitleMiddle.sendTitle(all, C.DarkRedB + "Important " + C.RedB + "This server is restarting.", TextUtil.getBar(C.RedB, C.GrayB, i, 40, "|", 40), new int[]{0,100,0});
				
				if(i==5)//40
				{
					_manager.shutdown(true);
					this.cancel();
				}
			}
		}.runTaskTimer(_plugin, 0, 4);
		return;
	}
}
