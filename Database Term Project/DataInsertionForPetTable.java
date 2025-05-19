import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PetAdoptionDataInsertion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Connection conn;
		
		try {
			
		// 1. Driver Loading and DB Connection!
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_adoption", "root", "password");
			 System.out.println("DB Connection Success!");
			
		// 2. Write SQL queries to create a DB Table!
			 String sql1 = "INSERT INTO pet "
					 + "VALUES(01, 'Wrigley', 'Dog' , 'Golden Retriever','6 Months',275),"
					 + "(02, 'Rizzo', 'Dog' , 'Dachshund ','5 Years',225),"
					 + "(03, 'Payton', 'Dog' , 'Bernedoodle','7 Years',150),"
					 + "(04, 'Madison', 'Cat' , 'Siamese','5 Months',100),"
					 + "(05, 'Harry', 'Cat' , 'Persian','3 Year',100),"
					 + "(06, 'Sosa', 'Cat' , 'American Shorthair','8 Years',50),"
					 + "(07, 'Jordan', 'Bird' , 'Grey Parrot','6 Months',35),"
					 + "(08, 'Pippen', 'Bird' , 'Canary','1 Year',15),"
					 + "(09, 'Rose', 'Bird' , 'Cockatoo','2 Years',15),"
					 + "(10, 'Hester', 'Rabbit' , 'Holland Lop','9 Months',20),"
					 + "(11, 'Kane', 'Chinchilla' , 'White Mosaic','1 Year',50),"
					 + "(12, 'Ernie', 'Hamster' , 'Winter White Russian Dwarf ','3 Months',5)";
			 
			 
			 // create the statement object to execute sql queries
			 Statement smt = conn.createStatement();
				 
			// Execute SQL query using the execute method of the statement object
			boolean result1 = smt.execute(sql1)	 ;
			System.out.print("Result: " + result1);
			
		// 3. Create the statement object to execute SQL queries.
			
			
		//4. Execute SQL Query using the execute method of the statement object
			
			
			System.out.println("Result: " + result1);
			
			
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
