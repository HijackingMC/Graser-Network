package com.grasernetwork.core.punish.data;

import java.sql.Timestamp;

import com.grasernetwork.core.rank.Rank;

public class Ban
{
	private int _reasonId;
	private String _staff;
	private Rank _staffRank;
	private Timestamp _time;
	private Timestamp _unban;

	public Ban(int reasonId, String staff, Rank staffRank, Timestamp time, Timestamp unban)
	{
		_reasonId = reasonId;
		_staff = staff;
		_staffRank = staffRank;
		_time = time;
		_unban = unban;
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

	public Timestamp getUnbanned()
	{
		return _unban;
	}
}
