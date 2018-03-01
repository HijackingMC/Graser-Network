package com.grasernetwork.core.rank;

import com.grasernetwork.util.C;

/**
 * Created by TeddehDev on 14/01/2016.
 */
public enum Rank
{
	OWNER("Owner", C.Red, 100),
	DEVELOPER("Developer", C.Blue, 60),
	ADMIN("Admin", C.Red, 61),
	QA("Mod", C.Gold, 53),
	BUILDER("Builder", C.Blue, 52),
	MODERATOR("Mod", C.Gold, 51),
	HELPER("Helper", C.Blue, 50),
	DONOR3("Donor3", C.LightPurple, 12),
	DONOR2("Donor2", C.LightPurple, 11),
	DONOR1("Donor1", C.LightPurple, 10),
	ALL("", C.Gray, 0);

	private String _name;
	private String _colour;
	private int _priority;

	Rank(String name, String colour, int priority)
	{
		_name = name;
		_colour = colour;
		_priority = priority;
	}

	public String getName()
	{
		return _name;
	}

	public String getColour()
	{
		return _colour;
	}

	public String getColourBold()
	{
		return _colour + C.Bold;
	}

	public String getNameWithColour()
	{
		return _colour + _name;
	}

	public String getBoldName()
	{
		return _colour + C.Bold + _name.toUpperCase();
	}

	public int getPriority()
	{
		return _priority;
	}

	public boolean hasPermission(Rank rank)
	{
		return _priority >= rank.getPriority();
	}
}
