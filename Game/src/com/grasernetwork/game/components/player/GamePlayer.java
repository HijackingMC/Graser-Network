package com.grasernetwork.game.components.player;

import org.bukkit.entity.Player;

/**
 * Created by Teddeh on 08/03/2016.
 */
public class GamePlayer
{
	private String player;
	private PlayerState playerState;

	public GamePlayer(Player player)
	{
		this.player = player.getName();
	}

	public GamePlayer(String player)
	{
		this.player = player;
	}

	/**
	 * Get the player name
	 *
	 * @return Player Name
	 */
	public String getPlayer()
	{
		return player;
	}

	/**
	 * Get a players state for the current game.
	 *
	 * @return PlayerState
	 */
	public PlayerState getPlayerState()
	{
		return playerState;
	}

	/**
	 * Set a players state for the current game.
	 *
	 * @param playerState
	 */
	public void setPlayerState(PlayerState playerState)
	{
		this.playerState = playerState;
	}
}
