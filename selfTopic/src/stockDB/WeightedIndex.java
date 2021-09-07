package stockDB;

import java.sql.*;

public class WeightedIndex {
    private String[] time;
    private String currentTime;
    private double[] index;
    private double currentIndex;
    private double rise_fall;
    private double rise_fall_ratio;
    private int[] volume;
    private int currentVolume;
    private double open;
    private double close;

    public WeightedIndex() {
        ResultSet resultSet = null;

        int count = 0;

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT count(time) from weighted_index;";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int i = 0;

        ResultSet resultSet1 = null;

        time = new String[count];
        index = new double[count];
        volume = new int[count];

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT [time], [index], volume from weighted_index;";
            resultSet1 = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet1.next()) {
                time[i] = resultSet1.getString(1);
                index[i] = resultSet1.getDouble(2);
                volume[i] = resultSet1.getInt(3);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WeightedIndex(String useless) {
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from weighted_index " +
                    "where [time]=(select max([time]) from weighted_index)";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                currentTime = resultSet.getString(1);
                currentIndex = resultSet.getDouble(2);
                rise_fall = resultSet.getDouble(3);
                rise_fall_ratio = resultSet.getDouble(4);
                currentVolume = resultSet.getInt(5);
                open = resultSet.getDouble(6);
                close = resultSet.getDouble(7);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCurrentData(String time, double index, double rise_fall, double rise_fall_ratio, int volume, double open, double close) {
        String insertSql = "INSERT INTO weighted_index ([time], [index], rise_fall, rise_fall_ratio, volume, [open], [close]) " +
                "VALUES ('" + time + "', " + "'" + index + "', " + "'" + rise_fall + "', " + "'" + rise_fall_ratio + "', " + "'" + volume + "', " + "'" + open + "', " + "'" +close + "');";

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsertProduct.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initializeWeightedIndex() {
        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             Statement statement  = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE weighted_index");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] getTime() {
        return time;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public double[] getIndex() {
        return index;
    }

    public double getCurrentIndex() {
        return currentIndex;
    }

    public double getRise_fall() {
        return rise_fall;
    }

    public double getRise_fall_ratio() {
        return rise_fall_ratio;
    }

    public int[] getVolume() {
        return volume;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }
}
