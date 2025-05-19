package TermProject;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;


public class termProjectSQL {

	public static void main(String[] args) {
		Connection conn;
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/termproject", "root", "Dad&mom10");
			System.out.println("Successful DB Connection");
			
			//2. Write SQL Query
			
			//Question 1 Adoption Transaction with Lowest Total Cost
			String sql1 = "SELECT adoption_transaction.TransactionID, adoption_transaction.EmployeeID, adoption_transaction.TotalCost, adopter.FirstName, adopter.LastName\r\n"
					+ " FROM adoption_transaction\r\n"
					+ " JOIN adopter ON adoption_transaction.adopterID = adopter.AdopterID \r\n"
					+ " WHERE adoption_transaction.TotalCost = ( \r\n"
					+ " SELECT MIN(TotalCost) FROM adoption_transaction );" ;
			
			//Question 2 Find Employees and Adopters with the same first name using Inner Join
			String sql2 = 
						"SELECT AdopterID, adopter.FirstName, adopter.LastName, EmployeeID, employee.FirstName, employee.LastName"
						+ " FROM adopter"
						+ " INNER JOIN employee"
						+ " ON adopter.FirstName = employee.FirstName"
						+ " ORDER BY EmployeeID";
			
			//Question 3
			String sql3 = 
					"CREATE PROCEDURE query12()\r\n" +
						    "BEGIN\r\n" +
						    "   DECLARE transactionCount INT;\r\n" +
						    "   SELECT COUNT(*) INTO transactionCount\r\n" +
						    "   FROM adoption_transaction\r\n" +
						    "   WHERE TotalCost >= 100;\r\n" +
						    "   SELECT CONCAT(transactionCount, ' transactions exceed $100') AS ResultMessage;\r\n" 
						    + "END ";
						    
			
	
			
					
			//3. Create the statement object to execute SQL query
			Statement stmt = conn.createStatement();
		
			// 4. Execute SQL query using the execute method
			ResultSet rs1 = stmt.executeQuery(sql1);
			System.out.println("===Question #1 Output===");
			while (rs1.next()) {
				String TransactionID = rs1.getString("TransactionID");
				String EmployeeID = rs1.getString("EmployeeID");
				String LastName = rs1.getString("LastName");
				String FirstName = rs1.getString("FirstName");
	
				System.out.println("TransactionID: " + TransactionID + "\n" + "EmployeeID: " + EmployeeID + "\n" + "LastName: " +
						LastName + "\n" + "FirstName: " + FirstName);
					
			} 
			
		
			System.out.println("\n===Question #2 Output===");
			ResultSet rs2 = stmt.executeQuery(sql2);
			while (rs2.next()) {
				String adopterID = rs2.getString("AdopterID");
				String adopterFirstName = rs2.getString("adopter.FirstName");
				String adopterLastName = rs2.getString("adopter.LastName");
				String employeeID = rs2.getString("EmployeeID");
				String employeeFirstName = rs2.getString("employee.FirstName");
				String employeeLastName = rs2.getString("employee.LastName");
				String name = rs2.getString("AdopterID");
				System.out.println("adopterID: " + adopterID + "\n" + "adopterFirstName: " + adopterFirstName + "\n" + "adopterFirstName: " + adopterLastName + "\n" +  "adopterLastName: " + adopterLastName + "\n" 
						+ "employeeID: " + employeeID + "\n" + "employeeFirstName: " + employeeFirstName + "\n" + "employeeLastName: " + employeeLastName + "\n");
					
		
			}
			
			System.out.println("===Question #3 Output===");
			stmt.executeUpdate(sql3);
			System.out.println("Stored procedure created successfully.");

					

			
			// 5. close
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			} catch (Exception e) {
			System.out.println("Error: " + e);
			}
		
		
		}
}
