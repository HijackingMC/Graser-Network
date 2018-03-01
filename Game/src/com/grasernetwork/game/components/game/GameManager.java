package com.grasernetwork.game.components.game;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.game.countdown.Countdown;
import com.grasernetwork.game.components.game.countdown.CountdownEvent;
import com.grasernetwork.game.components.game.event.GameFinishedEvent;
import com.grasernetwork.game.components.game.event.GameTeleportEvent;
import com.grasernetwork.game.components.game.gamestate.GameState;
import com.grasernetwork.game.components.game.gamestate.GameStateChangeEvent;
import com.grasernetwork.game.components.map.MapData;
import com.grasernetwork.game.components.map.MapLoader;
import com.grasernetwork.game.components.map.event.GameWorldGenerationEvent;
import com.grasernetwork.game.games.TestGame;
import com.grasernetwork.game.games.zombies.solo.ZombiesSolo;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ServerUtil;
import com.grasernetwork.util.packet.TabTitle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

/**
 * Created by Teddeh on 10/03/2016.
 */
public class GameManager implements Listener
{
	private static GamePlugin plugin;
	private Gamemode gamemode;
	private Game currentGame;
	private MapLoader mapLoader;
	private MapData mapData;
	private Location[] boarder;
	private List<Location> spawns;

	private String currentMapName = "";

	public GameManager(GamePlugin plugin)
	{
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	public Game getCurrentGame()
	{
		return currentGame;
	}

	public MapData getMapData()
	{
		return mapData;
	}

	public void initialize(Gamemode gamemode)
	{
		this.gamemode = gamemode;
		currentGame = gamemode.getGameInstance();

		try
		{
			File[] files = new File(plugin.getDataFolder().getCanonicalPath() + "/../../../../Maps/" + this.currentGame.getName().toUpperCase()).listFiles();
			File chosen = files[new Random().nextInt(files.length)];
			mapLoader = new MapLoader(plugin, currentGame);
			currentMapName = chosen.getName();
			System.out.println(chosen.getName());
			mapLoader.loadMap(chosen.getName());
			mapData = new MapData(plugin, currentGame.getName(), chosen.getName());
			new BukkitRunnable()
			{
				public void run()
				{
					mapLoader.generateWorld(chosen.getName());
				}
			}.runTaskLater(plugin, 40L);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		currentGame.initialize();

		ServerUtil.Log(Level.INFO, " ");
		ServerUtil.Log(Level.INFO, "GAME INITIALIZED");
		ServerUtil.Log(Level.INFO, " ");
		ServerUtil.Log(Level.INFO, "GameType: " + currentGame.getGameType());
		ServerUtil.Log(Level.INFO, "Game: " + currentGame.getName());
		ServerUtil.Log(Level.INFO, "Id: " + currentGame.getId());
		ServerUtil.Log(Level.INFO, " ");
	}

	public void cleanMapData()
	{
		for(Location location : boarder)
			location.getBlock().setType(Material.AIR);

		spawns = new ArrayList<Location>();
		for(Location location : mapData.getSpawnLocations("BLACK"))
		{
			spawns.add(location.add(.5, 0, .5));
			location.getBlock().setType(Material.AIR);
		}
	}

	public void cleanDataPoints(String[] dataBlocks)
	{
		for(String data : dataBlocks)
		{
			for(Location location : mapData.getDataLocations(data))
			{
				location.getBlock().setType(Material.AIR);
			}
		}
	}

	@EventHandler
	public void onWorldLoad(GameWorldGenerationEvent event)
	{
		if(!event.getMapName().equals(currentMapName))
			return;

		boarder = mapData.getBoarder();
		cleanMapData();
		currentGame.initDataPoints();
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		if (currentGame == null)
		{
			System.out.println("Current game is null");
			return;
		}

		TabTitle.sendTabTitle(event.getPlayer(),
				C.White + "< " + C.Aqua + ServerUtil.SERVER + C.White + " >" + "\n"
						+ C.Yellow + "Current game, " + currentGame.getName() + "\n ",
				"\n " + C.Yellow + "Visit our store: " + C.Gold + ServerUtil.STORE);

		if (currentGame.getGameState() == GameState.DEAD)
		{
			currentGame.setGameState(GameState.WAITING);
		}
		else if (currentGame.getGameState() == GameState.WAITING)
		{
//			if(Bukkit.getOnlinePlayers().size() >= (currentGame.getPlayerCap() / 2))
			if (Bukkit.getOnlinePlayers().size() >= 1)
			{
				currentGame.setGameState(GameState.INITIALIZING);

				Countdown countdown = new Countdown(plugin, /*30*/15, new int[]{/*30, */15, 10, 5, 4, 3, 2, 1}, new String[]{"The game is starting in %s", "Game has started!"});
				countdown.start();
			}
		}

		//setScoreboard *based on state*
	}

	@EventHandler
	public void countdownFinish(CountdownEvent event)
	{
		if(!event.isFinished())
			return;

		//list location
		//list player

		//for each player, get next location and tp
		//if locations list is maxed, get random

		if(mapData == null || currentGame == null)
		{
			plugin.getManager().shutdown(true);
			return;
		}

		boolean isSolo = true;
		if(currentGame instanceof Teams)
			isSolo = false;

		System.out.println("DEBUG > " + spawns.size());
		int i = 1;

		for(Player online : Bukkit.getOnlinePlayers())
		{
			if(spawns.size() <= i)
				online.teleport(spawns.get(i));
			else
			{
				online.teleport(spawns.get(i));
				i = 1;
				continue;
			}

			i += 1;
		}

		//TODO: Clean up

		GameTeleportEvent teleportEvent = new GameTeleportEvent(currentGame);
		Bukkit.getPluginManager().callEvent(teleportEvent);
	}

	@EventHandler
	public void gameUnload(GameStateChangeEvent event)
	{
		if(event.getTo() == GameState.DEAD)
		{
			Location lobby = Bukkit.getWorld("world").getSpawnLocation().add(0, 2, 0);
			Bukkit.getOnlinePlayers().forEach(p -> p.teleport(lobby));
			String mapName = currentMapName;

			GameFinishedEvent finishEvent = new GameFinishedEvent(currentGame);
			Bukkit.getPluginManager().callEvent(finishEvent);

			new BukkitRunnable()
			{
				public void run()
				{
					mapLoader.unloadWorldFile(mapName, true);
				}
			}.runTaskLater(plugin, 20L);
		}
	}

	public enum Gamemode
	{
		TEST_GAME(new TestGame(plugin)),
		ZOMBIES(new ZombiesSolo(plugin));

		private Game game;

		Gamemode(Game game)
		{
			this.game = game;
		}

		public Game getGameInstance()
		{
			return game;
		}
	}
}
