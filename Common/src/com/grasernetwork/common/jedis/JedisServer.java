package com.grasernetwork.common.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisServer {
	private JedisPool _jedis;
	private boolean _connected = false;

	public JedisServer(String ip) {
		new Thread(() -> {
			_jedis = new JedisPool(new JedisPoolConfig(), ip);
			_jedis.getResource().ping();
			System.out.println("Jedis connected");
			_connected = true;
		}).start();
	}

	public void publish(String channel, String message) {
		new Thread(() -> {
			Jedis jedis = _jedis.getResource();
			try {
				jedis.publish(channel, message);
				if (!message.startsWith("PING"))
					System.out.println("Published, " + message);
			}
			finally {
				jedis.close();
			}
		}).start();
	}

	public void subscribe(Subscriber sub, String... channels) {
		if (!_connected)
			return;

		Jedis jedis = _jedis.getResource();
		try {
			_jedis.getResource().subscribe(sub, channels);
		}
		finally {
			jedis.close();
		}
	}

	public boolean isConnected() {
		return _connected;
	}

}
