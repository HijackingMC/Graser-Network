package com.grasernetwork.lobby.cosmetic.pet;

import com.grasernetwork.lobby.cosmetic.misc.RarityType;
import com.grasernetwork.lobby.cosmetic.pet.data.PetData;
import com.grasernetwork.lobby.cosmetic.pet.data.food.Appertite;
import com.grasernetwork.lobby.cosmetic.pet.data.food.Food;
import com.grasernetwork.lobby.cosmetic.pet.data.food.appertite.*;
import com.grasernetwork.lobby.cosmetic.pet.type.*;
import com.grasernetwork.util.C;
import net.minecraft.server.v1_9_R1.Entity;
import net.minecraft.server.v1_9_R1.EntityTypes;
import net.minecraft.server.v1_9_R1.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.entity.Player;

import java.util.Map;

public enum PetType
{
	BLAZE(
			PetBlaze.class,
			"Blaze", 
			"Yjc4ZWYyZTRjZjJjNDFhMmQxNGJmZGU5Y2FmZjEwMjE5ZjViMWJmNWIzNWE0OWViNTFjNjQ2Nzg4MmNiNWYwIn19fQ==", 
			61,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			1, 
			RarityType.COMMON,
			5000
	),
			
	CAVE_SPIDER(
			PetCaveSpider.class, 
			"Cave Spider", 
			"NDE2NDVkZmQ3N2QwOTkyMzEwN2IzNDk2ZTk0ZWViNWMzMDMyOWY5N2VmYzk2ZWQ3NmUyMjZlOTgyMjQifX19", 
			59,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			26,
			RarityType.COMMON,
			5000
	),
	
	CHICKEN(
			PetChicken.class, 
			"Chicken", 
			"MTYzODQ2OWE1OTljZWVmNzIwNzUzNzYwMzI0OGE5YWIxMWZmNTkxZmQzNzhiZWE0NzM1YjM0NmE3ZmFlODkzIn19fQ==", 
			93,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			2, 
			RarityType.COMMON,
			5000
	),
	
	COW(
			PetCow.class, 
			"Cow", 
			"NWQ2YzZlZGE5NDJmN2Y1ZjcxYzMxNjFjNzMwNmY0YWVkMzA3ZDgyODk1ZjlkMmIwN2FiNDUyNTcxOGVkYzUifX19", 
			92,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			3, 
			RarityType.COMMON,
			5000
	),
	
	CREEPER(
			PetCreeper.class, 
			"Creeper", 
			"NDkxZmU4OGE3YTQyNWQ1ODlkYWMzZWYxOTNmNWI1OWE1OWE4MTE3Y2MxOWFlZjQ4YjJjNDg1ZTQ2MmY4YjkifX19", 
			50,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			4, 
			RarityType.COMMON,
			5000
	),
	
	ENDERMAN(
			PetEnderman.class, 
			"Enderman", 
			"N2E1OWJiMGE3YTMyOTY1YjNkOTBkOGVhZmE4OTlkMTgzNWY0MjQ1MDllYWRkNGU2YjcwOWFkYTUwYjljZiJ9fX0=", 
			58,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			5, 
			RarityType.COMMON,
			5000
	),
	
	GUARDIAN(
			PetGuardian.class, 
			"Guardian", 
			"OTMyYzI0NTI0YzgyYWIzYjNlNTdjMjA1MmM1MzNmMTNkZDhjMGJlYjhiZGQwNjM2OWJiMjU1NGRhODZjMTIzIn19fQ==", 
			68,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			6, 
			RarityType.COMMON,
			5000
	),
	
	HORSE(
			PetHorse.class, 
			"Horse", 
			"NjE5MDI4OTgzMDg3MzBjNDc0NzI5OWNiNWE1ZGE5YzI1ODM4YjFkMDU5ZmU0NmZjMzY4OTZmZWU2NjI3MjkifX19", 
			100,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			7,
			RarityType.COMMON,
			5000
	),
			
