package stockDB;

import java.sql.*;
import java.util.ArrayList;

public class Watchlist {
    private String username;
    private int list_no;
    private String stock_code;
    private String stock_name;
    private String industry;
    private int lot;
    private double buy_price;
    private double current_price;
    private double rise_fall;
    private double rise_fall_ratio;
    private int accum_vol;
    private int reward;
    private double ROI;
    private String buy_datetime;
    private static int refund;

    public Watchlist() {

    }

    private Watchlist(String username, int list_no) {
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT wl.username, wl.list_no, wl.stock_code, wl.stock_name, wl.industry, wl.lot, wl.buy_price, " +
                    "s.current_price, s.rise_fall, s.rise_fall_ratio, s.accum_vol, wl.buy_datetime from watchlist as wl " +
                    "right join stock as s on wl.stock_code=s.stock_code " +
                    "where wl.username='" + username + "' and wl.list_no=" + list_no + ";";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                this.username = username;
                this.list_no = list_no;
                this.stock_code = resultSet.getString(3);
                this.stock_name = resultSet.getString(4);
                this.industry = resultSet.getString(5);
                this.lot = resultSet.getInt(6);
                this.buy_price = resultSet.getDouble(7);
                this.current_price = resultSet.getDouble(8);
                this.rise_fall = resultSet.getDouble(9);
                this.rise_fall_ratio = resultSet.getDouble(10);
                this.accum_vol = resultSet.getInt(11);
                this.reward = (int)((this.current_price - this.buy_price)*this.lot*1000);
                this.ROI = this.reward/(this.buy_price*this.lot*1000);
                this.buy_datetime = resultSet.getString(12);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Watchlist(String username, String stock_code, int lot, String buy_datetime) {
        this.username = username;
        this.stock_code = stock_code;
        this.lot = lot;
        this.buy_datetime = buy_datetime;
    }

    public static String[] searchStockCodeOfWatchList(String username) {
        ResultSet resultSet = null;

        ArrayList<String> stockCode = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT stock_code from watchlist " +
                    "where username='" + username + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                stockCode.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockCode.toArray(String[]::new);
    }

    public static int numberOfWatchList(String username) {
        ResultSet resultSet = null;

        int noOfWatchList = 0;

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT MAX(list_no) from watchlist " +
                    "where username='" + username + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                noOfWatchList = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noOfWatchList;
    }

    public static Watchlist searchStockOfWatchList(String username, int list_no) {
        return new Watchlist(username, list_no);
    }

    public static Watchlist[] getAllDetailsInWatchList(String username) {
        ResultSet resultSet = null;

        ArrayList<Watchlist> watchlist = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT list_no from watchlist " +
                    "where username='" + username + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            Watchlist stock = null;
            int list_no = 0;

            while (resultSet.next()) {
                list_no = resultSet.getInt(1);
                stock = new Watchlist(username, list_no);
                watchlist.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return watchlist.toArray(Watchlist[]::new);
    }

    public void addToWatchList() {
        Stock stock = new Stock(stock_code);
        this.list_no = numberOfWatchList(username) + 1;
        this.stock_name = stock.getStock_name();
        this.industry = stock.getIndustry();
        this.buy_price = stock.getCurrent_price();
        this.current_price = stock.getCurrent_price();
        this.accum_vol = stock.getAccum_vol();

        String insertSql = "INSERT INTO watchlist (username, list_no, stock_code, stock_name, industry, lot, buy_price, buy_datetime) " +
                "VALUES ('" + this.username + "', " + "'" + this.list_no + "', " + "'" + this.stock_code + "', " + "'" + this.stock_name + "', " + "'" + this.industry + "', " + "'" + this.lot + "', " + "'" + this.buy_price + "', " + "'" + buy_datetime + "');";

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsertProduct.execute();

            System.out.println("Successfully added " + stock_code + " " + stock_name + " " + lot + " lots to watchlist!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String updateSql = "UPDATE user_data " +
                "SET principal=principal+" + current_price*lot*1000 +
                "where username='" + username + "';";

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsUpdateProduct.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteWatchList(String username, int list_no) {

        ResultSet resultSet = null;

        int lot = 0;
        double buy_price = 0.0;

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT lot, buy_price from watchlist " +
                    "where username='" + username + "' and list_no=" + list_no + ";";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                lot = resultSet.getInt(1);
                buy_price = resultSet.getDouble(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String deleteSql = "DELETE FROM watchlist " +
                "where username='" + username + "' and list_no=" + list_no + ";";

        ResultSet resultSet1 = null;

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             PreparedStatement prepsDeleteProduct = connection.prepareStatement(deleteSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsDeleteProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet1 = prepsDeleteProduct.getGeneratedKeys();

            // Print the order_no of the deleted row.
            while (resultSet1.next()) {
                System.out.println("Successfully deleted 1 watchlist: username=" + username + " and list_no=" + list_no + " !");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String updateSql = "UPDATE user_data " +
                "SET principal=principal-" + buy_price*lot*1000 +
                " where username='" + username + "';";

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsUpdateProduct.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void sellStockInWatchList(String username, int list_no, int lotToBeSold) {
        Watchlist stock = searchStockOfWatchList(username, list_no);

        refund = (int)((stock.current_price - stock.buy_price)*lotToBeSold*1000);

        String updateSql = "UPDATE watchlist " +
                "SET lot=lot-" + lotToBeSold +
                "where username='" + username + "' and list_no=" + list_no +
                " UPDATE user_data " +
                "SET principal=principal-" + stock.buy_price*lotToBeSold*1000 + ", total_refund=total_refund+" + refund +
                " where username='" + username + "';";

        try (Connection connection = DriverManager.getConnection(Stock.connectionUrl);
             PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsUpdateProduct.execute();

            System.out.println("Successfully sold " + stock.stock_code + " " + stock.stock_name + " " + lotToBeSold + " lots where list_no=" + list_no + " !");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public int getList_no() {
        return list_no;
    }

    public String getStock_code() {
        return stock_code;
    }

    public String getStock_name() {
        return stock_name;
    }

    public String getIndustry() {
        return industry;
    }

    public int getLot() {
        return lot;
    }

    public double getBuy_price() {
        return buy_price;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public double getRise_fall() {
        return rise_fall;
    }

    public double getRise_fall_ratio() {
        return rise_fall_ratio;
    }

    public int getAccum_vol() {
        return accum_vol;
    }

    public int getReward() {
        return reward;
    }

    public double getROI() {
        return ROI;
    }

    public String getBuy_datetime() {
        return buy_datetime;
    }

    public static int getRefund() {
        return refund;
    }
}
