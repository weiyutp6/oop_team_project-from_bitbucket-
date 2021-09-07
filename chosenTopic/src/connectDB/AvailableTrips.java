package connectDB;

import java.sql.*;
import java.util.ArrayList;

public class AvailableTrips {
    private int product_code;
    private int travel_code;
    private int title_code;
    private String title;
    private String product_key;
    private int price;
    private String start_date;
    private String end_date;
    private int lower_bound;
    private int upper_bound;
    private int remaining;

    public AvailableTrips() {

    }

    public AvailableTrips(int product_code) {
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT product_code, travel_code, title_code, title, product_key, price, start_date, end_date, lower_bound, upper_bound, remaining from trip_data " +
                    "where product_code='" + product_code + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                this.product_code = product_code;
                this.travel_code = resultSet.getInt(2);
                this.title_code = resultSet.getInt(3);
                this.title = resultSet.getString(4);
                this.product_key = resultSet.getString(5);
                this.price = resultSet.getInt(6);
                this.start_date = resultSet.getString(7);
                this.end_date = resultSet.getString(8);
                this.lower_bound = resultSet.getInt(9);
                this.upper_bound = resultSet.getInt(10);
                this.remaining = resultSet.getInt(11);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AvailableTrips(String product_key, String start_date) {
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT MIN(product_code), travel_code, title_code, title, product_key, price, start_date, end_date, lower_bound, upper_bound, remaining from trip_data " +
                    "where product_key='" + product_key + "' and start_date='" + start_date + "' group by travel_code, title_code, title, product_key, price, start_date, end_date, lower_bound, upper_bound, remaining;";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                this.product_code = resultSet.getInt(1);;
                this.travel_code = resultSet.getInt(2);
                this.title_code = resultSet.getInt(3);
                this.title = resultSet.getString(4);
                this.product_key = resultSet.getString(5);
                this.price = resultSet.getInt(6);
                this.start_date = resultSet.getString(7);
                this.end_date = resultSet.getString(8);
                this.lower_bound = resultSet.getInt(9);
                this.upper_bound = resultSet.getInt(10);
                this.remaining = resultSet.getInt(11);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String searchTitleByTitleCode(int title_code) {

        ResultSet resultSet = null;
        String title = "";

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT title from trip_data " +
                    "where title_code=" + title_code;
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                title = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return title;
    }

    public static String[] searchAvailableTitle(String keyWord, int start_date1, int start_date2) {
        String[] words = keyWord.split(" ");

        int size = 0;

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql;
            switch (words.length) {
                case 1:
                    selectSql = "SELECT COUNT(DISTINCT(title)) from trip_data " +
                            "where title LIKE '%" + words[0] + "%' and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
                case 2:
                    selectSql = "SELECT COUNT(DISTINCT(title)) from trip_data " +
                            "where (title LIKE '%" + words[0] + "%' or title like '%" + words[1] + "%') and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
                default:
                    selectSql = "SELECT COUNT(DISTINCT(title)) from trip_data " +
                            "where (title LIKE '%" + words[0] + "%' or title like '%" + words[1] + "%' or title like '%" + words[2] + "%') and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
            }

            // Store results from select statement

            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] availableTitle = new String[size];
        int i = 0;

        ResultSet resultSet2 = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql2;
            switch (words.length) {
                case 1:
                    selectSql2 = "SELECT DISTINCT(title) from trip_data " +
                            "where title LIKE '%" + words[0] + "%' and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
                case 2:
                    selectSql2 = "SELECT DISTINCT(title) from trip_data " +
                            "where (title LIKE '%" + words[0] + "%' or title like '%" + words[1] + "%') and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
                default:
                    selectSql2 = "SELECT DISTINCT(title) from trip_data " +
                            "where (title LIKE '%" + words[0] + "%' or title like '%" + words[1] + "%' or title like '%" + words[2] + "%') and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
            }

            // Store results from select statement

            resultSet2 = statement.executeQuery(selectSql2);

            while (resultSet2.next()) {
                availableTitle[i] = resultSet2.getString(1);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableTitle;
    }

    public static String[] searchRelatedTitle(String keyWord, int start_date1, int start_date2) {
        String[] words = keyWord.split(" ");
        ArrayList<Integer> travelCodes = new ArrayList<>();

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql;
            switch (words.length) {
                case 1:
                    selectSql = "SELECT travel_code from dbo.travel_code " +
                            "where travel_code_name LIKE '%" + words[0] + "%' or country_area LIKE '%" + words[0] + "%' or continent LIKE '%" + words[0] + "%';";
                    break;
                case 2:
                    selectSql = "SELECT travel_code from dbo.travel_code " +
                            "where travel_code_name LIKE '%" + words[0] + "%' or country_area LIKE '%" + words[0] + "%' or continent LIKE '%" + words[0] + "%' or travel_code_name LIKE '%" + words[1] + "%' or country_area LIKE '%" + words[1] + "%' or continent LIKE '%" + words[1] + "%';";
                    break;
                default:
                    selectSql = "SELECT travel_code from dbo.travel_code " +
                            "where travel_code_name LIKE '%" + words[0] + "%' or country_area LIKE '%" + words[0] + "%' or continent LIKE '%" + words[0] + "%' or travel_code_name LIKE '%" + words[1] + "%' or country_area LIKE '%" + words[1] + "%' or continent LIKE '%" + words[1] + "%' or travel_code_name LIKE '%" + words[2] + "%' or country_area LIKE '%" + words[2] + "%' or continent LIKE '%" + words[2] + "%';";
                    break;
            }

            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                travelCodes.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int size = 0;

        ResultSet resultSet2 = null;

        // Create and execute a SELECT SQL statement.
        StringBuilder selectSqlj;
        if(travelCodes.size() != 0) {
            selectSqlj = new StringBuilder("SELECT COUNT(DISTINCT(title)) from trip_data where (travel_code=" + travelCodes.get(0));
        }
        else {
            selectSqlj = new StringBuilder("SELECT COUNT(DISTINCT(title)) from trip_data where travel_code=-1");
        }

        for(int j = 1; j <travelCodes.size(); j++) {
            selectSqlj.append(" or travel_code=").append(travelCodes.get(j));
        }
        selectSqlj.append(") and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';");

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Store results from select statement

            resultSet2 = statement.executeQuery(selectSqlj.toString());

            while (resultSet2.next()) {
                size = resultSet2.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] relatedTitle = new String[size];
        int i = 0;

        ResultSet resultSet3 = null;

        // Create and execute a SELECT SQL statement.
        StringBuilder selectSql;
        if(travelCodes.size() != 0) {
            selectSql = new StringBuilder("SELECT DISTINCT(title) from trip_data where (travel_code=" + travelCodes.get(0));
        }
        else {
            selectSql = new StringBuilder("SELECT DISTINCT(title) from trip_data where travel_code=-1");
        }

        for(int j = 1; j <travelCodes.size(); j++) {
            selectSql.append(" or travel_code=").append(travelCodes.get(j));
        }
        selectSql.append(") and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';");

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Store results from select statement

            resultSet3 = statement.executeQuery(selectSql.toString());

            while (resultSet3.next()) {
                relatedTitle[i] = resultSet3.getString(1);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatedTitle;
    }

    public static Object[][] searchDetailByTitle(String title) {
        ResultSet resultSet = null;
        int size = 0;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT COUNT(product_code) from trip_data " +
                    "where title='" + title + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet2 = null;
        String[][] detail = new String[size][8];
        int i = 0;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT product_code, start_date, end_date, price, lower_bound, upper_bound, remaining from trip_data " +
                    "where title='" + title + "';";
            resultSet2 = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet2.next()) {
                detail[i][0] = resultSet2.getString(1);
                detail[i][1] = resultSet2.getString(2);
                detail[i][2] = resultSet2.getString(3);
                detail[i][3] = resultSet2.getString(4);
                detail[i][4] = resultSet2.getString(5);
                detail[i][5] = resultSet2.getString(6);
                detail[i][6] = resultSet2.getString(7);
                detail[i][7] = title;
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detail;
    }

    public static int[] availableTitleCode(String keyWord, int start_date1, int start_date2) {
        String[] words = keyWord.split(" ");

        int size = 0;

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql;
            switch (words.length) {
                case 1:
                    selectSql = "SELECT COUNT(DISTINCT title_code) from trip_data " +
                            "where title LIKE '%" + words[0] + "%' and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
                case 2:
                    selectSql = "SELECT COUNT(DISTINCT title_code) from trip_data " +
                            "where (title LIKE '%" + words[0] + "%' or title like '%" + words[1] + "%') and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
                default:
                    selectSql = "SELECT COUNT(DISTINCT title_code) from trip_data " +
                            "where (title LIKE '%" + words[0] + "%' or title like '%" + words[1] + "%' or title like '%" + words[2] + "%') and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
            }

            // Store results from select statement

            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int[] titleCodes = new int[size];
        int i = 0;

        ResultSet resultSet2 = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql2;
            switch (words.length) {
                case 1:
                    selectSql2 = "SELECT DISTINCT title_code from trip_data " +
                            "where title LIKE '%" + words[0] + "%' and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
                case 2:
                    selectSql2 = "SELECT DISTINCT title_code from trip_data " +
                            "where (title LIKE '%" + words[0] + "%' or title like '%" + words[1] + "%') and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
                default:
                    selectSql2 = "SELECT DISTINCT title_code from trip_data " +
                            "where (title LIKE '%" + words[0] + "%' or title like '%" + words[1] + "%' or title like '%" + words[2] + "%') and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
                    break;
            }

            // Store results from select statement

            resultSet2 = statement.executeQuery(selectSql2);

            while (resultSet2.next()) {
                titleCodes[i] = resultSet2.getInt(1);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titleCodes;
    }

    public static int[] relatedTitleCode(String keyWord, int start_date1, int start_date2) {
        String[] words = keyWord.split(" ");
        ArrayList<Integer> travelCodes = new ArrayList<>();

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql;
            switch (words.length) {
                case 1:
                    selectSql = "SELECT travel_code from dbo.travel_code " +
                            "where travel_code_name LIKE '%" + words[0] + "%' or country_area LIKE '%" + words[0] + "%' or continent LIKE '%" + words[0] + "%';";
                    break;
                case 2:
                    selectSql = "SELECT travel_code from dbo.travel_code " +
                            "where travel_code_name LIKE '%" + words[0] + "%' or country_area LIKE '%" + words[0] + "%' or continent LIKE '%" + words[0] + "%' or travel_code_name LIKE '%" + words[1] + "%' or country_area LIKE '%" + words[1] + "%' or continent LIKE '%" + words[1] + "%';";
                    break;
                default:
                    selectSql = "SELECT travel_code from dbo.travel_code " +
                            "where travel_code_name LIKE '%" + words[0] + "%' or country_area LIKE '%" + words[0] + "%' or continent LIKE '%" + words[0] + "%' or travel_code_name LIKE '%" + words[1] + "%' or country_area LIKE '%" + words[1] + "%' or continent LIKE '%" + words[1] + "%' or travel_code_name LIKE '%" + words[2] + "%' or country_area LIKE '%" + words[2] + "%' or continent LIKE '%" + words[2] + "%';";
                    break;
            }

            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                travelCodes.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int size = 0;

        ResultSet resultSet2 = null;

        // Create and execute a SELECT SQL statement.
        StringBuilder selectSqlj;
        if(travelCodes.size() != 0) {
            selectSqlj = new StringBuilder("SELECT COUNT(DISTINCT title_code) from trip_data where (travel_code=" + travelCodes.get(0));
        }
        else {
            selectSqlj = new StringBuilder("SELECT COUNT(DISTINCT title_code) from trip_data where travel_code=-1");
        }

        for(int j = 1; j <travelCodes.size(); j++) {
            selectSqlj.append(" or travel_code=").append(travelCodes.get(j));
        }
        selectSqlj.append(") and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';");

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Store results from select statement

            resultSet2 = statement.executeQuery(selectSqlj.toString());

            while (resultSet2.next()) {
                size = resultSet2.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int[] titleCodes = new int[size];
        int i = 0;

        ResultSet resultSet3 = null;

        // Create and execute a SELECT SQL statement.
        StringBuilder selectSql;
        if(travelCodes.size() != 0) {
            selectSql = new StringBuilder("SELECT DISTINCT title_code from trip_data where (travel_code=" + travelCodes.get(0));
        }
        else {
            selectSql = new StringBuilder("SELECT DISTINCT title_code from trip_data where travel_code=-1");
        }

        for(int j = 1; j <travelCodes.size(); j++) {
            selectSql.append(" or travel_code=").append(travelCodes.get(j));
        }
        selectSql.append(") and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';");

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Store results from select statement

            resultSet3 = statement.executeQuery(selectSql.toString());

            while (resultSet3.next()) {
                titleCodes[i] = resultSet3.getInt(1);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titleCodes;
    }

    public static int[] relatedProductCode(int title_code, int start_date1, int start_date2) {
        int size = 0;

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT COUNT(product_code) from trip_data " +
                    "where title_code='" + title_code + "' and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        int[] productCodes = new int[size];
        int i = 0;

        ResultSet resultSet2 = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT product_code from trip_data " +
                    "where title_code='" + title_code + "' and start_date>='" + start_date1 + "' and start_date <='" + start_date2 + "';";
            resultSet2 = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet2.next()) {
                productCodes[i] = resultSet2.getInt(1);
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return productCodes;
    }

    public int getProduct_code() {
        return product_code;
    }

    public int getTravel_code() {
        return travel_code;
    }

    public int getTitle_code() {
        return title_code;
    }

    public String getTitle() {
        return title;
    }

    public String getProduct_key() {
        return product_key;
    }

    public int getPrice() {
        return price;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public int getLower_bound() {
        return lower_bound;
    }

    public int getUpper_bound() {
        return upper_bound;
    }

    public int getRemaining() {
        return remaining;
    }
}