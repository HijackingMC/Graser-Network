package com.grasernetwork.game.games.zombies.weapon.gun;

import com.grasernetwork.util.C;
import com.grasernetwork.util.packet.ActionBar;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

/**
 * Created by Teddeh on 02/04/2016.
 */
public class StandardGun extends Gun
{
	private final boolean canUpgrade;
	private final int wallCost, wallAmmoCost, upgradedAmmoCost;
	private final String upgradedName;
	private final GunType gunType;

	public StandardGun(int id, String name, int totalClipAmmo, int totalAmmoHold, long reloadTime, float recoil, boolean canUpgrade, int wallCost, int wallAmmoCost, int upgradedAmmoCost, String upgradedName, GunType gunType)
	{
		super(id, name, totalClipAmmo, totalAmmoHold, reloadTime, recoil);

		this.canUpgrade = canUpgrade;
		this.wallCost = wallCost;
		this.wallAmmoCost = wallAmmoCost;
		this.upgradedAmmoCost = canUpgrade ? upgradedAmmoCost : 0;
		this.upgradedName = upgradedName;
		this.gunType = gunType;
	}

	@Override
	public void shoot(Player player)
	{
		player.launchProjectile(Snowball.class);
		ActionBar.playActionBar(player, C.WhiteB + "Ammo:" + C.Reset + " " + C.RedB + getClipAmmo() + C.Reset + C.White + " / " + C.RedB + getAmmoHold() + C.Reset);
		reduceClipAmmo(1);
	}

	public boolean isUpgradable()
	{
		return canUpgrade;
	}

	public int getWallCost()
	{
		return wallCost;
	}

	public int getWallAmmoCost()
	{
		return wallAmmoCost;
	}

	public int getUpgradedAmmoCost()
	{
		return upgradedAmmoCost;
	}

	public String getUpgradedName()
	{
		return upgradedName;
	}

	public GunType getGunType()
	{
		return gunType;
	}
}