	IRON_GOLEM(
			PetIronGolem.class, 
			"Iron Golem", 
			"ODkwOTFkNzllYTBmNTllZjdlZjk0ZDdiYmE2ZTVmMTdmMmY3ZDQ1NzJjNDRmOTBmNzZjNDgxOWE3MTQifX19", 
			99,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			8,
			RarityType.COMMON,
			5000
	),
			
	MAGMA_CUBE(
			PetMagmaCube.class, 
			"Magma Cube", 
			"Mzg5NTdkNTAyM2M5MzdjNGM0MWFhMjQxMmQ0MzQxMGJkYTIzY2Y3OWE5ZjZhYjM2Yjc2ZmVmMmQ3YzQyOSJ9fX0=", 
			62,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			9,
			RarityType.COMMON,
			5000
	),
			
	MUSHROOM_COW(
			PetMushroom.class, 
			"Mushroom Cow", 
			"ZDBiYzYxYjk3NTdhN2I4M2UwM2NkMjUwN2EyMTU3OTEzYzJjZjAxNmU3YzA5NmE0ZDZjZjFmZTFiOGRiIn19fQ==", 
			96,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			10,
			RarityType.COMMON,
			5000
	),
			
	OCELOT(
			PetOcelot.class, 
			"Ocelot", 
			"NTY1N2NkNWMyOTg5ZmY5NzU3MGZlYzRkZGNkYzY5MjZhNjhhMzM5MzI1MGMxYmUxZjBiMTE0YTFkYjEifX19", 
			98,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			11,
			RarityType.COMMON,
			5000
	),
			
	PIG(
			PetPig.class, 
			"Pig", 
			"NjIxNjY4ZWY3Y2I3OWRkOWMyMmNlM2QxZjNmNGNiNmUyNTU5ODkzYjZkZjRhNDY5NTE0ZTY2N2MxNmFhNCJ9fX0=", 
			90,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			12,
			RarityType.COMMON,
			5000
	),
			
	PIG_ZOMBIE(
			PetZombiePig.class, 
			"Zombie Pigman", 
			"NzRlOWM2ZTk4NTgyZmZkOGZmOGZlYjMzMjJjZDE4NDljNDNmYjE2YjE1OGFiYjExY2E3YjQyZWRhNzc0M2ViIn19fQ==", 
			57,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			13,
			RarityType.COMMON,
			5000
	),
			
	RABBIT(
			PetRabbit.class, 
			"Rabbit", 
			"ZGM3YTMxN2VjNWMxZWQ3Nzg4Zjg5ZTdmMWE2YWYzZDJlZWI5MmQxZTk4NzljMDUzNDNjNTdmOWQ4NjNkZTEzMCJ9fX0=", 
			101,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			14,
			RarityType.COMMON,
			5000
	),
			
	SHEEP(
			PetSheep.class, 
			"Sheep", 
			"ZjMxZjljY2M2YjNlMzJlY2YxM2I4YTExYWMyOWNkMzNkMThjOTVmYzczZGI4YTY2YzVkNjU3Y2NiOGJlNzAifX19", 
			91,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			15,
			RarityType.COMMON,
			5000
	),
			
	SKELETON(
			PetSkeleton.class, 
			"Skeleton", 
			"NWNkNzEzYzVmNWU0NmRhNDM2YThmNTRiNTIzZDQzYWYyOWY3YWU4ZmIxODQ3OTJjY2E3M2IxNzE3ZmVhYTYxIn19fQ==", 
			51,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			16, 
			RarityType.COMMON,
			5000
	),
			
	SLIME(
			PetSlime.class, 
			"Slime", 
			"MTZhZDIwZmMyZDU3OWJlMjUwZDNkYjY1OWM4MzJkYTJiNDc4YTczYTY5OGI3ZWExMGQxOGM5MTYyZTRkOWI1In19fQ==", 
			55,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			17, 
			RarityType.COMMON,
			5000
	),
			
