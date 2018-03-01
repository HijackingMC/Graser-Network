package com.grasernetwork.bungeecore;

import java.util.regex.Pattern;

import com.grasernetwork.bungeecore.network.NetworkMOTD;
import com.grasernetwork.common.ServerType;
import com.grasernetwork.common.jedis.Subscriber;

public class BungeeSubscriber extends Subscriber
{
	private BungeeCore _bungee;

	public BungeeSubscriber(BungeeCore bungee)
	{
		_bungee = bungee;
	}

	@Override
	public void onMessage(String channel, String message)
	{
		String[] str = message.split(Pattern.quote(";"));
		if (channel.equalsIgnoreCase("bungee"))
		{
			if (str[0].equals("CRE"))
			{
				// CRE;IP;PORT;NAME
				System.out.println("Attempting to create server, " + message);
				_bungee.createServer(str[3], str[1], Integer.parseInt(str[2]));
				System.out.println("Server, " + str[3] + " has been created on " + str[1] + ":" + str[2] + "!");
			}
			else if (str[0].equals("GETNAME")) // GETNAME;ip;port;type
			{
				String serverName = getServerName(ServerType.valueOf(str[3]));
				_bungee.getJedis().publish("bungee", "NAME;" + str[1] + ";" + str[2] + ";" + serverName);
				System.out.println("Returning '" + serverName + "' to " + str[1] + ":" + str[2]);
			}
			else if(str[0].equals("DEL")) {
				_bungee.getProxy().getServers().remove(str[1]);
				System.out.println("Removing " + str[1] + " from servers");
			}
		}
		else if (channel.equals("global"))
		{
			//SETMOTD;MOTD
			//SETMOTD;SCHEDULED_MAINTENENCE
			if(str[0].equals("SETMOTD")) {
				NetworkMOTD motd = NetworkMOTD.valueOf(str[1]);
				if(motd == null) return;
				_bungee.setMotd(motd);
				System.out.println("Updated MOTD");
			}
		}

	}

	private String getServerName(ServerType type)
	{
		for (int i = 1; i < 150; i++)
		{
			if (_bungee.getProxy().getServerInfo(type.name() + "-" + i) == null)
				return type.name() + "-" + i;
		}

		return null;
	}
}
