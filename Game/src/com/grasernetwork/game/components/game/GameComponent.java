package com.grasernetwork.game.components.game;

import com.grasernetwork.game.GamePlugin;
import com.grasernetwork.game.components.Component;
import com.grasernetwork.game.components.game.gamestate.GameState;
import com.grasernetwork.game.components.game.gamestate.GameStateChangeEvent;
import com.grasernetwork.game.components.player.GamePlayer;
import com.grasernetwork.game.components.player.PlayerState;
import com.grasernetwork.util.ServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Teddeh on 08/03/2016.
 */
public abstract class GameComponent extends Component
{
	private List<GamePlayer> gamePlayers;
	private HashMap<GameState, List<Component>> gameComponents;
	private List<Component> globalComponents;
	private GameState gameState;

	public GameComponent(GamePlugin plugin)
	{
		super(plugin, true);

		gamePlayers = new ArrayList<GamePlayer>();
		gameComponents = new HashMap<GameState, List<Component>>();
		setGameState(GameState.WAITING);
	}

	/**
	 * Add global component for current game
	 *
	 * @param components
	 */
	public void addComponent(Component... components)
	{
		for (Component component : components)
		{
			globalComponents.add(component);
			component.register();
		}
	}

	public void removeGlobalComponents()
	{
		globalComponents.forEach(component -> component.unregister());
		globalComponents.clear();
	}

	public List<Component> getGlobalComponents()
	{
		return globalComponents;
	}

	/**
	 * Add a component to a GameState for the current game.
	 *
	 * @param gameState
	 * @param components
	 */
	public void addComponent(GameState gameState, Component... components)
	{
		gameComponents.put(gameState, Arrays.asList(components));
	}

	/**
	 * Remove an existing GameState component for the current game.
	 *
	 * @param gameState
	 * @param components
	 */
	public void removeComponent(GameState gameState, Component... components)
	{
		if (!gameComponents.containsKey(gameState)) return;

		List<Component> c = gameComponents.get(gameState);
		if (c.isEmpty()) return;

		for (Component component : components)
		{
			if (!c.contains(component)) continue;

			if (component.isRegistered()) component.unregister();

			c.remove(component);
		}

		gameComponents.put(gameState, c);
	}

	/**
	 * Get current game state.
	 *
	 * @return
	 */
	public GameState getGameState()
	{
		return gameState;
	}

	/**
	 * Set game state for the current game.
	 *
	 * @param gameState
	 */
	public void setGameState(GameState gameState)
	{
		GameStateChangeEvent event = new GameStateChangeEvent(this.gameState, gameState);
		Bukkit.getPluginManager().callEvent(event);
		this.gameState = gameState;
	}

	/**
	 * Register a player to the current game.
	 *
	 * @param player Player Object.
	 */
	public void addGamePlayer(Player player)
	{
		addGamePlayer(player.getName());
	}

	/**
	 * Register a player to the current game.
	 *
	 * @param player Player Name.
	 */
	public void addGamePlayer(String player)
	{
		if (containsGamePlayer(player)) return;

		GamePlayer gamePlayer = new GamePlayer(player);
		if (gameState == null) gamePlayer.setPlayerState(PlayerState.PLAYING);

		if (gameState == GameState.IN_PROGRESS) gamePlayer.setPlayerState(PlayerState.SPECTATING);

		gamePlayers.add(gamePlayer);
	}

	/**
	 * Remove a player registration from the current game.
	 *
	 * @param player Player Object.
	 */
	public void removeGamePlayer(Player player)
	{
		removeGamePlayer(player.getName());
	}

	/**
	 * Remove a player registration from the current game.
	 *
	 * @param player Player Name.
	 */
	public void removeGamePlayer(String player)
	{
		if (!containsGamePlayer(player)) return;

		for (GamePlayer gamePlayer : gamePlayers)
		{
			if (!gamePlayer.getPlayer().equals(player)) continue;

			gamePlayers.remove(player);
			break;
		}
	}

	/**
	 * Check if a player is registered to the current game.
	 *
	 * @param player Player Object
	 * @return Boolean
	 */
	public boolean containsGamePlayer(Player player)
	{
		return containsGamePlayer(player.getName());
	}

	/**
	 * Check if a player is registered to the current game.
	 *
	 * @param player Player Name
	 * @return Boolean
	 */
	public boolean containsGamePlayer(String player)
	{
		for (GamePlayer gamePlayer : gamePlayers)
		{
			if (!gamePlayer.getPlayer().equals(player)) continue;

			return true;
		}

		return false;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		addGamePlayer(event.getPlayer());
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event)
	{
		removeGamePlayer(event.getPlayer());
	}

	@EventHandler
	public void onGameStateChange(GameStateChangeEvent event)
	{
		if (event.getFrom() != null)
		{
			GameState from = event.getFrom();
			if (!gameComponents.containsKey(from)) return;

			for (Component component : gameComponents.get(from))
			{
				if (!isRegistered()) continue;

				component.unregister();
			}
		} else ServerUtil.Log(Level.WARNING, "GameState FROM is null");

		if (event.getTo() != null)
		{
			GameState to = event.getTo();
			if (!gameComponents.containsKey(to)) return;

			for (Component component : gameComponents.get(to))
			{
				if (isRegistered()) continue;

				component.register();
			}
		} else ServerUtil.Log(Level.WARNING, "GameState TO is null");
	}
}
