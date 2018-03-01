package com.grasernetwork.lobby.cosmetic.morph;

import com.grasernetwork.lobby.cosmetic.misc.RarityType;
import com.grasernetwork.lobby.cosmetic.morph.type.*;

public enum MorphList
{
//	BAT(MorphBat.class, "NmZmZDgwOGY4MTI3YjRhZDQ1OGQ5ZDJlMTgxYzY5MGFkZjQ4OWE2YWQzMmVlMmFhNGFjZmE2MzQxZmU4NDIifX19"),
	BLAZE(MorphBlaze.class, "Yjc4ZWYyZTRjZjJjNDFhMmQxNGJmZGU5Y2FmZjEwMjE5ZjViMWJmNWIzNWE0OWViNTFjNjQ2Nzg4MmNiNWYwIn19fQ==", 1, RarityType.COMMON),
	CAVE_SPIDER(MorphCaveSpider.class, "NDE2NDVkZmQ3N2QwOTkyMzEwN2IzNDk2ZTk0ZWViNWMzMDMyOWY5N2VmYzk2ZWQ3NmUyMjZlOTgyMjQifX19", 2, RarityType.COMMON),
	CHICKEN(MorphChicken.class, "MTYzODQ2OWE1OTljZWVmNzIwNzUzNzYwMzI0OGE5YWIxMWZmNTkxZmQzNzhiZWE0NzM1YjM0NmE3ZmFlODkzIn19fQ==", 3, RarityType.COMMON),
	COW(MorphCow.class, "NWQ2YzZlZGE5NDJmN2Y1ZjcxYzMxNjFjNzMwNmY0YWVkMzA3ZDgyODk1ZjlkMmIwN2FiNDUyNTcxOGVkYzUifX19", 4, RarityType.COMMON),
	CREEPER(MorphCreeper.class, "NDkxZmU4OGE3YTQyNWQ1ODlkYWMzZWYxOTNmNWI1OWE1OWE4MTE3Y2MxOWFlZjQ4YjJjNDg1ZTQ2MmY4YjkifX19", 5, RarityType.COMMON),
	ENDERMAN(MorphEnderman.class, "N2E1OWJiMGE3YTMyOTY1YjNkOTBkOGVhZmE4OTlkMTgzNWY0MjQ1MDllYWRkNGU2YjcwOWFkYTUwYjljZiJ9fX0=", 6, RarityType.COMMON),
	GUARDIAN(MorphGuardian.class, "OTMyYzI0NTI0YzgyYWIzYjNlNTdjMjA1MmM1MzNmMTNkZDhjMGJlYjhiZGQwNjM2OWJiMjU1NGRhODZjMTIzIn19fQ==", 7, RarityType.COMMON),
	HORSE(MorphHorse.class, "NjE5MDI4OTgzMDg3MzBjNDc0NzI5OWNiNWE1ZGE5YzI1ODM4YjFkMDU5ZmU0NmZjMzY4OTZmZWU2NjI3MjkifX19", 8, RarityType.COMMON),
	IRON_GOLEM(MorphIronGolem.class, "ODkwOTFkNzllYTBmNTllZjdlZjk0ZDdiYmE2ZTVmMTdmMmY3ZDQ1NzJjNDRmOTBmNzZjNDgxOWE3MTQifX19", 9, RarityType.COMMON),
	MAGMA_CUBE(MorphMagmaCube.class, "Mzg5NTdkNTAyM2M5MzdjNGM0MWFhMjQxMmQ0MzQxMGJkYTIzY2Y3OWE5ZjZhYjM2Yjc2ZmVmMmQ3YzQyOSJ9fX0=", 10, RarityType.COMMON),
	MUSHROOM_COW(MorphMooshroom.class, "ZDBiYzYxYjk3NTdhN2I4M2UwM2NkMjUwN2EyMTU3OTEzYzJjZjAxNmU3YzA5NmE0ZDZjZjFmZTFiOGRiIn19fQ==", 11, RarityType.COMMON),
	OCELOT(MorphOcelot.class, "NTY1N2NkNWMyOTg5ZmY5NzU3MGZlYzRkZGNkYzY5MjZhNjhhMzM5MzI1MGMxYmUxZjBiMTE0YTFkYjEifX19", 12, RarityType.COMMON),
	PIG(MorphPig.class, "NjIxNjY4ZWY3Y2I3OWRkOWMyMmNlM2QxZjNmNGNiNmUyNTU5ODkzYjZkZjRhNDY5NTE0ZTY2N2MxNmFhNCJ9fX0=", 13, RarityType.COMMON),
	PIG_ZOMBIE(MorphZombiePig.class, "NzRlOWM2ZTk4NTgyZmZkOGZmOGZlYjMzMjJjZDE4NDljNDNmYjE2YjE1OGFiYjExY2E3YjQyZWRhNzc0M2ViIn19fQ==", 14, RarityType.COMMON),
	RABBIT(MorphRabbit.class, "ZGM3YTMxN2VjNWMxZWQ3Nzg4Zjg5ZTdmMWE2YWYzZDJlZWI5MmQxZTk4NzljMDUzNDNjNTdmOWQ4NjNkZTEzMCJ9fX0=", 15, RarityType.COMMON),
	SHEEP(MorphSheep.class, "ZjMxZjljY2M2YjNlMzJlY2YxM2I4YTExYWMyOWNkMzNkMThjOTVmYzczZGI4YTY2YzVkNjU3Y2NiOGJlNzAifX19", 16, RarityType.COMMON),
	SKELETON(MorphSkeleton.class, "NWNkNzEzYzVmNWU0NmRhNDM2YThmNTRiNTIzZDQzYWYyOWY3YWU4ZmIxODQ3OTJjY2E3M2IxNzE3ZmVhYTYxIn19fQ==", 17, RarityType.COMMON),
	SLIME(MorphSlime.class, "MTZhZDIwZmMyZDU3OWJlMjUwZDNkYjY1OWM4MzJkYTJiNDc4YTczYTY5OGI3ZWExMGQxOGM5MTYyZTRkOWI1In19fQ==", 18, RarityType.COMMON),
	SNOWMAN(MorphSnowman.class, "MWZkZmQxZjc1MzhjMDQwMjU4YmU3YTkxNDQ2ZGE4OWVkODQ1Y2M1ZWY3MjhlYjVlNjkwNTQzMzc4ZmNmNCJ9fX0=", 19, RarityType.COMMON),
	SPIDER(MorphSpider.class, "Y2Q1NDE1NDFkYWFmZjUwODk2Y2QyNThiZGJkZDRjZjgwYzNiYTgxNjczNTcyNjA3OGJmZTM5MzkyN2U1N2YxIn19fQ==", 20, RarityType.COMMON),
	SQUID(MorphSquid.class, "MDE0MzNiZTI0MjM2NmFmMTI2ZGE0MzRiODczNWRmMWViNWIzY2IyY2VkZTM5MTQ1OTc0ZTljNDgzNjA3YmFjIn19fQ==", 21, RarityType.COMMON),
	VILLAGER(MorphVillager.class, "ODIyZDhlNzUxYzhmMmZkNGM4OTQyYzQ0YmRiMmY1Y2E0ZDhhZThlNTc1ZWQzZWIzNGMxOGE4NmU5M2IifX19", 22, RarityType.COMMON),
	WITCH(MorphWitch.class, "OTFmMTUwODU5MDI5MWJlZTA1NjJkMzJhZDc1NDRmNjI4YWVhZDg5ZWEzY2FhNTRkYzg3NTEyMGFjOTViMWZhIn19fQ==", 23, RarityType.COMMON),
	WITHER(MorphWither.class, "Y2RmNzRlMzIzZWQ0MTQzNjk2NWY1YzU3ZGRmMjgxNWQ1MzMyZmU5OTllNjhmYmI5ZDZjZjVjOGJkNDEzOWYifX19", 24, RarityType.COMMON),
	WOLF(MorphWolf.class, "NjlkMWQzMTEzZWM0M2FjMjk2MWRkNTlmMjgxNzVmYjQ3MTg4NzNjNmM0NDhkZmNhODcyMjMxN2Q2NyJ9fX0=", 25, RarityType.COMMON),
	ZOMBIE(MorphZombie.class, "YTYyMjQ5NDEzMTRiY2EyZWJiYjY2YjEwZmZkOTQ2ODBjYzk4YzM0MzVlZWI3MWEyMjhhMDhmZDQyYzI0ZGIifX19", 26, RarityType.COMMON);

	private Class<? extends Morph> _morph;
	private String _mobData;
	private int _morphId;
	private RarityType _rarityType;
	
	MorphList(Class<? extends Morph> morph, String mobData, int morphId, RarityType rarityType)
	{
		_morph = morph;
		_mobData = mobData;
		_morphId = morphId;
		_rarityType = rarityType;
	}
	
	public Class<? extends Morph> getMorph()
	{
		return _morph;
	}
	
	public String getData()
	{
		return _mobData;
	}
	
	public int getMorphId()
	{
		return _morphId;
	}
	
	public RarityType getRarityType()
	{
		return _rarityType;
	}
	
	public static MorphList getById(int id)
	{
		for(MorphList morph : MorphList.values())
		{
			if(morph.getMorphId() != id)
				continue;
			
			return morph;
		}
		
		return null;
	}
}
