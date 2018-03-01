package com.grasernetwork.redisslave;

import java.io.File;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import com.grasernetwork.common.jedis.JedisServer;
import com.grasernetwork.common.jedis.Subscriber;

public class Slave extends Subscriber {
    private JedisServer jedis;

    public static void main(String[] args) throws UnknownHostException {
        new Slave();
    }

    public Slave() throws UnknownHostException {
        System.out.println("Loading redis Slave...");
        jedis = new JedisServer("localhost");
        while (!jedis.isConnected()) {
        }
        System.out.println("Requesting running servers from redis");
        jedis.subscribe(this, "servers");

    }

    @Override
    public void onMessage(String channel, String message) {
        String[] components = message.split(Pattern.quote(" "));

        if (message.equals("DEL")) {
            String name = components[1];
            deleteDirectory(new File("/server/home/hub/" + name));
            System.out.println("deleting " + name);
        } else if(message.equals("CREATE")) {
            String name = components[1];
            String port = components[2];
        }
    }

    private void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
        }
        directory.delete();
    }
}
