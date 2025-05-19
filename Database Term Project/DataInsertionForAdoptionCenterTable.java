package term;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataInsertionForAdoptionCenterTable {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/termproject", "root", "surjona98!");
            System.out.println("DB Connection Success!");

            String sql = "INSERT INTO AdoptionCenter (Name, Location, Position, CellPhone) " +
                    "VALUES " +
                    "('Paws and Claws', 'New York', 'Manager', '123-456-7890')," +
                    "('Furry Friends', 'Los Angeles', 'Coordinator', '987-654-3210')," +
                    "('Happy Tails', 'Chicago', 'Volunteer', '555-555-5555')," +
                    "('Pets Haven', 'Houston', 'Caretaker', '777-777-7777')," +
                    "('Animal Allies', 'San Francisco', 'Trainer', '333-333-3333')," +
                    "('Pet Paradise', 'Miami', 'Veterinarian', '444-444-4444')," +
                    "('Companion Care', 'Denver', 'Assistant', '666-666-6666')," +
                    "('Rescue Ranch', 'Seattle', 'Caretaker', '222-222-2222')," +
                    "('Forever Furry', 'Boston', 'Trainer', '888-888-8888')," +
                    "('Pawsitive Vibes', 'Dallas', 'Volunteer', '999-999-9999')," +
                    "('Cuddles and Co.', 'Austin', 'Manager', '111-111-1111')," +
                    "('Whiskers and Wags', 'Portland', 'Coordinator', '222-222-2222')," +
                    "('Furry Haven', 'Phoenix', 'Assistant', '333-333-3333')," +
                    "('Pet Harmony', 'Las Vegas', 'Trainer', '444-444-4444')," +
                    "('Tails of Joy', 'Atlanta', 'Veterinarian', '555-555-5555')";

            stmt = conn.createStatement();

            boolean result = stmt.execute(sql);
            System.out.println("Result: " + result);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Error in closing resources: " + e);
            }
        }
    }
}
