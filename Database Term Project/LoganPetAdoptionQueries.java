import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class LoganPetAdoptionQueries {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn;
		try
		{
		//1. Driver loading and DB connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_adoption", "root", "password here");
			 System.out.println("DB Connection Success!");
		//2. Write SQL query
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

		 // Query 3: Average age of a species.
		String sql3= "SELECT Species, AVG(Age) AS AverageAge "
                + "FROM Pet "
                + "JOIN Adoption_Transaction ON Pet.PetID = Adoption_Transaction.PetID "
                + "GROUP BY Species;";
					
		
		//3. Create the statement object to execute SQL query
		Statement stmt = conn.createStatement();
		
		//4
		ResultSet rs = stmt.executeQuery(sql1);
		System.out.println("====Question #1 Output====");
		
		
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
		
		
		System.out.println("====Question #2 Output====");
		ResultSet rs2 = stmt.executeQuery(sql2);
		while(rs2.next())
		{
			String FirstName = rs.getString("FirstName");
			String LastName = rs.getString("LastName");
			int TotalAdoptions = rs.getInt("TotalAdoptions");
			
			System.out.println("EmployeeFirstName: "+ FirstName + "\n");
			System.out.println("EmployeeLastName: "+ LastName + "\n");
			System.out.println("TotalAdoptions: "+ TotalAdoptions + "\n");
			
		}
		
		System.out.println("====Question #3 Output====");
		ResultSet rs3 = stmt.executeQuery(sql3);
		while(rs3.next())
		{
			String Species = rs3.getString("Species");
			String AverageAge = rs3.getString("AverageAge");
			
			System.out.println("Species: "+ Species + "\n");
			System.out.println("AverageAge: "+ AverageAge + "\n");
			
			
		}
// close
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
					
		 }
		catch(Exception e)
		{
			System.out.println("Error: " + e);
		}
		
	}

}
