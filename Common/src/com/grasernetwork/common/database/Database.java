package com.grasernetwork.common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{

	private Connection _connection = null;
	private String IP;
	private String PORT;
	private String DATABASE;
	private String USERNAME;
	private String PASSWORD;

	public Database(String ip, String port, String db, String user, String pass)
	{
		IP = ip;
		PORT = port;
		DATABASE = db;
		USERNAME =  user;
		PASSWORD = pass;
	}

	public void openConnection()
	{
		try
		{
			if (_connection != null)
			{
				if (!_connection.isClosed())
					return;
			}

			String host = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;
			_connection = DriverManager.getConnection(host, USERNAME, PASSWORD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public Connection getConnection()
	{
		return _connection;
	}

	public boolean isClosed()
	{
		try
		{
			return _connection.isClosed();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return true;
	}

	public void closeConnection()
	{
		try
		{
			if (!isClosed())
			{
				_connection.close();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
