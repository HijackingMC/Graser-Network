package com.grasernetwork.lobby.cosmetic.pet.data;

import net.minecraft.server.v1_9_R1.Entity;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.grasernetwork.lobby.cosmetic.pet.PetType;
import com.grasernetwork.lobby.cosmetic.pet.data.emotion.EmotionData;
import com.grasernetwork.lobby.cosmetic.pet.data.food.FoodData;
import com.grasernetwork.lobby.cosmetic.pet.data.level.LevelData;
import com.grasernetwork.util.C;
import com.grasernetwork.util.ItemBuilder;

public class PetData
{
	private PetType _petType;
	private EmotionData _emotionData;
	private LevelData _levelData;
	private FoodData _foodData;
	private String _petName;
	
	public Entity entity;
	
	public PetData(PetType petType, String petName, EmotionData emotionData, LevelData levelData, FoodData foodData)
	{
		_petType = petType;
		_petName = petName;
		_emotionData = emotionData;
		_levelData = levelData;
		_foodData = foodData;
	}
	
	public PetType getPetType()
	{
		return _petType;
	}
	
	public EmotionData getEmotionData()
	{
		return _emotionData;
	}
	
	public LevelData getLevelData()
	{
		return _levelData;
	}
	
	public FoodData getFoodData()
	{
		return _foodData;
	}
	
	public String getName()
	{
		return _petName;
	}
	
	public ItemStack[] calculateStatusItems()
	{
		int total = _foodData.getFoodLevel() + _foodData.getThurstLevel();
		
		if(total >= 160)
		{
			if(total >= 180)
				return getTemplate(Material.STAINED_CLAY, (byte) 3, Material.STAINED_GLASS, (byte) 3, C.LightPurpleB + "Very Happy", 9);
			return getTemplate(Material.STAINED_CLAY, (byte) 3, Material.STAINED_GLASS, (byte) 3, C.LightPurpleB + "Very Happy", 8);
		}
		
		if(total >= 120)
		{
			if(total >= 140)
				return getTemplate(Material.STAINED_CLAY, (byte) 5, Material.STAINED_GLASS, (byte) 5, C.GreenB + "Happy", 7);
			return getTemplate(Material.STAINED_CLAY, (byte) 5, Material.STAINED_GLASS, (byte) 5, C.GreenB + "Happy", 6);
		}
		
		if(total >= 80)
		{
			if(total >= 100)
				return getTemplate(Material.STAINED_CLAY, (byte) 4, Material.STAINED_GLASS, (byte) 4, C.YellowB + "Normal", 5);
			return getTemplate(Material.STAINED_CLAY, (byte) 4, Material.STAINED_GLASS, (byte) 4, C.YellowB + "Normal", 4);
		}
		
		if(total >= 40)
		{
			if(total >= 60)
				return getTemplate(Material.STAINED_CLAY, (byte) 1, Material.STAINED_GLASS, (byte) 1, C.GoldB + "Sad", 3);
			return getTemplate(Material.STAINED_CLAY, (byte) 1, Material.STAINED_GLASS, (byte) 1, C.GoldB + "Sad", 2);
		}
		
		if(total > 0)
		{
			return getTemplate(Material.STAINED_CLAY, (byte) 14, Material.STAINED_GLASS, (byte) 14, C.RedB + "Very Sad", 1);
		}
		
		if(total <= 0)
		{
			return getTemplate(Material.BARRIER, (byte) 0, Material.BARRIER, (byte) 0, C.DarkRed + "Dead", 9);
		}
		
		return getTemplate(Material.STAINED_GLASS, (byte) 0, Material.STAINED_GLASS, (byte) 0, "", 9);
	}
	
	public String getStatus()
	{
		int total = _foodData.getFoodLevel() + _foodData.getThurstLevel();
		
		if(total >= 160)
		{
			return C.LightPurpleB + "Very Happy";
		}
		
		if(total >= 120)
		{
			return C.GreenB + "Happy";
		}
		
		if(total >= 80)
		{
			return C.YellowB + "Normal";
		}
		
		if(total >= 40)
		{
			return C.GoldB + "Sad";
		}
		
		if(total > 0)
		{
			return C.RedB + "Very Sad";
		}
		
		if(total <= 0)
		{
			return C.DarkRed + "Dead";
		}

		return C.DarkRed + "Dead";
	}
	
	private ItemStack[] getTemplate(Material material, byte data, Material material2, byte data2, String mood, int i)
	{
		ItemStack[] item = new ItemStack[9];
		
		for(int x = 0; x < i; x++)
			item[x] = new ItemBuilder(material).setDurability(data).setName(mood).build();
		
		for(int x = i; x < ((9-i) + i); x++)
			item[x] = new ItemBuilder(material2).setDurability(data2).setName(mood).build();
		
		return item;
	}
}
