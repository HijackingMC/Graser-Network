package com.grasernetwork.redismaster;

import java.util.HashMap;
import java.util.regex.Pattern;

import com.grasernetwork.common.Server;
import com.grasernetwork.common.ServerType;
import com.grasernetwork.common.jedis.JedisServer;
import com.grasernetwork.common.jedis.Subscriber;

public class Master extends Subscriber {
    private HashMap<String, Server> servers = new HashMap<String, Server>();
    private JedisServer jedis;

    public static void main(String[] args) {
        System.out.println("Starting up Master server");
        new Master();
    }

    public Master() {
        jedis = new JedisServer("localhost");
        while (!jedis.isConnected()) {
        }
        System.out.println("Requesting running servers from redis");
        jedis.subscribe(this, "servers");
        jedis.publish("servers", "REQUESTALL");
    }

    @Override
    public void onMessage(String channel, String message) {
        String[] components = message.split(Pattern.quote(" "));
        if (components[0].equals("NEWSERVER")) {
            Server newServer = new Server(components[1], Integer.valueOf(components[2]), components[3], ServerType.valueOf(components[4]));
            servers.put(components[1], newServer);
            System.out.println("Adding server " + components[1]);
        }
        else if (components[0].equals("DELSERVER")) {
            servers.remove(components[1]);
            System.out.println("Removing server " + components[1]);
        }
    }

    public void createServer(String ip, String port, ServerType type) {
        System.out.println("Creating server on " + ip + ":" + port);
        jedis.publish("servers", "BUILD " + ip + " " + port + " " + type.name());
    }
}

