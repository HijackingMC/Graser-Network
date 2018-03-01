package com.grasernetwork.game.components.game;

import com.grasernetwork.game.GamePlugin;

/**
 * Created by Teddeh on 08/03/2016.
 */
public abstract class Game extends GameComponent
{
	private final int id;
	private final int playerCap;
	private final String name;
	private final GameType gameType;

	/**
	 * This constructor should be used for all NON-ARCADE based gamemodes.
	 *
	 * @param id   The game id. -- Should never be changed! --
	 * @param name The game name.
	 */
	public Game(GamePlugin plugin, int id, String name, int playerCap)
	{
		super(plugin);

		this.id = id;
		this.name = name;
		this.playerCap = playerCap;
		this.gameType = GameType.STANDALONE;
	}

	/**
	 * This constructor should be used for games which are not Standalone games.
	 *
	 * @param id       The game id. -- Should never be changed! --
	 * @param name     The game name.
	 * @param gameType The Gametype of the game.
	 */
	public Game(GamePlugin plugin, int id, String name, int playerCap, GameType gameType)
	{
		super(plugin);

		this.id = id;
		this.name = name;
		this.playerCap = playerCap;
		this.gameType = gameType;
	}

	/**
	 * Called when the game class is registered.
	 */
	public abstract void initialize();

	/**
	 * Called when the game world has been generated.
	 */
	public void initDataPoints() {}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public int getPlayerCap()
	{
		return playerCap;
	}

	public GameType getGameType()
	{
		return gameType;
	}
}