	SNOWMAN(
			PetSnowman.class, 
			"Snowman", 
			"MWZkZmQxZjc1MzhjMDQwMjU4YmU3YTkxNDQ2ZGE4OWVkODQ1Y2M1ZWY3MjhlYjVlNjkwNTQzMzc4ZmNmNCJ9fX0=", 
			97,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			18,
			RarityType.COMMON,
			5000
	),
			
	SPIDER(
			PetSpider.class, 
			"Spider", 
			"Y2Q1NDE1NDFkYWFmZjUwODk2Y2QyNThiZGJkZDRjZjgwYzNiYTgxNjczNTcyNjA3OGJmZTM5MzkyN2U1N2YxIn19fQ==", 
			52,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			19,
			RarityType.COMMON,
			5000
	),
			
	SQUID(
			PetSquid.class, 
			"Squid", 
			"MDE0MzNiZTI0MjM2NmFmMTI2ZGE0MzRiODczNWRmMWViNWIzY2IyY2VkZTM5MTQ1OTc0ZTljNDgzNjA3YmFjIn19fQ==", 
			94,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			20,
			RarityType.COMMON,
			5000
	),
			
	VILLAGER(
			PetVillager.class, 
			"Villager", 
			"ODIyZDhlNzUxYzhmMmZkNGM4OTQyYzQ0YmRiMmY1Y2E0ZDhhZThlNTc1ZWQzZWIzNGMxOGE4NmU5M2IifX19", 
			120,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			21,
			RarityType.COMMON,
			5000
	),
			
	WITCH(
			PetWitch.class, 
			"Witch", 
			"OTFmMTUwODU5MDI5MWJlZTA1NjJkMzJhZDc1NDRmNjI4YWVhZDg5ZWEzY2FhNTRkYzg3NTEyMGFjOTViMWZhIn19fQ==", 
			66,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			22,
			RarityType.COMMON,
			5000
	),
			
	WITHER(
			PetWither.class, 
			"Wither", 
			"Y2RmNzRlMzIzZWQ0MTQzNjk2NWY1YzU3ZGRmMjgxNWQ1MzMyZmU5OTllNjhmYmI5ZDZjZjVjOGJkNDEzOWYifX19", 
			64,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			23,
			RarityType.COMMON,
			5000
	),
			
	WOLF(
			PetWolf.class, 
			"Wolf", 
			"NjlkMWQzMTEzZWM0M2FjMjk2MWRkNTlmMjgxNzVmYjQ3MTg4NzNjNmM0NDhkZmNhODcyMjMxN2Q2NyJ9fX0=", 
			95,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			24,
			RarityType.COMMON,
			5000
	),
			
	ZOMBIE(
			PetZombie.class, 
			"Zombie", 
			"YTYyMjQ5NDEzMTRiY2EyZWJiYjY2YjEwZmZkOTQ2ODBjYzk4YzM0MzVlZWI3MWEyMjhhMDhmZDQyYzI0ZGIifX19", 
			54,
			new Food(
					new Appertite[]
							{
							new AppertiteApple("Apple", new int[] {20,10,0,0}),
							new AppertiteBread("Bread", new int[] {10,0,0,0}),
							new AppertiteCarrot("Carrot", new int[] {5,0,0,0}),
							new AppertiteChocolate("Chocolate", new int[] {10,0,0,20}),
							new AppertiteClownfish("Clownfish", new int[] {5,0,0,0}),
							new AppertiteCookedFish("Cooked Fish", new int[] {5,0,0,0}),
							new AppertiteCookedSalmon("Cooked Salmon", new int[] {5,0,0,0}),
							new AppertiteCookie("Cookie", new int[] {10,0,0,0}),
							new AppertiteGoldenApple("Golden Apple", new int[] {40,10,50,0}),
							new AppertiteHumanFlesh("Human Flesh", new int[] {10,0,0,0}),
							new AppertiteInstantDamage("Instant Damage", new int[] {0,30,0,80}),
							new AppertiteInstantHealth("Instant Health", new int[] {0,50,80}),
							new AppertiteMilk("Milk", new int[] {5,20,0,0}),
							new AppertitePotato("Potato", new int[] {5,0,0,0}),
							new AppertiteRawBeef("Raw Beef", new int[] {10,0,0,0}),
							new AppertiteRawFish("Raw Fish", new int[] {5,0,0,0}),
							new AppertiteRottenFlesh("Rotten Flesh", new int[] {0,0,0,10}),
							new AppertiteWater("Water", new int[] {0,40,0,0}),
							new AppertiteWheat("Wheat", new int[] {40,0,0,0})
							}
					),
			25,
			RarityType.COMMON,
			5000
	);
	
