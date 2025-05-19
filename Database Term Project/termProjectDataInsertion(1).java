package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class termProjectDataInsertion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn;
		
		try
		{
			//1. Driver Loading and DB Connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/termproject", "root", "csce460");
			System.out.println("Successful DB Connection");
			
			//2. Write SQL Query
			
			
			String insert1 = "INSERT INTO adopter VALUES "
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
			
		
			
			//Create the statement object to execute SQL Query
			Statement stmt = conn.createStatement();
			
			//4. Execute SQL query using execute(String sql) method of the statement object
			
		
			
			boolean result1 = stmt.execute(insert1);
			System.out.println("Inserting data is successful!");
			
			//5. close
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
			}
			catch(Exception e)
			{
			System.out.println("Error: "+e);
			}
	}
}

			
					
							
	
