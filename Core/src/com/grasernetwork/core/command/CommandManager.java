package com.grasernetwork.core.command;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;

import com.grasernetwork.core.CoreManager;
import com.grasernetwork.core.command.general.CrystalsCommand;
import com.grasernetwork.core.command.general.GamemodeCommand;
import com.grasernetwork.core.command.general.HelpCommand;
import com.grasernetwork.core.command.general.MessageCommand;
import com.grasernetwork.core.command.general.RestartCommand;
import com.grasernetwork.core.command.general.StopCommand;
import com.grasernetwork.core.command.general.TeleportCommand;
import com.grasernetwork.core.command.general.TempCommand;
import com.grasernetwork.core.heads.command.HeadsCommand;
import com.grasernetwork.core.profile.ProfileManager;
import com.grasernetwork.core.punish.PunishManager;
import com.grasernetwork.core.punish.command.PunishCommand;
import com.grasernetwork.core.rank.RankCommand;
import com.grasernetwork.util.ChatType;
import com.grasernetwork.util.PlayerUtil;


public class CommandManager implements Listener
{
	public static HashMap<String, CommandBase> Commands = new HashMap<String, CommandBase>();

	private CoreManager _manager;
	private ProfileManager _profileManager;
	private PunishManager _punishManager;

	public CommandManager(CoreManager manager, ProfileManager profileManager, PunishManager punishManager)
	{
		_manager = manager;
		_profileManager = profileManager;
		_punishManager = punishManager;

		Bukkit.getPluginManager().registerEvents(this, manager.getPlugin());

		new MessageCommand();
		new GamemodeCommand();
		new RankCommand(manager);

		new HeadsCommand();
//		new CapeCommand(_manager.getPlugin(), profileManager);
		new PunishCommand(_manager, _punishManager);
		new HelpCommand(_profileManager);
		new RestartCommand(_manager.getPlugin(), _manager);
		new TempCommand(_manager.getPlugin());
		new StopCommand(_manager);
		new CrystalsCommand(_manager);
		new TeleportCommand();
	}

	@EventHandler
	public void commandProcess(PlayerCommandPreprocessEvent event)
	{
		String commandName = event.getMessage().substring(1);
		String[] args = new String[]{};

		if(commandName.equals("tps"))
			return;
		
		if (commandName.contains(" "))
		{
			commandName = commandName.split(" ")[0];
			args = event.getMessage().substring(event.getMessage().indexOf(' ') + 1).split(" ");
		}

		if(!Commands.containsKey(commandName))
		{
//			event.setCancelled(true);
			PlayerUtil.message(event.getPlayer(), "The command '/%s' does not exist.", new String[]{commandName}, ChatType.ERROR);
			return;
		}
		
		CommandBase command = Commands.get(commandName.toLowerCase());

		Player player = event.getPlayer();
		if(!command.hasPermission(_profileManager.getProfile(player)))
		{
			event.setCancelled(true);
			PlayerUtil.message(player, "You do not have permission, requires [%s]", new String[]{command.getRequiredRank().getBoldName()}, ChatType.PERMISSION);
			return;
		}

		if (command != null)
		{
			event.setCancelled(true);
			command.execute(event.getPlayer(), args);
			return;
		}
	}
	
	@EventHandler
	public void map(MapInitializeEvent event)
	{
		MapView map = event.getMap();
		for(MapRenderer render : map.getRenderers())
		{
			map.removeRenderer(render);
		}
		
		map.addRenderer(new MapRenderer()
		{
			@Override
			public void render(MapView mapView, MapCanvas mapCanvas, Player player)
			{
				Image icon = null;
				try
				{
					icon = new ImageIcon(_manager.getPlugin().getDataFolder().getCanonicalPath() + "/chrome.png").getImage();
				} catch (IOException e)
				{
					return;
				}
				
				mapCanvas.drawImage(0, 0, icon);
				
//				for(int x = 0; x < 128; x++)
//				{
//					for(int z = 0; z < 128; z++)
//					{
//						mapCanvas.setPixel(x, z, MapPalette.WHITE);
//					}
//				}
				
				mapCanvas.drawText(5, 5, MinecraftFont.Font, player.getName());
			}
		});
	}
}
