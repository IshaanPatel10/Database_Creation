package termproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class termprojectTableCreation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn;
		
		try {
			
		// 1. Driver Loading and DB Connection!
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/termproject2", "root", "CSCE460");
					System.out.println("Successfull DB Connection");
			
		// 2. Write SQL queries to create a DB Table!
			String adopterTable = "CREATE TABLE Adopter ("
					+ "AdopterID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,"
                    + "LastName VARCHAR(45) NULL,"
                    + "FirstName VARCHAR(45) NULL,"
                    + "CellPhone VARCHAR(45) NULL,"
                    + "Address VARCHAR(500) NULL,"
                    + "Email VARCHAR(45) NULL"
                    + ")";
			
			String petTable = "CREATE TABLE Pet ("
					+ "PetID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,"
                    + "Name VARCHAR(45) NULL,"
                    + "Species VARCHAR(45) NULL,"
                    + "Breed VARCHAR(45) NULL,"
                    + "Age VARCHAR(45) NULL,"
                    + "AdoptionFee DECIMAL NULL"
                    + ")";
			
			String adoptionCenterTable = "CREATE TABLE AdoptionCenter ("
					+ "CenterID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,"
                    + "Name VARCHAR(45) NULL,"
                    + "Location VARCHAR(45) NULL,"
                    + "CellPhone VARCHAR(45) NULL"
                    + ")";
			
			String employeeTable = "CREATE TABLE Employee ("
					+ "EmployeeID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,"
                    + "LastName VARCHAR(45) NULL,"
                    + "FirstName VARCHAR(45) NULL,"
                    + "Position VARCHAR(45) NULL,"
                    + "CellPhone VARCHAR(45) NULL,"
                    + "Email VARCHAR(45) NULL,"
                    + "CenterID INT,"
                    + "FOREIGN KEY (CenterID) REFERENCES AdoptionCenter(CenterID)"
                    + ")";
			
			String adoptionTransactionTable = "CREATE TABLE adoption_transaction ("
        			+ "TransactionID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,"
        			+ "AdoptionDate DATE NULL,"
        			+ "BaseAdoptionFee DECIMAL(5,2) NULL,"
        			+ "NecessitiesCost DECIMAL(5,2) NULL,"
        			+ "Discount DECIMAL(5,2) NULL,"
        			+ "TotalCost DECIMAL(5,2) NULL,"
        			+ "AdopterID INT,"
        			+ "PetID INT,"
        			+ "EmployeeID INT,"
        			+ "CenterID INT,"
        			+ "FOREIGN KEY (PetID) REFERENCES Pet(PetID),"
        			+ "FOREIGN KEY (AdopterID) REFERENCES Adopter(AdopterID),"
        			+ "FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID),"
        			+ "FOREIGN KEY (CenterID) REFERENCES AdoptionCenter(CenterID)"
        			+ ")";
			
		// 3. Create the statement object to execute SQL queries.
			Statement smt = conn.createStatement();
			
		//4. Execute SQL Query using the execute method of the statement object
			boolean result1 = smt.execute(adopterTable);
			boolean result2 = smt.execute(petTable);
			boolean result3 = smt.execute(adoptionCenterTable);
			boolean result4 = smt.execute(employeeTable);
			boolean result5 = smt.execute(adoptionTransactionTable);
			
			System.out.println("Result: " + result1);
			System.out.println("Result: " + result2);
			System.out.println("Result: " + result3);
			System.out.println("Result: " + result4);
			System.out.println("Result: " + result5);
			
	// 5. Close 
			
		if (smt != null)
			smt.close();
		if (conn != null)
			conn.close();
		} 
		catch(Exception e){
			System.out.println("Error: "+e);
		}

	}
		
	
}