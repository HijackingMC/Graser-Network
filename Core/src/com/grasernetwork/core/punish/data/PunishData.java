package com.grasernetwork.core.punish.data;

import java.util.List;

public class PunishData
{
	private List<Ban> _ban;
	private List<Mute> _mute;
	private List<Warn> _warn;
	
	public PunishData(List<Ban> ban, List<Mute> mute, List<Warn> warn)
	{
		//Ban
		_ban = ban;
		
		//Mute
		_mute = mute;
		
		//Warn
		_warn = warn;
	}
	
	public List<Ban> getBanData()
	{
		return _ban;
	}
	
	public List<Mute> getMuteData()
	{
		return _mute;
	}
	
	public List<Warn> getWarnData()
	{
		return _warn;
	}
	
	public void addBanData(Ban ban)
	{
		_ban.add(ban);
	}
	
	public void addMuteData(Mute mute)
	{
		_mute.add(mute);
	}
	
	public void addWarnData(Warn warn)
	{
		_warn.add(warn);
		System.out.println("Adding");
		System.out.println(_warn.size());
	}
}
