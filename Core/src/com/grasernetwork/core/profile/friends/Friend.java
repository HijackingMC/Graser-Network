package com.grasernetwork.core.profile.friends;

/**
 * Created by TeddehDev on 16/01/2016.
 */
public class Friend
{
	private String _uuid;
	private boolean _bestFriend;

	public Friend(String uuid, boolean bestFriend)
	{
		_uuid = uuid;
		_bestFriend = bestFriend;
	}

	public String getUUID()
	{
		return _uuid;
	}

	public boolean isBestFriend()
	{
		return _bestFriend;
	}

	public void setBestFriend(boolean status)
	{
		_bestFriend = status;
	}
}
