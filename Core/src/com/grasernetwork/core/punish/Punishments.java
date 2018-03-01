package com.grasernetwork.core.punish;

import com.grasernetwork.core.rank.Rank;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ServerUtil;

/**
 * Created by TeddehDev on 17/01/2016.
 */
public class Punishments
{

	public enum Helper
	{
		SPAM(
				11,
				(byte) 3,
				"Intentionally Spamming in chat.",
				Rank.HELPER,
				Type.WARN,
				"Intentionally Spamming",
				43200000,
				new String[]
						{
								"",
								C.White + "User intentionally posting the",
								C.White + "same or similar messages",
								C.White + "consecutively, a repetitive number",
								C.White + "of times within the space of",
								C.White + "a few or more seconds."
						},
				1
		),

		CAPS(
				12,
				(byte) 3,
				"Abusing Caps/Uppercase in chat.",
				Rank.HELPER,
				Type.WARN,
				"Abusing Uppercase",
				28800000,
				new String[]
						{
								"",
								C.White + "Messages comprising of",
								C.White + "all or a large amount",
								C.White + "of caps is considered ",
								C.White + "chat abuse"
						},
				2
		),

		EXCESSIVE_SPAM(
				20,
				(byte) 3,
				"Excessively Spamming in chat.",
				Rank.HELPER,
				Type.MUTE,
				"Excessively Spamming",
				151200000,
				new String[]
						{
								"",
								C.White + "User intentionally posting the",
								C.White + "same or similar messages",
								C.White + "consecutively, a repetitive number",
								C.White + "of times within the space of",
								C.White + "a few or more seconds.",
								"",
								C.RedB + "Continually"
						},
				3
		),

		EXCESSIVE_CAPS(
				21,
				(byte) 3,
				"Excessive abuse of Caps/Uppercase in chat.",
				Rank.HELPER,
				Type.MUTE,
				"Excessive Uppercase abuse",
				151200000,
				new String[]
						{
								"",
								C.White + "Messages comprising of",
								C.White + "all or a large amount",
								C.White + "of caps is considered ",
								C.White + "chat abuse",
								"",
								C.RedB + "Continually"
						},
				4
		),

		CURSING(
				29,
				(byte) 3,
				"Intentionally Cursing/Swearing in chat.",
				Rank.HELPER,
				Type.MUTE,
				"Intentional Cursing",
				345600000,
				new String[]
						{
								"",
								C.White + "Messages containing 1 or more",
								C.White + "number of bad words (curses)."
						},
				5
		),

		TROLLING(
				30,
				(byte) 3,
				"Intentionally annoying or angering players.",
				Rank.HELPER,
				Type.MUTE,
				"Intentional Trolling",
				86400000,
				new String[]
						{
								"",
								C.White + "Intentionally trolling online",
								C.White + "users after a 1/2 warnings"
						},
				6
		),

		DISRESPECT(
				38,
				(byte) 3,
				"Being Disrespectful to online player(s).",
				Rank.HELPER,
				Type.MUTE,
				"Disrespectful",
				14400000,
				new String[]
						{
								"",
								C.White + "Being disrespectful after",
								C.White + "1/2 warnings.",
								C.White + "",
								C.White + "If the user that was disrespected",
								C.White + "was a member of staff,",
								C.White + "no warnings needed."
						},
				7
		),

		RACISM(
				39,
				(byte) 3,
				"Being Disrespectful to other players (Racist comments).",
				Rank.HELPER,
				Type.BAN,
				"Disrespectful (Racist)",
				345600000,
				new String[]
						{
								"",
								C.White + "Being disrespectful to player(s)",
								C.White + "about there religion,",
								C.White + "no warnings required."
						},
				8
		),

		SEXIST_REMARKS(
				47,
				(byte) 3,
				"Being Disrespectful to other players (Sexist comments).",
				Rank.HELPER,
				Type.BAN,
				"Disrespectful (Sexist)",
				345600000,
				new String[]
						{
								"",
								C.White + "Being disrespectful to player(s)",
								C.White + "about there sexuality,",
								C.White + "no warnings required."
						},
				9
		),

		ADVERTISEMENT(
				48,
				(byte) 3,
				"Advertising/ Promoting other networks/brands.",
				Rank.HELPER,
				Type.BAN,
				"Advertising",
				7243200000L,
				new String[]
						{
								"",
								C.White + "Accidental advertisement requires",
								C.White + "a friendly warning",
								C.White + "",
								C.White + "NON-Accidental advertisement,",
								C.White + "no warning required."
						},
				10
		);

