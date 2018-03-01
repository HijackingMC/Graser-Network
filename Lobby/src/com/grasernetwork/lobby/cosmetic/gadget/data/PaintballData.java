package com.grasernetwork.lobby.cosmetic.gadget.data;

import org.bukkit.Material;

public class PaintballData
{
	private Material _originalMaterial;
	private byte _originalData;

	private Material _changedMaterial;
	private byte _changedData;

	public PaintballData(Material originalMaterial, Material changedMaterial, byte originalData, byte changedData)
	{
		_originalMaterial = originalMaterial;
		_changedMaterial = changedMaterial;
		_originalData = originalData;
		_changedData = changedData;
	}

	public Material getOriginalMaterial()
	{
		return _originalMaterial;
	}

	public Material getChangedMaterial()
	{
		return _changedMaterial;
	}

	public byte getOriginalData()
	{
		return _originalData;
	}

	public byte getChangedData()
	{
		return _changedData;
	}
}
