package com.grasernetwork.core.punish.data;

import java.sql.Timestamp;

import com.grasernetwork.core.rank.Rank;

public class Mute
{
	private int _reasonId;
	private String _staff;
	private Rank _staffRank;
	private Timestamp _time;
	private Timestamp _unmute;

	public Mute(int reasonId, String staff, Rank staffRank, Timestamp time, Timestamp unmute)
	{
		_reasonId = reasonId;
		_staff = staff;
		_staffRank = staffRank;
		_time = time;
		_unmute = unmute;
	}

	/** Getters */
	public int getReasonId()
	{
		return _reasonId;
	}
	
	public String getStaff()
	{
		return _staff;
	}
	
	public Rank getStaffRank()
	{
		return _staffRank;
	}

	public Timestamp getTime()
	{
		return _time;
	}

	public Timestamp getUnmute()
	{
		return _unmute;
	}
}