		private int _slot;
		private byte _data;
		private String _reason;
		private Rank _requiredRank;
		private Type _type;
		private String _punishTitle;
		private long _time;
		private String[] _punishDescription;
		private int _id;

		Helper(int slot, byte data, String reason, Rank requiredRank, Type type, String punishTitle, long time, String[] punishDescription, int id)
		{
			_slot = slot;
			_data = data;
			_reason = reason;
			_requiredRank = requiredRank;
			_type = type;
			_punishTitle = punishTitle;
			_time = time;
			_punishDescription = punishDescription;
			_id = id;
		}

		public int getSlot()
		{
			return _slot;
		}

		public byte getData()
		{
			return _data;
		}

		public String getReason()
		{
			return _reason;
		}

		public Rank getRequiredRank()
		{
			return _requiredRank;
		}

		public Type getType()
		{
			return _type;
		}

		public String getPunishTitle()
		{
			return _punishTitle;
		}

		public long getTime()
		{
			return _time;
		}

		public String[] getPunishDescription()
		{
			return _punishDescription;
		}
		
		public int getId()
		{
			return _id;
		}
	}

	public enum Moderator
	{
		BLACKMAIL(
				14,
				(byte) 4,
				"Using Blackmail for your benefit.",
				Rank.MODERATOR,
				Type.BAN,
				"Blackmail",
				604800000,
				new String[]
						{
								"",
								C.White + "Blackmailing users on the server,",
								C.White + "if the incident takes place elsewhere,",
								C.White + "no actions need to take place."
						},
				11
		),

		HACKUSATION(
				15,
				(byte) 4,
				"Accusing players of hacking.",
				Rank.MODERATOR,
				Type.MUTE,
				"Hackusation",
				43200000,
				new String[]
						{
								"",
								C.White + "Continues accusations on",
								C.White + "online player(s) for hacking."
						},
				12
		),

		OFFENCIVE_SKIN(
				23,
				(byte) 4,
				"Offencive/Rude skin.",
				Rank.MODERATOR,
				Type.BAN,
				"Offencive Skin",
				2592000000L,
				new String[]
						{
								"",
								C.White + "offensive or sexually explicit skin,",
								C.White + "a warning is required."
						},
				13
		),

		OFFENCIVE_CAPE(
				24,
				(byte) 4,
				"Offencive/Rude cape.",
				Rank.MODERATOR,
				Type.BAN,
				"Offencive Cape",
				2592000000L,
				new String[]
						{
								"",
								C.White + "offensive or sexually explicit cape,",
								C.White + "a warning is required."
						},
				14
		),

		EXPLOITING(
				32,
				(byte) 4,
				"Exploiting bugs/glitches for your benefit.",
				Rank.MODERATOR,
				Type.BAN,
				"Exploiting Bugs/Glitches",
				86400000,
				new String[]
						{
								"",
								C.White + "Exploiting bugs/ glitches,",
								C.White + "any sort of exploit abusing."
						},
				15
		),

		MISLEADING_LINKS(
				33,
				(byte) 4,
				"Promoting misleading links/websites.",
				Rank.MODERATOR,
				Type.BAN,
				"Misleading Links",
				604800000,
				new String[]
						{
								"",
								C.White + "Globally announcing misleading/ malicious links,",
								C.White + "strictly forbidden."
						},
				16
		),

		EXPOSING_DETAILS(
				41,
				(byte) 4,
				"Exposing player(s) personal details.",
				Rank.MODERATOR,
				Type.BAN,
				"Exposing Personal Info",
				1209600000,
				new String[]
						{
								"",
								C.White + "Exposing personal information",
								C.White + "about an online user."
						},
				17
		),

		GRIEFING(
				42,
				(byte) 4,
				"Griefing an area that doesn't belong to you.",
				Rank.MODERATOR,
				Type.BAN,
				"Intentional Griefing",
				172800000,
				new String[]
						{
								"",
								C.White + "Griefing an area that does",
								C.White + "not belong to the user."
						},
				18
		),

		HACKS1(
				50,
				(byte) 4,
				"Using a modified client/ hacks (Low).",
				Rank.MODERATOR,
				Type.BAN,
				"Hacked Client (Severity: Low)",
				1209600000,
				new String[]
						{
								"",
								C.White + "Use of hacked client/ modified client."
						},
				19
		),

		HACKS2(
				51,
				(byte) 4,
				"Using a modified client/ hacks (Medium).",
				Rank.MODERATOR,
				Type.BAN,
				"Hacked Client (Severity: Medium)",
				2592000000L,
				new String[]
						{
								"",
								C.White + "Use of hacked client/ modified client."
						},
				20
		);

