package termproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataInsertion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn;
		
		try {
			
		// 1. Driver Loading and DB Connection!
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/termproject", "root", "CSCE460");
			 System.out.println("DB Connection Success!");
			
		// 2. Write SQL queries to create a DB Table!
			 String sql1 = "INSERT INTO adopter VALUES "
						+ "(1001, 'Smith', 'Sam', '206-254-1234', '123 Main Street, Pleasantville, CA 98765', 'samsmith@gmail.com'), "
						+ "(1002, 'Evanston', 'John', '206-254-2345', '456 Elm Avenue, Harmony City, TX 54321', 'johnevanston@gmail.com'), "
						+ "(1003, 'Murray', 'Dale', '206-254-3456', '789 Oak Lane, Serenity Springs, NY 12345', 'dalemurray@gmail.com'), "
						+ "(1004, 'Murphy', 'Jerry', '206-545-8765', '101 Maple Court, Tranquil Meadows, FL 67890', 'jerrymurphy@gmail.com'), "
						+ "(1005, 'Allanach', 'Jerry', '206-254-4567', '234 Pine Road, Blissful Heights, AZ 45678', 'jerryallanach@gmail.com'),"
						+ "(1006, 'Pitt', 'Brad', '206-546-1234', '567 Cedar Street, Dreamland Falls, IL 23456', 'bradpitt@gmail.com'),"
						+ "(1007, 'Fontaine', 'Joan', '206-546-2345', '890 Birch Lane, Whispering Pines, OH 78901', 'johnfontaine@gmail.com'),"
						+ "(1008, 'Prefontaine', 'Steve', '206-546-3456', '112 Willow Avenue, Rainbow Valley, WA 34567', 'steveprefontaine@gmail.com'),"
						+ "(1009, 'Jacobs', 'Noah', '206-546-4567', '345 Redwood Drive, Jubilation Junction, GA 89012', 'noahjacobs@gmail.com'),"
						+ "(1010, 'Kap', 'Colin', '206-546-5678', '678 Juniper Lane, Radiant Ridge, MI 12345', 'colinkap@gmail.com'),"
						+ "(1011, 'Ferrari', 'Enzo', '206-546-6789', '901 Spruce Street, Enchanted Gardens, PA 67890', 'enzoferrari@gmail.com'),"
						+ "(1012, 'Neutron', 'Jimmy', '206-546-7890', '234 Laurel Road, Mystic Hills, NC 23456', 'jimmyneutron@gmail.com')";
			 
			 String sql2 = "INSERT INTO pet "
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
			 
			 String sql3 = "INSERT INTO AdoptionCenter " +
	                    "VALUES " +
	                    "(1, 'Paws and Claws', 'New York', '123-456-7890')," +
	                    "(2, 'Furry Friends', 'Los Angeles', '987-654-3210')," +
	                    "(3, 'Happy Tails', 'Chicago', '555-555-5555')," +
	                    "(4, 'Pets Haven', 'Houston', '777-777-7777')," +
	                    "(5, 'Animal Allies', 'San Francisco', '333-333-3333')," +
	                    "(6, 'Pet Paradise', 'Miami', '444-444-4444')," +
	                    "(7, 'Companion Care', 'Denver', '666-666-6666')," +
	                    "(8, 'Rescue Ranch', 'Seattle', '222-222-2222')," +
	                    "(9, 'Forever Furry', 'Boston', '888-888-8888')," +
	                    "(10, 'Pawsitive Vibes', 'Dallas', '999-999-9999')," +
	                    "(11, 'Cuddles and Co.', 'Austin', '111-111-1111')," +
	                    "(12, 'Whiskers and Wags', 'Portland', '222-222-2222')," +
	                    "(13, 'Furry Haven', 'Phoenix', '333-333-3333')," +
	                    "(14, 'Pet Harmony', 'Las Vegas', '444-444-4444')," +
	                    "(15, 'Tails of Joy', 'Atlanta', '555-555-5555')";
			 
			 String sql4 = "INSERT INTO Employee " +
	                    "VALUES " +
	                    "(26, 'Doe', 'John', 'Manager', '123-456-7890', 'john.doe@gmail.com', 1)," +
	                    "(8, 'Smith', 'Jane', 'Caretaker', '987-654-3210', 'jane.smith@gmail.com', 11)," +
	                    "(45, 'Johnson', 'Michael', 'Assistant', '555-555-5555', 'michael.johnson@gmail.com', 7)," +
	                    "(19, 'Williams', 'Emily', 'Trainer', '777-777-7777', 'emily.williams@gmail.com', 5)," +
	                    "(37, 'Brown', 'David', 'Coordinator', '333-333-3333', 'david.brown@gmail.com', 12)," +
	                    "(12, 'Lee', 'Sarah', 'Veterinarian', '444-444-4444', 'sarah.lee@gmail.com', 15)," +
	                    "(3, 'Garcia', 'Robert', 'Volunteer', '666-666-6666', 'robert.garcia@gmail.com', 2)," +
	                    "(31, 'Martinez', 'Olivia', 'Caretaker', '222-222-2222', 'olivia.martinez@gmail.com', 14)," +
	                    "(22, 'Wilson', 'William', 'Assistant', '888-888-8888', 'william.wilson@gmail.com', 8)," +
	                    "(49, 'Anderson', 'Ava', 'Trainer', '999-999-9999', 'ava.anderson@gmail.com', 9)," +
	                    "(6, 'Lopez', 'Ethan', 'Coordinator', '777-888-9999', 'ethan.lopez@gmail.com', 10)," +
	                    "(42, 'Thomas', 'Sophia', 'Veterinarian', '111-222-3333', 'sophia.thomas@gmail.com', 6)," +
	                    "(15, 'Hall', 'Daniel', 'Volunteer', '444-555-6666', 'daniel.hall@gmail.com', 3)," +
	                    "(29, 'Clark', 'Grace', 'Caretaker', '777-999-1111', 'grace.clark@gmail.com', 4)," +
	                    "(5, 'Rodriguez', 'Liam', 'Assistant', '888-777-5555', 'liam.rodriguez@gmail.com', 13)";
			 
			 String sql5 = "INSERT INTO adoption_transaction "
			 		+ "VALUES "
						+ "(11, '2023-04-15', 275.00, 100.00, 5.00, 370.00, 1001, 01, 45, 7), "
						+ "(22, '2023-04-17', 50.00, 35.00, 0.00, 85.00, 1002, 06, 26, 1), "
						+ "(33, '2023-04-17', 100.00, 40.00, 8.00, 132.00, 1003, 04, 5, 13), "
						+ "(44, '2023-04-17', 150.00, 25.00, 10.00, 165.00, 1004, 03, 37, 12), "
						+ "(55, '2023-04-23', 50.00, 75.00, 0.00, 125.00, 1005, 11, 26, 1), "
						+ "(66, '2023-04-30', 225.00, 30.00, 0.00, 255.00, 1006, 02, 49, 9), "
						+ "(77, '2023-05-01', 20.00, 25.00, 0.00, 45.00, 1007, 10, 42, 6),"
						+ "(88, '2023-05-01', 15.00, 60.00, 0.00, 75.00, 1008, 08, 26, 1),"
						+ "(99, '2023-05-04', 5.00, 10.00, 7.00, 8.00, 1009, 12, 31, 8),"
						+ "(110, '2023-05-15', 35.00, 20.00, 15.00, 40.00, 1010, 07, 15, 3),"
						+ "(111, '2023-05-25', 100.00, 155.00, 0.00, 255.00, 1011, 05, 29, 4),"
						+ "(112, '2023-06-07', 15.00, 20.00, 25.00, 10.00, 1012, 09, 19, 5)";

			 // create the statement object to execute sql queries
			 Statement smt = conn.createStatement();
				 
			// Execute SQL query using the execute method of the statement object
			boolean result1 = smt.execute(sql1)	 ;
			boolean result2 = smt.execute(sql2)	 ;
			boolean result3 = smt.execute(sql3)	 ;
			boolean result4 = smt.execute(sql4)	 ;
			boolean result5 = smt.execute(sql5)	 ;
			
			System.out.print("Result: " + result1);
			System.out.print("Result: " + result2);
			System.out.print("Result: " + result3);
			System.out.print("Result: " + result4);
			System.out.print("Result: " + result5);
		
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

