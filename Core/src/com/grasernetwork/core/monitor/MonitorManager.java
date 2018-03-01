package com.grasernetwork.core.monitor;

import com.grasernetwork.core.monitor.command.TpsCommand;

/**
 * Created by luke1 on 26/01/2016.
 */
public class MonitorManager
{
    public MonitorManager()
    {
        new TpsCommand();
    }
}
