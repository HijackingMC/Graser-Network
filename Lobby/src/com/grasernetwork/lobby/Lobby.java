package com.grasernetwork.lobby;

import com.grasernetwork.core.Core;

public class Lobby extends Core
{

	@Override
	public void enable()
	{
		new LobbyManager(this);
	}

	@Override
	public void onDisable()
	{
		super.onDisable();
	}
}