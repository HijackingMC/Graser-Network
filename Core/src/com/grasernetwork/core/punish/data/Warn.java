package com.grasernetwork.core.punish.data;

import java.sql.Timestamp;

import com.grasernetwork.core.rank.Rank;

public class Warn
{
	private String _reasonId;
	private String _staff;
	private Rank _staffRank;
	private Timestamp _time;

	public Warn(String reason, String staff, Rank staffRank, Timestamp time)
	{
		_reasonId = reason;
		_staff = staff;
		_staffRank = staffRank;
		_time = time;
	}

	/** Getters */
	public String getReason()
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
}
