package com.grasernetwork.core.profile;

import com.grasernetwork.core.profile.friends.Friend;
import com.grasernetwork.core.punish.data.PunishData;
import com.grasernetwork.core.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.UUID;

public class Profile
{
	private boolean _profileLoaded;

	private UUID _uuid;
	private String _name;
	private long _online, _joined;
	private LinkedList<Friend> _friends;
//	private Morph _morph;
//	private HashMap<PetType, PetData> _petMap = new HashMap<PetType, PetData>();

	public boolean muted = true;
	
	public Rank rank;
	public int netLevel = 0;
	public int netExp = 1;
	public int crystals = 500;
	public long firstLogin = 0L;
	public long lastLogin = 0L;
	public Locale language = Locale.ENGLISH;
	public PunishData punishData;
	
//	public CosmeticData cosmeticData = null;
	
	public Profile(Player player)
	{
		_uuid = player.getUniqueId();
		_name = player.getName();
		rank = null;
		_online = 1;
		_friends = new LinkedList<Friend>();
	}

	/** Getters */
	public boolean isProfileLoaded()
	{
		return _profileLoaded;
	}

	public UUID getUUID()
	{
		return _uuid;
	}

	public String getName()
	{
		return _name;
	}

	public Rank getRank()
	{
		return rank;
	}

	public long getOnlineTime()
	{
		return _online;
	}

	public long getJoinedTime()
	{
		return _joined;
	}

	public LinkedList<Friend> getFriends()
	{
		return _friends;
	}

	public Friend getFriendByName(String friend)
	{
		for(Friend f : _friends)
		{
			if(!f.getUUID().equals(Bukkit.getOfflinePlayer(friend)))
				continue;

			return f;
		}

		return null;
	}
	
//	public Morph getMorph()
//	{
//		return _morph;
//	}

	/** Setters */
	public void addFreind(Friend friend)
	{
		_friends.add(friend);
	}

	public void removeFreind(Friend friend)
	{
		if(_friends.contains(friend))
			_friends.remove(friend);
	}

	public void setFriends(LinkedList<Friend> friends)
	{
		_friends = friends;
	}

	public void setUUID(UUID uuid)
	{
		_uuid = uuid;
	}

	public void setName(String name)
	{
		_name = name;
	}

	public void setRank(Rank rank)
	{
		rank = rank;
	}

	public void setOnlineTime(long online)
	{
		_online = online;
	}

	public void setJoinedTime(long online)
	{
		_joined = online;
	}

	public void setProfileLoaded(boolean loaded)
	{
		_profileLoaded = loaded;
	}

//	public void setMorph(Morph morph)
//	{
//		_morph = morph;
//	}
}
