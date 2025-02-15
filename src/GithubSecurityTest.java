import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class GithubSecurityTest {
        public static void main(String[] args) {
            // Hardcoded credentials (Secret Scanning Test)
            String dbUrl = "jdbc:mysql://localhost:3306/testdb";
            String dbUser = "root";
            String dbPassword = "password123"; // Hardcoded password

            try {
                Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                Statement stmt = conn.createStatement();

                // SQL Injection Vulnerability (Code Scanning Test)
                String userInput = "admin' OR '1'='1"; // Simulating a malicious input
                String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    System.out.println("User: " + rs.getString("username"));
                }

                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Command Injection Vulnerability
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter a command: ");
                String command = reader.readLine();

                // Vulnerable execution of user input
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = output.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


