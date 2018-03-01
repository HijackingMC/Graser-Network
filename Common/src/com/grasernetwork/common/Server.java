package com.grasernetwork.common;

public class Server {

	public String ip, name;
	public int port;
	public ServerType type;

	public Server(String ip, int port, String name, ServerType type) {
		this.ip = ip;
		this.port = port;
		this.type = type;
		this.name = name;
	}

}
