package term;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class ProjectQueries {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/termproject", "root", "surjona98!");
            System.out.println("DB Connection Success!");

            Statement stmt = conn.createStatement();
            
            /* LOGAN SKIDMORE QUERIES */
            
            // Query 1: Adoption transaction with the highest total cost.
            String sql1 = "SELECT Adoption_Transaction.TransactionID, Adoption_Transaction.AdoptionDate, "
                    + "Adoption_Transaction.TotalCost, Adopter.FirstName, "
                    + "Adopter.LastName "
                    + "FROM Adoption_Transaction "
                    + "JOIN Adopter ON Adoption_Transaction.AdopterID = Adopter.AdopterID "
                    + "WHERE Adoption_Transaction.TotalCost = ( "
                    + "SELECT MAX(TotalCost) FROM Adoption_Transaction );";
    		
    		 // Query 2: Employees who have handled the highest number of adoptions.
    		String sql2 = "SELECT Employee.FirstName, Employee.LastName, "
                    + "COUNT(Adoption_Transaction.EmployeeID) AS TotalAdoptions "
                    + "FROM Employee "
                    + "JOIN Adoption_Transaction ON Employee.EmployeeID = Adoption_Transaction.EmployeeID "
                    + "WHERE Adoption_Transaction.AdoptionDate = ( "
                    + "SELECT MAX(AdoptionDate) FROM Adoption_Transaction ) "
                    + "GROUP BY Employee.EmployeeID "
                    + "ORDER BY TotalAdoptions DESC;";


    		// Query 3: Displays Info on pets that are less than $30 to adopt
    		String sql3= "SELECT Name, Species, Breed, AdoptionFee "
                   + "FROM Pet "
                   + "where AdoptionFee < 30 "
                   + "order by AdoptionFee;";
    		
    		
    		
    		
    		 /* CELINE OGERO QUERIES */
    		// Query 1: Retrieve adopters who adopted dogs
            String dogAdoptersQuery = "SELECT Adopter.FirstName, Adopter.LastName " +
                    "FROM Adopter " +
                    "JOIN Adoption_Transaction ON Adopter.AdopterID = Adoption_Transaction.AdopterID " +
                    "JOIN Pet ON Adoption_Transaction.PetID = Pet.PetID " +
                    "WHERE Pet.Species = 'Dog'";
            
            //query 2 retrieve the pet with the highest adoption fee
            String highestAdoptionFeeQuery = "SELECT PetID, Name, Species, MAX(AdoptionFee) AS HighestAdoptionFee " +
                    "FROM Pet GROUP BY PetID, Name, Species ORDER BY HighestAdoptionFee DESC LIMIT 1";
            
            //query 3. 
            // create a view that displays information about employees who are 
            // caretakers and their assigned adoption centers
            String createCaretakerViewQuery = "CREATE VIEW CaretakerCenters AS " +
            	    "SELECT Employee.FirstName, Employee.LastName, AdoptionCenter.Name " +
            	    "FROM Employee " +
            	    "JOIN AdoptionCenter ON Employee.CenterID = AdoptionCenter.CenterID " +
            	    "WHERE Employee.Position = 'Caretaker'";
            	Statement createViewStmt = conn.createStatement();
            	createViewStmt.executeUpdate(createCaretakerViewQuery);
                 	
            /* JACOB WILKINS QUERIES */
           	// Find the transaction data for every transaction that involved someone from the manager position.
            	String managerTransactionData = "SELECT Atr.TransactionID, E.EmployeeID, E.LastName, E.FirstName " +
                        "FROM adoption_transaction Atr " +
                        "JOIN employee E ON Atr.employeeID = E.employeeID " +
                        "WHERE Position = 'Manager' " +
                        "ORDER BY TransactionID DESC";
    			String discountstoredProcedureSql =
    					"CREATE PROCEDURE has_discount(IN d_adopterID INT)\n" +
    		                    "BEGIN\n" +
    		                    "    DECLARE discount_status DECIMAL(5,2);\n" +
    		                    "    SELECT Discount INTO discount_status\n" +
    		                    "    FROM adoption_transaction\n" +
    		                    "    WHERE d_adopterID = AdopterID;\n" +
    		                    "    START TRANSACTION;\n" +
    		                    "    IF discount_status = 0.00 THEN\n" +
    		                    "        SELECT 'The adopter does not have a discount!' AS message;\n" +
    		                    "    ELSE\n" +
    		                    "        SELECT CONCAT('The adopter has a discount of ', '$', discount_status) AS message;\n" +
    		                    "    END IF;\n" +
    		                    "END;";
    			 String employeeviewSql =
    	                    "CREATE OR REPLACE VIEW employee_not_involved1 AS " +
    	                    "SELECT E.EmployeeID, E.LastName, E.FirstName " +
    	                    "FROM employee E " +
    	                    "LEFT JOIN adoption_transaction Atr " +
    	                    "ON E.EmployeeID = Atr.EmployeeID " +
    	                    "WHERE Atr.EmployeeID IS NULL " +
    	                    "ORDER BY EmployeeID;";
    			 
    			 /* ISHAAN PATEL QUERIES */ 
    			 // Question 1 Adoption Transaction with Lowest Total Cost
    				String lowestTotalCost = "SELECT adoption_transaction.TransactionID, adoption_transaction.EmployeeID, adoption_transaction.TotalCost, adopter.FirstName, adopter.LastName\r\n"
    						+ " FROM adoption_transaction\r\n"
    						+ " JOIN adopter ON adoption_transaction.adopterID = adopter.AdopterID \r\n"
    						+ " WHERE adoption_transaction.TotalCost = ( \r\n"
    						+ " SELECT MIN(TotalCost) FROM adoption_transaction );" ;
    				
    				//Question 2 Find Employees and Adopters with the same first name using Inner Join
    				String similarFirstName = 
    							"SELECT AdopterID, adopter.FirstName, adopter.LastName, EmployeeID, employee.FirstName, employee.LastName"
    							+ " FROM adopter"
    							+ " INNER JOIN employee"
    							+ " ON adopter.FirstName = employee.FirstName"
    							+ " ORDER BY EmployeeID";
    				
    				//Question 3 find transactions equal or greater than 100
    				String  equalOrGreaterThan100= 
    						"CREATE PROCEDURE query12()\r\n" +
    							    "BEGIN\r\n" +
    							    "   DECLARE transactionCount INT;\r\n" +
    							    "   SELECT COUNT(*) INTO transactionCount\r\n" +
    							    "   FROM adoption_transaction\r\n" +
    							    "   WHERE TotalCost >= 100;\r\n" +
    							    "   SELECT CONCAT(transactionCount, ' transactions exceed $100') AS ResultMessage;\r\n" 
    							    + "END ";
    							    
      
    			//display the results
    				
            	ResultSet rs = stmt.executeQuery(sql1);
        		System.out.println("==== transaction with the highest total cost Output====");
        		
        		
        		while(rs.next())
        		{
        			int TransactionID = rs.getInt("TransactionID");
        			Date AdoptionDate = rs.getDate("AdoptionDate");
        			String TotalCost = rs.getString("TotalCost");
        			String FirstName = rs.getString("FirstName");
        			String LastName = rs.getString("LastName");
        			
        			
        			System.out.println("TransactionID: "+ TransactionID + "\n");
        			System.out.println("AdoptionDate: "+ AdoptionDate + "\n");
        			System.out.println("TotalCost: "+ TotalCost + "\n");
        			System.out.println("AdopterFirstName: "+ FirstName + "\n");
        			System.out.println("AdopterLastName: "+ LastName + "\n");
        		}
        		
        		
        		System.out.println("==== Employees who have handled the highest number of adoptions.====");
        		ResultSet rs2 = stmt.executeQuery(sql2);
        		while(rs2.next())
        		{
        			String FirstName = rs2.getString("FirstName");
        			String LastName = rs2.getString("LastName");
        			int TotalAdoptions = rs2.getInt("TotalAdoptions");
        			
        			System.out.println("EmployeeFirstName: "+ FirstName + "\n");
        			System.out.println("EmployeeLastName: "+ LastName + "\n");
        			System.out.println("TotalAdoptions: "+ TotalAdoptions + "\n");
        			
        		}
        		
        		System.out.println("====Pets that cost less than $30 ====");
        		ResultSet rs3 = stmt.executeQuery(sql3);
        		while(rs3.next())
        		{
        			String Name = rs3.getString("Name");
        			String Species = rs3.getString("Species");
        			String Breed = rs3.getString("Breed");
        			int AdoptionFee = rs3.getInt("AdoptionFee");
        			
        			
        			System.out.println("Name: "+ Name + "\n");
        			System.out.println("Species: "+ Species + "\n");
        			System.out.println("Breed: "+ Breed + "\n");
        			System.out.println("AdoptionFee: "+ AdoptionFee + "\n");
        					
        		}
        		
        		ResultSet rs4 = stmt.executeQuery(dogAdoptersQuery);
        		System.out.println("==== Adopters who Adopted Dogs ====");
        		while (rs4.next()) {
        		    String firstName = rs4.getString("FirstName");
        		    String lastName = rs4.getString("LastName");

        		    System.out.println("AdopterFirstName: " + firstName);
        		    System.out.println("AdopterLastName: " + lastName + "\n");
        		}

        		ResultSet rs5 = stmt.executeQuery(highestAdoptionFeeQuery);
        		System.out.println("==== Pet with the Highest Adoption Fee ====");
        		while (rs5.next()) {
        		    int petID = rs5.getInt("PetID");
        		    double highestAdoptionFee = rs5.getDouble("HighestAdoptionFee");
        		    String name = rs5.getString("Name");
        		    String species = rs5.getString("Species");

        		    System.out.println("PetID: " + petID);
        		    System.out.println("Name: " + name);
        		    System.out.println("Species: " + species);
        		    System.out.println("HighestAdoptionFee: " + highestAdoptionFee + "\n");
        		}
        		
        	  String selectFromViewQuery = "SELECT * FROM CaretakerCenters";
        	        
        	 	System.out.println("CaretakerCenters view created successfully.");
        		ResultSet rs6 = stmt.executeQuery(selectFromViewQuery);
        		System.out.println("==== Caretakers and Assigned Centers ====");
        		while (rs6.next()) {
        		    String firstName = rs6.getString("FirstName");
        		    String lastName = rs6.getString("LastName");
        		    String Name = rs6.getString("Name");

        		    System.out.println("First Name: " + firstName);
        		    System.out.println("Last Name: " + lastName);
        		    System.out.println("Center Name: " + Name + "\n");
        		}
        		
        		
        		ResultSet rs7 = stmt.executeQuery(managerTransactionData);

    			System.out.println("=== Manager Transaction Data Output===");
    			
    			while (rs7.next()) {
    				String TransactionID = rs7.getString("TransactionID");
    				String EmployeeID = rs7.getString("EmployeeID");
    				String LastName = rs7.getString("LastName");
    				String FirstName = rs7.getString("FirstName");

    				System.out.println("TransactionID: " + TransactionID + "\n" + "EmployeeID: " + EmployeeID + "\n" + "LastName: " +
    				LastName + "\n" + "FirstName: " + FirstName + "\n");
    			}

    			System.out.println("=== Stored Procedure Output===");
    			stmt.executeUpdate(discountstoredProcedureSql);
    			System.out.println("Stored procedure created successfully.");
    			
    			
    			System.out.println("===Employees not involved with a transaction ===");
    			stmt.executeUpdate(employeeviewSql);
    			System.out.println("View created successfully." + "\n");
    			
    			
    	
    			ResultSet rs8 = stmt.executeQuery(lowestTotalCost);
    			System.out.println("===Transaction with Lowest Total Cost===");
    			while (rs8.next()) {
    				String TransactionID = rs8.getString("TransactionID");
    				String EmployeeID = rs8.getString("EmployeeID");
    				String LastName = rs8.getString("LastName");
    				String FirstName = rs8.getString("FirstName");
    	
    				System.out.println("TransactionID: " + TransactionID + "\n" + "EmployeeID: " + EmployeeID + "\n" + "LastName: " +
    						LastName + "\n" + "FirstName: " + FirstName + "\n");
    					
    			} 
    			
    		
    			System.out.println("\n=== Employees & adopters with similar First Name ===");
    			ResultSet rs9 = stmt.executeQuery(similarFirstName);
    			while (rs9.next()) {
    				String adopterID = rs9.getString("AdopterID");
    				String adopterFirstName = rs9.getString("adopter.FirstName");
    				String adopterLastName = rs9.getString("adopter.LastName");
    				String employeeID = rs9.getString("EmployeeID");
    				String employeeFirstName = rs9.getString("employee.FirstName");
    				String employeeLastName = rs9.getString("employee.LastName");
    				System.out.println("adopterID: " + adopterID + "\n" + "adopterFirstName: " + adopterFirstName + "\n"  +  "adopterLastName: " + adopterLastName + "\n" 
    						+ "employeeID: " + employeeID + "\n" + "employeeFirstName: " + employeeFirstName + "\n" + "employeeLastName: " + employeeLastName + "\n");	
    			}
    			
    			System.out.println("===Transactions equal to or greater than 100===");
    			stmt.executeUpdate(equalOrGreaterThan100);
    			System.out.println("Stored procedure created successfully.");

            
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
