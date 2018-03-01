package com.grasernetwork.util;

public enum ChatType
{
	ERROR(
			C.RedB + "ERROR >" + C.Reset + " ",
			C.White,
			C.Red + C.Italic
	),

	PERMISSION(
			C.RedB + "PERMISSION >" + C.Reset + " ",
			C.White,
			C.Red + C.Italic
	),

	SUCCESS(
			C.GreenB + "SUCCESS >" + C.Reset + " ",
			C.White,
			C.Green + C.Italic
	),

	WARNING(
			C.YellowB + "WARNING >" + C.Reset + " ",
			C.White,
			C.Yellow + C.Italic
	),

	COMMAND(
			C.YellowB + "COMMAND >" + C.Reset + " ",
			C.White,
			C.Yellow + C.Italic
	),

	SERVER(
			C.AquaB + "SERVER >" + C.Reset + " ",
			C.White,
			C.Aqua + C.Italic
	),
	
	
	DEATH(
			C.LightPurpleB + "DEATH >" + C.Reset + " ",
			C.White,
			C.LightPurple + C.Italic
	),

	PUNISH(
			C.RedB + "PUNISH >" + C.Reset + " ",
			C.White,
			C.Red + C.Italic
	);

	private String _prefix;
	private String _colour;
	private String _keyColour;

	ChatType(String prefix, String colour, String keyColour)
	{
		_prefix = prefix;
		_colour = colour;
		_keyColour = keyColour;
	}

	public String getPrefix()
	{
		return _prefix;
	}

	public String getColour()
	{
		return _colour;
	}

	public String getKeyColour()
	{
		return _keyColour;
	}
}
