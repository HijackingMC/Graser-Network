package com.grasernetwork.lobby.cosmetic.hat;

import com.grasernetwork.lobby.cosmetic.hat.type.*;

public enum HatList
{
	/** Characters */
	BATMAN(new HatBatman(), 1),
	DEADPOOL(new HatDeadpool(), 2),
	DOGE(new HatDoge(), 3),
	LUIGI(new HatLuigi(), 4),
	MARIO(new HatMario(), 5),
	PATRICK(new HatPatrick(), 6),
	SONIC(new HatSonic(), 7),
	SPONGEBOB(new HatSpongebob(), 8),
	STITCH(new HatStitch(), 9),
	
	/** Food */
	POPCORN(new HatPopcorn(), 10),
	PEPSI(new HatPepsi(), 11),
	NUTELLA(new HatNutella(), 12),
	
	/** Misc */
	BEACHBALL(new HatBeachball(), 13),
	RUBIKCUBE(new HatRubikCube(), 14),
	SKULL(new HatSkull(), 15),
	BEE(new HatBee(), 16),
	
	/** Emoji */
	ANGEL(new HatAngel(), 17),
	BIGGRIN(new HatBigGrin(), 18),
	BIGSMILE(new HatBigSmile(), 19),
	COOL(new HatCool(), 20),
	DEAD(new HatDead(), 21),
	DERP(new HatDerp(), 22),
	EMBARRASED(new HatEmbarrased(), 23),
	SMILE(new HatSmile(), 24),
	SUPRISED(new HatSuprised(), 25),
	WINK(new HatWink(), 26);
	
	private Hat _hat;
	private int _hatId;

	HatList(Hat hat, int hatId)
	{
		_hat = hat;
		_hatId = hatId;
	}
	
	public Hat getHatClass()
	{
		return _hat;
	}
	
	public int getHatId()
	{
		return _hatId;
	}
	
	public static HatList getById(int id)
	{
		for(HatList hat : HatList.values())
		{
			if(hat.getHatId() != id)
				continue;
			
			return hat;
		}
		
		return null;
	}
}
