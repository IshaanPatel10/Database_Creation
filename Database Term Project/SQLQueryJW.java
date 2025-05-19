package termproject;
import java.sql.*;

public class SQLQueryJW {

	public static void main(String[] args) {
		Connection conn;

		try {
			// 1. Driver loading and DB connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/termproject1", "root", "CSCE460");
			System.out.println("Successful DB Connection");

			// 2. Write SQL query
			String sql1 = "SELECT Atr.TransactionID, E.EmployeeID, E.LastName, E.FirstName " +
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

			// 3. Create the statement object to execute SQL query
			Statement stmt = conn.createStatement();

			// 4. Execute SQL query using the execute method
			ResultSet rs1 = stmt.executeQuery(sql1);

			System.out.println("===Question 1 Output===");
			while (rs1.next()) {
				String TransactionID = rs1.getString("TransactionID");
				String EmployeeID = rs1.getString("EmployeeID");
				String LastName = rs1.getString("LastName");
				String FirstName = rs1.getString("FirstName");

				System.out.println("TransactionID: " + TransactionID + "\n" + "EmployeeID: " + EmployeeID + "\n" + "LastName: " +
				LastName + "\n" + "FirstName: " + FirstName);
			}

			System.out.println("===Question 2 Output===");
			stmt.executeUpdate(discountstoredProcedureSql);
			System.out.println("Stored procedure created successfully.");
			
			
			System.out.println("===Question 3 Output===");
			stmt.executeUpdate(employeeviewSql);
			System.out.println("View created successfully.");
			

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
