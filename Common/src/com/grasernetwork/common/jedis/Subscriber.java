package com.grasernetwork.common.jedis;

import redis.clients.jedis.JedisPubSub;

public abstract class Subscriber extends JedisPubSub
{
	@Override
	public final void onPMessage(String arg0, String arg1, String arg2)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void onPSubscribe(String arg0, int arg1)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void onPUnsubscribe(String arg0, int arg1)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void onSubscribe(String arg0, int arg1)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void onUnsubscribe(String arg0, int arg1)
	{
		// TODO Auto-generated method stub
		
	}
}
