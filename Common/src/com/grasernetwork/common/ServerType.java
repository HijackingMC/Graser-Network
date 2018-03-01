package com.grasernetwork.common;

public enum ServerType {
	LOBBY(100), GAME(50), BUILD(20);

	ServerType(int max) {
		maxPlayers = max;
	}

	public int maxPlayers = 0;

}
