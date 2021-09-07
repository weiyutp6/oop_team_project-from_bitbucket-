import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabaseConnection {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://127.0.0.1\\SQLEXPRESS;"
                        + "database=Travel;"
                        + "user=sa;"
                        + "password=b075050;";
//                        + "encrypt=true;"
//                        + "trustServerCertificate=false;"
//                        + "loginTimeout=30;";
                /*
                "jdbc:sqlserver://LAPTOP-IBSJHL86\\SQLEXPRESS.database.windows.net:1433;"
                        + "database=Travel;"
                        + "user=sa;"
                        + "password=b075050;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";
*/

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
//            String selectSql = "SELECT TOP 100 Title, travel_code, travel_code_name from travel_code";
            String selectSql = "SELECT TOP 100 travel_code, travel_code_name from travel_code";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}