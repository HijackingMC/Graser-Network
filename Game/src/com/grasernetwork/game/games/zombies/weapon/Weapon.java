package com.grasernetwork.game.games.zombies.weapon;

/**
 * Created by Teddeh on 01/04/2016.
 */
public abstract class Weapon implements Cloneable
{
	private int id;
	private String name;

	public Weapon(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