	private String _name;
	private Integer _id;
	private String _url;
	private Class<? extends Entity> _ent;
	private Food _food;
	private Integer _petId;
	private RarityType _rarityType;
	private Integer _price;
	
	PetType(Class<? extends Entity> ent, String name, String url, int id, Food food, int petId, RarityType rarityType, int price)
	{
		_ent = ent;
		_name = name;
		_id = id;
		_url = url;
		_food = food;
		_petId = petId;
		_rarityType = rarityType;
		_price = price;
	}
	
	public Class<? extends Entity> getEClass()
	{
		return _ent;
	}
	
	public String getPetName()
	{
		return _name;
	}
	
	public String getPetData()
	{
		return _url;
	}
	
	public Food getFood()
	{
		return _food;
	}
	
	public int getPetId()
	{
		return _petId;
	}
	
	public RarityType getRarityType()
	{
		return _rarityType;
	}
	
	public int getPrice()
	{
		return _price;
	}
	
	public static PetType getById(int id)
	{
		for(PetType pet : PetType.values())
		{
			if(pet.getPetId() != id)
				continue;
			
			return pet;
		}
		
		return null;
	}
	
	public Entity givePet(Player player, PetType petType, PetData petData)
	{
		World world = ((CraftWorld) player.getWorld()).getHandle();
		Entity entity = null;
		
		switch (petType)
		{
		case BLAZE:
			PetBlaze petBlaze = new PetBlaze(world);
			petBlaze.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petBlaze.setCustomNameVisible(true);
			entity = petBlaze;
			break;
			
		case CAVE_SPIDER:
			PetCaveSpider petCaveSpider = new PetCaveSpider(world);
			petCaveSpider.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petCaveSpider.setCustomNameVisible(true);
			entity = petCaveSpider;
			break;
			
		case CHICKEN:
			PetChicken petChicken = new PetChicken(world);
			petChicken.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petChicken.setCustomNameVisible(true);
			entity = petChicken;
			break;
			
		case COW:
			PetCow petCow = new PetCow(world);
			petCow.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petCow.setCustomNameVisible(true);
			entity = petCow;
			break;
			
		case CREEPER:
			PetCreeper petCreeper = new PetCreeper(world);
			petCreeper.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petCreeper.setCustomNameVisible(true);
			entity = petCreeper;
			break;
			
		case ENDERMAN:
			PetEnderman petEnderman = new PetEnderman(world);
			petEnderman.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petEnderman.setCustomNameVisible(true);
			entity = petEnderman;
			break;
			
		case GUARDIAN:
			PetGuardian petGuardian = new PetGuardian(world);
			petGuardian.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petGuardian.setCustomNameVisible(true);
			entity = petGuardian;
			break;
			
		case HORSE:
			PetHorse petHorse = new PetHorse(world);
			petHorse.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petHorse.setCustomNameVisible(true);
			entity = petHorse;
			break;
			
		case IRON_GOLEM:
			PetIronGolem petIronGolem = new PetIronGolem(world);
			petIronGolem.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petIronGolem.setCustomNameVisible(true);
			entity = petIronGolem;
			break;
			
		case MAGMA_CUBE:
			PetMagmaCube pet = new PetMagmaCube(world);
			pet.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			pet.setCustomNameVisible(true);
			entity = pet;
			break;
			
		case MUSHROOM_COW:
			PetMushroom petMushroom = new PetMushroom(world);
			petMushroom.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petMushroom.setCustomNameVisible(true);
			entity = petMushroom;
			break;
			
		case OCELOT:
			PetOcelot petOcelot = new PetOcelot(world);
			petOcelot.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petOcelot.setCustomNameVisible(true);
			entity = petOcelot;
			break;
			
		case PIG:
			PetPig petPig = new PetPig(world);
			petPig.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petPig.setCustomNameVisible(true);
			entity = petPig;
			break;
			
		case PIG_ZOMBIE:
			PetZombiePig petPigZombie = new PetZombiePig(world);
			petPigZombie.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petPigZombie.setCustomNameVisible(true);
			entity = petPigZombie;
			break;
			
		case RABBIT:
			PetRabbit petRabbit = new PetRabbit(world);
			petRabbit.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petRabbit.setCustomNameVisible(true);
			entity = petRabbit;
			break;
			
		case SHEEP:
			PetSheep petSheep = new PetSheep(world);
			petSheep.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petSheep.setCustomNameVisible(true);
			entity = petSheep;
			break;
			
		case SKELETON:
			PetSkeleton petSkeleton = new PetSkeleton(world);
			petSkeleton.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petSkeleton.setCustomNameVisible(true);
			entity = petSkeleton;
			break;
			
		case SLIME:
			PetSlime petSlime = new PetSlime(world);
			petSlime.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petSlime.setCustomNameVisible(true);
			entity = petSlime;
			break;
			
		case SNOWMAN:
			PetSnowman petSnowman = new PetSnowman(world);
			petSnowman.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petSnowman.setCustomNameVisible(true);
			entity = petSnowman;
			break;
		
		case SPIDER:
			PetSpider petSpider = new PetSpider(world);
			petSpider.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petSpider.setCustomNameVisible(true);
			entity = petSpider;
			break;
			
		case SQUID:
			PetSquid petSquid = new PetSquid(world);
			petSquid.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petSquid.setCustomNameVisible(true);
			entity = petSquid;
			break;
		
		case VILLAGER:
			PetVillager petVillager = new PetVillager(world);
			petVillager.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petVillager.setCustomNameVisible(true);
			entity = petVillager;
			break;
		
		case WITCH:
			PetWitch petWitch = new PetWitch(world);
			petWitch.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petWitch.setCustomNameVisible(true);
			entity = petWitch;
			break;
			
		case WITHER:
			PetWither petWither = new PetWither(world);
			petWither.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petWither.setCustomNameVisible(true);
			entity = petWither;
			break;
			
		case WOLF:
			PetWolf petWolf = new PetWolf(world);
			petWolf.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petWolf.setCustomNameVisible(true);
			entity = petWolf;
			break;
			
		case ZOMBIE:
			PetZombie petZombie = new PetZombie(world);
			petZombie.setCustomName(C.White + "(" + C.YellowB + "PET" + C.Reset + C.White + ") " + C.Aqua + (petData.getName().equalsIgnoreCase("null") ? _name : petData.getName()));
			petZombie.setCustomNameVisible(true);
			entity = petZombie;
			break;

		default:
			break;
		}
		
		((Map) PetReflection.getPrivateField("c", EntityTypes.class, null)).put(_name, _ent);
		((Map) PetReflection.getPrivateField("d", EntityTypes.class, null)).put(_ent, _name);
		((Map) PetReflection.getPrivateField("f", EntityTypes.class, null)).put(_ent, Integer.valueOf(_id));
		
		Location l = player.getLocation();
		entity.setLocation(l.getX(), l.getY(), l.getZ(), 0, 0);
		world.addEntity(entity);
//		entity.spawnIn(world);
		return entity;
	}
}
