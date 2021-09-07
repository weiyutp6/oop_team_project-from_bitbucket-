package stockDB;

import java.sql.*;
import java.util.ArrayList;

public class Stock {
    private String stock_code;
    private String stock_name;
    private String industry;
    private double current_price;
    private double rise_fall;
    private double rise_fall_ratio;
    private int accum_vol;
    private int current_vol;
    private double open;
    private double close;
    private double limit_up;
    private double limit_down;
    private double high;
    private double low;
    private String buy_price;
    private String sale_price;
    private int buy_vol;
    private int sale_vol;
    private String latest_time;
    private String latest_date;

    static final String connectionUrl =
            "jdbc:sqlserver://127.0.0.1\\SQLEXPRESS;"
                    + "database=stockAPP;"
                    + "user=sa;"
                    + "password=b075050;";

    public Stock() {

    }

    public Stock(String stock_code) {
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT stock_code, stock_name, industry, current_price, rise_fall, rise_fall_ratio, accum_vol, current_vol, [open], [close], " +
                    "limit_up, limit_down, high, low, buy_price, sale_price, buy_vol, sale_vol, latest_time, latest_date from stock " +
                    "where stock_code='" + stock_code + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                this.stock_code = stock_code;
                this.stock_name = resultSet.getString(2);
                this.industry = resultSet.getString(3);
                this.current_price = resultSet.getDouble(4);
                this.rise_fall = resultSet.getInt(5);
                this.rise_fall_ratio = resultSet.getDouble(6);
                this.accum_vol = resultSet.getInt(7);
                this.current_vol = resultSet.getInt(8);
                this.open = resultSet.getInt(9);
                this.close = resultSet.getInt(10);
                this.limit_up = resultSet.getDouble(11);
                this.limit_down = resultSet.getDouble(12);
                this.high = resultSet.getDouble(13);
                this.low = resultSet.getDouble(14);
                this.buy_price = resultSet.getString(15);
                this.sale_price = resultSet.getString(16);
                this.buy_vol = resultSet.getInt(17);
                this.sale_vol = resultSet.getInt(18);
                this.latest_time = resultSet.getString(19);
                this.latest_date = resultSet.getString(20);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Stock(String stock_code, double current_price, double rise_fall, double rise_fall_ratio, int accum_vol, int current_vol, double open, double close, double limit_up, double limit_down, double high, double low, String buy_price, String sale_price, int buy_vol, int sale_vol, String latest_time, String latest_date) {
        this.stock_code = stock_code;
        this.current_price = current_price;
        this.rise_fall = rise_fall;
        this.rise_fall_ratio = rise_fall_ratio;
        this.accum_vol = accum_vol;
        this.current_vol = current_vol;
        this.open = open;
        this.close = close;
        this.limit_up = limit_up;
        this.limit_down = limit_down;
        this.high = high;
        this.low = low;
        this.buy_price = buy_price;
        this.sale_price = sale_price;
        this.buy_vol = buy_vol;
        this.sale_vol = sale_vol;
        this.latest_time = latest_time;
        this.latest_date = latest_date;
    }

    public static Stock searchStockByStockCode(String stock_code) {
        return new Stock(stock_code);
    }

    public static String[] searchStockCodeByStockName(String stock_name) {
        ResultSet resultSet = null;

        ArrayList<String> stockCode = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT stock_code from stock " +
                    "where stock_name like '%" + stock_name + "%';";
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

    public static String[][] StockCodeOfIndustry(String industry) {
        ResultSet resultSet = null;
        int size = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT COUNT(stock_code) from stock " +
                    "where industry='" + industry + "';";
            resultSet = statement.executeQuery(selectSql);


            // Store results from select statement

            while (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet2 = null;
        String[][] stocks = new String[size][2];
        int i = 0;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT stock_code, stock_name from stock " +
                    "where industry='" + industry + "';";
            resultSet2 = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet2.next()) {
                stocks[i][0] = resultSet2.getString(1);
                stocks[i][1] = resultSet2.getString(2);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocks;
    }

    public void crawlerToDB() {
        String updateSql = "UPDATE stock " +
                "SET current_price='" + this.current_price + "', " + "rise_fall='" + this.rise_fall + "', " + "rise_fall_ratio='" + this.rise_fall_ratio + "', " + "accum_vol='" + this.accum_vol +
                "', " + "current_vol='" + this.current_vol + "', " + "[open]='" + this.open + "', " + "[close]='" + this.close + "', " + "limit_up='" + this.limit_up + "', " + "limit_down='" + this.limit_down +
                "', " + "high='" + this.high + "', " + "low='" + this.low + "', " + "buy_price='" + this.buy_price + "', " + "sale_price='" + this.sale_price + "', " + "buy_vol='" + this.buy_vol +
                "', " + "sale_vol='" + this.sale_vol + "', " + "latest_time='" + this.latest_time + "', " + "latest_date='" + this.latest_date + "' " +
                "where stock_code='" + this.stock_code + "';";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsUpdateProduct.execute();

            System.out.println("Successfully updated the data of " + stock_code + " " + searchStockByStockCode(stock_code).getStock_name() + "!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
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

    public int getCurrent_vol() {
        return current_vol;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public double getLimit_up() {
        return limit_up;
    }

    public double getLimit_down() {
        return limit_down;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public String getBuy_price() {
        return buy_price;
    }

    public String getSale_price() {
        return sale_price;
    }

    public int getBuy_vol() {
        return buy_vol;
    }

    public int getSale_vol() {
        return sale_vol;
    }

    public String getLatest_time() {
        return latest_time;
    }

    public String getLatest_date() {
        return latest_date;
    }
}
