package com.jspidersdb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Dao 
{
	public void intitDb();
	public void addName(int id, String name) throws SQLException;
	public String getName(int id) throws SQLException;
	public void updateName(int id, String newname) throws SQLException;
	public void deleteName(int id) throws SQLException;
	public void closeConnection();
}

class MySqlImpl implements Dao
{
	private String url = "jdbc:mysql://localhost:3306/usersdb";
	private String username = "root";
	private String password = "root@123";
	private Connection con;

	@Override
	public void addName(int id, String name) throws SQLException 
	{
		/*
		 * 2 : Prepare the statement(insert name)
		 * 3 : Execute Query
		 * 4 : Process the result*/
		String insertQuery = "insert into users values(?,?)";
		PreparedStatement pm  = con.prepareStatement(insertQuery);
		pm.setInt(1,id);
		pm.setString(2,name);
		pm.execute();
		System.out.println("Data added to db");
		/*System.out.println("Adding name");
		System.out.println(id);
		System.out.println(name);*/
		
	}

	@Override
	
	public String getName(int id) throws SQLException
	{
		/*
		 * 2 : Prepare the statement(select name)
		 * 3 : Execute Query
		 * 4 : Process the result*/
		String Name = "";
		System.out.println(id);
		String getname = "select name from users where idusers = ?";
		PreparedStatement pm = con.prepareStatement(getname);
		pm.setInt(1, id);
		ResultSet rs = pm.executeQuery();
		rs.next();
		Name = rs.getString("name");
		return Name;
		/*System.out.println("Adding id");
		System.out.println(id);*/
	}

	@Override
	public void updateName(int id, String newname) throws SQLException 
	{
		/*
		 * 2 : Prepare the statement(update newname)
		 * 3 : Execute Query
		 * 4 : Process the result*/
		System.out.println(id);
		System.out.println(newname);
		String updateQuery = "update users set name = ? where idusers = ?";
		PreparedStatement pm = con.prepareStatement(updateQuery);
		pm.setString(1,newname);
		pm.setInt(2,id);
		int count = pm.executeUpdate();
		System.out.println(count+ "row updated");
		/*System.out.println("Updating newname");
		System.out.println(id);
		System.out.println(newname);*/
	}

	@Override
	public void deleteName(int id) throws SQLException 
	{
		/*
		 * 2 : Prepare the statement(delete name)
		 * 3 : Execute Query
		 * 4 : Process the result*/
		
		System.out.println("Deleting name");
		System.out.println(id);
		String deletequery = "delete name from users where id = ?";
		PreparedStatement pm = con.prepareStatement(deletequery);
		pm.setInt(id, 1);
		int count = pm.executeUpdate();
		System.out.println(count+ " rows updated");
	}

	@Override
	public void intitDb() 
	{
		/* 1 : Register the Driver to DriverManager
		 *     and get the DB connection*/
		try
		{
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Successfull ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 /*
		 * 2 : Prepare the statement(SQl)
		 * 3 : Execute Query
		 * 4 : Process the result
		 * 5 : Close the connection
		 * 
		 */
	}

	@Override
	public void closeConnection() 
	{
		// Close the connection
		try 
		{
			con.close();
			System.out.println("Connection Closed");
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}
