package com.grasernetwork.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class ItemBuilder
{
	private final ItemStack itemstack;
	
	public ItemBuilder(Material material) 
	{
		this.itemstack = new ItemStack(material);
	}
	
	public ItemBuilder setDurability(short durability) {
		itemstack.setDurability(durability);
		return this;
	}
	
	public ItemBuilder setSkullOwner(Player owner)
	{
		ItemMeta meta = itemstack.getItemMeta();
		((SkullMeta)meta).setOwner(owner.getName());
		itemstack.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setSkullOwner(String owner)
	{
		ItemMeta meta = itemstack.getItemMeta();
		((SkullMeta)meta).setOwner(owner);
		itemstack.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setAmount(int amount) 
	{
		itemstack.setAmount(amount);
		return this;
	}
	
	public ItemBuilder setName(String name) 
	{
		ItemMeta meta = itemstack.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemstack.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setLore(String... lore)
	{
		ItemMeta meta = itemstack.getItemMeta();
		
		List<String> list = new ArrayList<String>();
		for (String str : lore)
			list.add(ChatColor.translateAlternateColorCodes('&', str));
		
		meta.setLore(list);
		itemstack.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setLoreArray(List<String> lore)
	{
		ItemMeta meta = itemstack.getItemMeta();
		
		List<String> list = new ArrayList<String>();
		for (String str : lore)
			list.add(ChatColor.translateAlternateColorCodes('&', str));
		
		meta.setLore(list);
		itemstack.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setLore(int line, String lore)
	{
		ItemMeta meta = itemstack.getItemMeta();
		
		List<String> list = new ArrayList<String>();
		if (meta.getLore() != null) 
			list.addAll(meta.getLore());
		
		list.set(line, ChatColor.translateAlternateColorCodes('&', lore));
		
		meta.setLore(list);
		itemstack.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder addLore(String lore) {
		ItemMeta meta = itemstack.getItemMeta();
		
		List<String> list = new ArrayList<String>();
		if (meta.getLore() != null) {
			list.addAll(meta.getLore());
		}
		
		list.add(ChatColor.translateAlternateColorCodes('&', lore));
		
		meta.setLore(list);
		itemstack.setItemMeta(meta);
		return this;
	}
	
	public ItemStack setCustomSkull(String urlString)
	{
		String url = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv" + urlString;
		ItemStack head = itemstack.clone();
		
		if (url.isEmpty())
			return head;
		
		SkullMeta headMeta = (SkullMeta) head.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", url));
		Field profileField;
		try
		{
			profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headMeta, profile);
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		head.setItemMeta(headMeta);
		
		return head;
	}
	
	public ItemStack build() {
		return itemstack.clone();
	}
}