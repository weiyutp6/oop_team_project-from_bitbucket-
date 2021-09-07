package stockDB;

import java.sql.*;

public class User {
    private String username;
    private int principal;
    private int total_refund;
    private int total_reward;
    private double total_ROI;

    public User() {

    }

    public User(String username) {
        this.username = username;

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT total_refund from user_data " +
                    "where username='" + username + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                this.total_refund = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet2 = null;


        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT wl.lot, wl.buy_price, s.current_price from watchlist as wl " +
                    "right join stock as s on wl.stock_code=s.stock_code " +
                    "where username='" + username + "';";
            resultSet2 = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet2.next()) {
                this.principal += resultSet2.getInt(1)*resultSet2.getInt(2)*1000;
                this.total_reward += (resultSet2.getInt(3) - resultSet2.getInt(2))*resultSet2.getInt(1)*1000;
                this.total_ROI = (double)total_reward/(double)principal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newUser(String username) {
        String insertSql = "INSERT INTO user_data (username, principal, total_refund) " +
                "VALUES ('" + username + "', " + "'" + 0 + "', " + "'" + 0 + "');";

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsertProduct.execute();

            System.out.println("Successfully added one user, username=" + username + " !");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public int getPrincipal() {
        return principal;
    }

    public int getTotal_refund() {
        return total_refund;
    }

    public int getTotal_reward() {
        return total_reward;
    }

    public double getTotal_ROI() {
        return total_ROI;
    }
}
