package term;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataInsertionForEmployeeTable {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // 1. Driver Loading and DB Connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/termproject", "root", "CSCE460");
            System.out.println("DB Connection Success!");

            // 2. Write SQL queries to insert data into Employee table
            String sql1 = "INSERT INTO Employee (LastName, FirstName, Position, CellPhone, Email) " +
                    "VALUES " +
                    "('Doe', 'John', 'Manager', '123-456-7890', 'john.doe@gmail.com')," +
                    "('Smith', 'Jane', 'Caretaker', '987-654-3210', 'jane.smith@gmail.com')," +
                    "('Johnson', 'Michael', 'Assistant', '555-555-5555', 'michael.johnson@gmail.com')," +
                    "('Williams', 'Emily', 'Trainer', '777-777-7777', 'emily.williams@gmail.com')," +
                    "('Brown', 'David', 'Coordinator', '333-333-3333', 'david.brown@gmail.com')," +
                    "('Lee', 'Sarah', 'Veterinarian', '444-444-4444', 'sarah.lee@gmail.com')," +
                    "('Garcia', 'Robert', 'Volunteer', '666-666-6666', 'robert.garcia@gmail.com')," +
                    "('Martinez', 'Olivia', 'Caretaker', '222-222-2222', 'olivia.martinez@gmail.com')," +
                    "('Wilson', 'William', 'Assistant', '888-888-8888', 'william.wilson@gmail.com')," +
                    "('Anderson', 'Ava', 'Trainer', '999-999-9999', 'ava.anderson@gmail.com')," +
                    "('Lopez', 'Ethan', 'Coordinator', '777-888-9999', 'ethan.lopez@gmail.com')," +
                    "('Thomas', 'Sophia', 'Veterinarian', '111-222-3333', 'sophia.thomas@gmail.com')," +
                    "('Hall', 'Daniel', 'Volunteer', '444-555-6666', 'daniel.hall@gmail.com')," +
                    "('Clark', 'Grace', 'Caretaker', '777-999-1111', 'grace.clark@gmail.com')," +
                    "('Rodriguez', 'Liam', 'Assistant', '888-777-5555', 'liam.rodriguez@gmail.com')";

            // 3. Create the statement object to execute SQL queries
            stmt = conn.createStatement();

            // 4. Execute SQL insert query
            boolean result1 = stmt.execute(sql1);
            System.out.println("Result: " + result1);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            // 5. Close resources in finally block
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources: " + e);
            }
        }
    }
}
