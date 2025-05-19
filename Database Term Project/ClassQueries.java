package term;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClassQueries {

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/termproject", "root", "surjona98!");
            System.out.println("DB Connection Success!");

            Statement stmt = conn.createStatement();

            // Query 1: Retrieve total transactions
            String totalCostQuery = "SELECT SUM(TotalCost) AS TotalCostOfTransactions FROM Adoption_Transaction";

            // Query 2: Retrieve adopters who adopted dogs
            String dogAdoptersQuery = "SELECT Adopter.FirstName, Adopter.LastName " +
                    "FROM Adopter " +
                    "JOIN Adoption_Transaction ON Adopter.AdopterID = Adoption_Transaction.AdopterID " +
                    "JOIN Pet ON Adoption_Transaction.PetID = Pet.PetID " +
                    "WHERE Pet.Species = 'Dog'";
            
            //query 3 retrieve the pet with the highest adoption fee
            String highestAdoptionFeeQuery = "SELECT PetID, Name, Species, MAX(AdoptionFee) AS HighestAdoptionFee " +
                    "FROM Pet GROUP BY PetID, Name, Species ORDER BY HighestAdoptionFee DESC LIMIT 1";
            
            //query 4. 
            // create a view that displays information about employees who are 
            // caretakers and their assigned adoption centers
            String createCaretakerViewQuery = "CREATE VIEW CaretakerCenters AS " +
            	    "SELECT Employee.FirstName, Employee.LastName, AdoptionCenter.Name " +
            	    "FROM Employee " +
            	    "JOIN AdoptionCenter ON Employee.CenterID = AdoptionCenter.CenterID " +
            	    "WHERE Employee.Position = 'Caretaker'";
            	Statement createViewStmt = conn.createStatement();
            	createViewStmt.executeUpdate(createCaretakerViewQuery);
            	System.out.println("CaretakerCenters view created successfully.");


            ResultSet rs1 = stmt.executeQuery(totalCostQuery);
            System.out.println("==== Total Cost of All Transactions ====");
            while (rs1.next()) {
                double totalCost = rs1.getDouble("TotalCostOfTransactions");
                System.out.println("Total Cost of Transactions: " + totalCost);
            }

            ResultSet rs2 = stmt.executeQuery(dogAdoptersQuery);
            System.out.println("==== Adopters who Adopted Dogs ====");
            while (rs2.next()) {
                String firstName = rs2.getString("FirstName");
                String lastName = rs2.getString("LastName");

                System.out.println("AdopterFirstName: " + firstName);
                System.out.println("AdopterLastName: " + lastName);
            }

            ResultSet rs3 = stmt.executeQuery(highestAdoptionFeeQuery);
            System.out.println("==== Pet with the Highest Adoption Fee ====");
            while (rs3.next()) {
                int petID = rs3.getInt("PetID");
                double highestAdoptionFee = rs3.getDouble("HighestAdoptionFee");
                String name = rs3.getString("Name");
                String species = rs3.getString("Species");

                System.out.println("PetID: " + petID);
                System.out.println("Name: " + name);
                System.out.println("Species: " + species);
                System.out.println("HighestAdoptionFee: " + highestAdoptionFee);
            }
            
            String selectFromViewQuery = "SELECT * FROM CaretakerCenters";
        
            ResultSet rs4 = stmt.executeQuery(selectFromViewQuery);
            System.out.println("==== Caretakers and Assigned Centers ====");
            while (rs4.next()) {
                String firstName = rs4.getString("FirstName");
                String lastName = rs4.getString("LastName");
                String Name = rs4.getString("Name");

                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Center Name: " + Name);
            }

            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
