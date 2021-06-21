package com.jspidersdb;



import java.sql.SQLException;

public class MainClass {

	public static void main(String[] args)
	{
		MySqlImpl m = new MySqlImpl();
		m.intitDb();
		try 
		{
			System.out.println("Adding name and id");
			m.addName(90,"name90");
			System.out.println();
			System.out.println("Getting name ");
			String name = m.getName(90);
			System.out.println(name);
			System.out.println();
			System.out.println("Updating name");
			m.updateName(90,"Dinga");
			System.out.println();
			System.out.println("After updating name");
			String name1 = m.getName(90);
			System.out.println(name1);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		m.closeConnection();
	}

}