		private int _slot;
		private byte _data;
		private String _reason;
		private Rank _requiredRank;
		private Type _type;
		private String _punishTitle;
		private long _time;
		private String[] _punishDescription;
		private int _id;

		Moderator(int slot, byte data, String reason, Rank requiredRank, Type type, String punishTitle, long time, String[] punishDescription, int id)
		{
			_slot = slot;
			_data = data;
			_reason = reason;
			_requiredRank = requiredRank;
			_type = type;
			_punishTitle = punishTitle;
			_time = time;
			_punishDescription = punishDescription;
			_id = id;
		}

		public int getSlot()
		{
			return _slot;
		}

		public byte getData()
		{
			return _data;
		}

		public String getReason()
		{
			return _reason;
		}

		public Rank getRequiredRank()
		{
			return _requiredRank;
		}

		public Type getType()
		{
			return _type;
		}

		public String getPunishTitle()
		{
			return _punishTitle;
		}

		public long getTime()
		{
			return _time;
		}

		public String[] getPunishDescription()
		{
			return _punishDescription;
		}
		
		public int getId()
		{
			return _id;
		}
	}

	public enum Admin
	{
		SCAMMING(
				17,
				(byte) 14,
				"Intentionally Scamming player(s) on " + ServerUtil.SERVER + ".",
				Rank.ADMIN,
				Type.BAN,
				"Scamming",
				1209600000,
				new String[]
						{
								"",
								C.White + "Scamming users on the server,",
								C.White + "if the scamming takes place elsewhere,",
								C.White + "no actions need to take place."
						},
				21
		),

		CHARGEBACK(
				26,
				(byte) 14,
				"Issuing a Chargeback on your purchase(s).",
				Rank.ADMIN,
				Type.BAN,
				"Chargeback",
				157680000000L,
				new String[]
						{
								"",
								C.White + "An issued chargeback on purchased item(s)."
						},
				22
		),

		IMPERSONATION(
				35,
				(byte) 14,
				"Impersonating player(s). (Pretending you're somebody else)",
				Rank.ADMIN,
				Type.BAN,
				"Impersonation",
				1209600000,
				new String[]
						{
								"",
								C.White + "Impersonating a user is strictly forbidden.",
								C.White + "",
								C.White + "Impersonating a staff member, permanent ban."
						},
				23
		),

		FALSE_REPORTING(
				44,
				(byte) 14,
				"Intentionally false reporting players.",
				Rank.ADMIN,
				Type.BAN,
				"False Reporting",
				604800000,
				new String[]
						{
								"",
								C.White + "Excessive false reporting of online users."
						},
				24
		),

		HACKS3(
				53,
				(byte) 14,
				"Using a modified client/ hacks (High).",
				Rank.ADMIN,
				Type.BAN,
				"Hacked Client (Severity: High)",
				10368000000L,
				new String[]
						{
								"",
								C.White + "Use of hacked client/ modified client."
						},
				25
		);

		private int _slot;
		private byte _data;
		private String _reason;
		private Rank _requiredRank;
		private Type _type;
		private String _punishTitle;
		private long _time;
		private String[] _punishDescription;
		private int _id;

		Admin(int slot, byte data, String reason, Rank requiredRank, Type type, String punishTitle, long time, String[] punishDescription, int id)
		{
			_slot = slot;
			_data = data;
			_reason = reason;
			_requiredRank = requiredRank;
			_type = type;
			_punishTitle = punishTitle;
			_time = time;
			_punishDescription = punishDescription;
			_id = id;
		}

		public int getSlot()
		{
			return _slot;
		}

		public byte getData()
		{
			return _data;
		}

		public String getReason()
		{
			return _reason;
		}

		public Rank getRequiredRank()
		{
			return _requiredRank;
		}

		public Type getType()
		{
			return _type;
		}

		public String getPunishTitle()
		{
			return _punishTitle;
		}

		public long getTime()
		{
			return _time;
		}

		public String[] getPunishDescription()
		{
			return _punishDescription;
		}
		
		public int getId()
		{
			return _id;
		}
	}

	public enum Type
	{
		BAN,
		MUTE,
		WARN
	}
	
	public static String getReasonById(int id)
	{
		if(id == 100)
		{
			return "Permanently Banned!";
		}
		
		for(Helper helper : Helper.values())
		{
			if(helper.getId() != id)
				continue;
			
			return helper.getReason();
		}
		
		for(Moderator moderator : Moderator.values())
		{
			if(moderator.getId() != id)
				continue;
			
			return moderator.getReason();
		}
		
		for(Admin admin : Admin.values())
		{
			if(admin.getId() != id)
				continue;
			
			return admin.getReason();
		}
		
		return "-";
	}
}
