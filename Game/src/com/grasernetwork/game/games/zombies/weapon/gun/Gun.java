package com.grasernetwork.game.games.zombies.weapon.gun;

import com.grasernetwork.game.games.zombies.weapon.Weapon;
import org.bukkit.entity.Player;

/**
 * Created by Teddeh on 01/04/2016.
 */
public abstract class Gun extends Weapon
{
	private final int totalClipAmmo, totalAmmoHold;
	private int clipAmmo, ammoHold;
	private long reloadTime;
	private float recoil;

	public Gun(int id, String name, int totalClipAmmo, int totalAmmoHold, long reloadTime, float recoil)
	{
		super(id, name);

		this.totalClipAmmo = totalClipAmmo;
		this.totalAmmoHold = totalAmmoHold;
		this.reloadTime = reloadTime;
		this.clipAmmo = totalClipAmmo;
		this.ammoHold = totalAmmoHold;
		this.recoil = recoil;
	}

	public void shoot(Player player)
	{}

	public void reload()
	{
		if (isOutOfAmmo()) return;

		if (!canReload()) return;

		//TODO run reload time

		int ammoToSpare = 0;
		if (clipAmmo > 0) ammoToSpare = clipAmmo;

		if (ammoHold < totalClipAmmo)
		{
			if (ammoToSpare > 0)
			{
				if (ammoHold + ammoToSpare < totalClipAmmo)
				{
					clipAmmo += ammoHold;
					ammoHold = 0;
					return;
				}

				int passedOver = ((ammoHold + ammoToSpare) % totalClipAmmo);
				clipAmmo = totalClipAmmo;
				ammoHold = passedOver;
				return;
			}

			clipAmmo = ammoHold;
			ammoHold = 0;
			return;
		}

		if (ammoToSpare > 0)
		{
			ammoHold = (ammoHold - totalClipAmmo) + ammoToSpare;
			clipAmmo = totalClipAmmo;
			return;
		}

		ammoHold -= totalClipAmmo;
		clipAmmo = totalClipAmmo;
	}

	public boolean isFullClip()
	{
		return clipAmmo == totalClipAmmo;
	}

	public boolean canReload()
	{
		return !isFullClip() && ammoHold > 0;
	}

	public boolean isOutOfAmmo()
	{
		return (!canReload() && clipAmmo <= 0);
	}

	public int getTotalClipAmmo()
	{
		return totalClipAmmo;
	}

	public int getTotalAmmoHold()
	{
		return totalAmmoHold;
	}

	public int getClipAmmo()
	{
		return clipAmmo;
	}

	public int getAmmoHold()
	{
		return ammoHold;
	}

	public long getReloadTime()
	{
		return reloadTime;
	}

	public float getRecoil()
	{
		return recoil;
	}

	public void reduceClipAmmo(int amount)
	{
		if (clipAmmo - amount < 0)
		{
			reload();
			return;
		}

		clipAmmo -= amount;
	}
}
