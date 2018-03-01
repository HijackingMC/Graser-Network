package com.grasernetwork.core.profile.friends;

import com.grasernetwork.core.CoreManager;

/**
 * Created by TeddehDev on 16/01/2016.
 */
public class FriendManager
{
	private static final String UPDATE = "UPDATE profile SET friends=? WHERE uuid=?";
	private static final String SELECT = "SELECT friends FROM profile WHERE uuid=?";

	private CoreManager _manager;

	public FriendManager(CoreManager manager)
	{
		_manager = manager;
	}
}
