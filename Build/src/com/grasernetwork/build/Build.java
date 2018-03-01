package com.grasernetwork.build;

import com.grasernetwork.core.Core;

public class Build extends Core
{

	@Override
	public void enable()
	{
		new BuildManager(this);
	}

	@Override
	public void onDisable()
	{
		super.onDisable();
	}
}
