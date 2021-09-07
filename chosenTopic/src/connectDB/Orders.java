package connectDB;

import java.sql.*;

public class Orders {
    private int order_no;
    private String member_id;
    private int product_code;
    private String title;
    private String product_key;
    private String start_date;
    private String end_date;
    private int adults_qty;
    private int kids_qty;
    private int babys_qty;
    private int total_price;
    private String order_date;

    static final String connectionUrl =
            "jdbc:sqlserver://127.0.0.1\\SQLEXPRESS;"
                    + "database=Travel;"
                    + "user=sa;"
                    + "password=b075050;";

    public Orders() {

    }

    public Orders(int order_no) {
        this.order_no=order_no;
    }

    public Orders(int order_no, String member_id, int product_code, int adults_qty, int kids_qty, int babys_qty, String order_date) {
        this.order_no=order_no;
        this.member_id=member_id;
        this.product_code=product_code;
        this.adults_qty=adults_qty;
        this.kids_qty=kids_qty;
        this.babys_qty=babys_qty;
        this.order_date=order_date;

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT product_code, price from trip_data " +
                    "where product_code='" + this.product_code+ "';";
            resultSet = statement.executeQuery(selectSql);


            while (resultSet.next()) {
                int unitPrice=resultSet.getInt(2);
                this.total_price=unitPrice*this.adults_qty+(int)(0.9*unitPrice)*this.kids_qty+(int)(0.7*unitPrice)*this.babys_qty;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Orders(int order_no, String member_id, String product_key, String start_date, int adults_qty, int kids_qty, int babys_qty, String order_date) {
        this.order_no=order_no;
        this.member_id=member_id;
        //this.product_code=product_code;
        this.product_key=product_key;
        this.start_date=start_date;
        this.adults_qty=adults_qty;
        this.kids_qty=kids_qty;
        this.babys_qty=babys_qty;
        this.order_date=order_date;

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT MIN(product_code), price from trip_data " +
                    "where product_key='" + this.product_key + "' and start_date='" + this.start_date + "' group by price;";
            resultSet = statement.executeQuery(selectSql);


            while (resultSet.next()) {
                this.product_code=resultSet.getInt(1);
                int unitPrice=resultSet.getInt(2);
                this.total_price=unitPrice*this.adults_qty+(int)(0.9*unitPrice)*this.kids_qty+(int)(0.7*unitPrice)*this.babys_qty;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Orders(int order_no, String member_id, int product_code, String title, String product_key, String start_date, String end_date, int adults_qty, int kids_qty, int babys_qty, int total_price, String order_date) {
        this.order_no=order_no;
        this.member_id=member_id;
        this.product_code=product_code;
        this.title=title;
        this.product_key=product_key;
        this.start_date=start_date;
        this.end_date=end_date;
        this.adults_qty=adults_qty;
        this.kids_qty=kids_qty;
        this.babys_qty=babys_qty;
        this.total_price=total_price;
        this.order_date=order_date;
    }

    public static String getLastOrderNo() {
        ResultSet resultSet = null;

        StringBuffer lastOrderNo = new StringBuffer("");

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT MAX(order_no) from order_data;";
            resultSet = statement.executeQuery(selectSql);


            while (resultSet.next()) {
                lastOrderNo.append(resultSet.getInt(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return lastOrderNo.toString();
    }

    public void addOrder() {

        String insertSql = "INSERT INTO order_data (order_no, member_id, product_code, adults_qty, kids_qty, babys_qty, total_price, order_date) " +
                "VALUES ('" + this.order_no + "', " + "'" + this.member_id + "', " + "'" + this.product_code + "', " + "'" + this.adults_qty + "', " + "'" + this.kids_qty + "', " + "'" + this.babys_qty + "', " + "'" + this.total_price + "', " + "'" + this.order_date + "');";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsertProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet = prepsInsertProduct.getGeneratedKeys();

            // Print the order_no of the inserted row.
//            while (resultSet.next()) {
//                System.out.println("Successfully generated 1 order: order_no=" + this.order_no + " !");
//            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String updateSql = "UPDATE trip_data " +
                "SET remaining=remaining-'" + (adults_qty + kids_qty + babys_qty) + "' " +
                "where product_code='" + this.product_code + "';";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsUpdateProduct.execute();
            // Retrieve the generated key from the insert.
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteOrder(int order_no) {
        ResultSet resultSet = null;

        int product_code = 0;
        int peopleQty = 0;
        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT product_code, adults_qty, kids_qty, babys_qty from order_data " +
                    "where order_no='" + order_no + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                product_code = resultSet.getInt(1);
                peopleQty = resultSet.getInt(2) + resultSet.getInt(3) + resultSet.getInt(4);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String deleteSql = "DELETE FROM order_data " +
                "where order_no='" + order_no + "';";

        ResultSet resultSet1 = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsDeleteProduct = connection.prepareStatement(deleteSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsDeleteProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet1 = prepsDeleteProduct.getGeneratedKeys();

            // Print the order_no of the deleted row.
//            while (resultSet1.next()) {
//                System.out.println("Successfully deleted 1 order: order_no=" + order_no + " !");
//            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String updateSql = "UPDATE trip_data " +
                "SET remaining=remaining+'" + peopleQty + "' " +
                "where product_code='" + product_code + "';";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsUpdateProduct.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void UpdateProductCode(int order_no, int product_code, String order_date) {
        ResultSet resultSet = null;

        int newPrice=0;
        int originProductCode=0;
        int adults_qty=0;
        int kids_qty=0;
        int babys_qty=0;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT td.price, od.product_code, od.adults_qty, od.kids_qty, od.babys_qty from trip_data as td " +
                    "right join order_data as od on od.order_no='" + order_no + "' " +
                    "where td.product_code='" + product_code + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                newPrice=resultSet.getInt(1);
                originProductCode=resultSet.getInt(2);
                adults_qty=resultSet.getInt(3);
                kids_qty=resultSet.getInt(4);
                babys_qty=resultSet.getInt(5);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        int diff=adults_qty+kids_qty+babys_qty;
        int newTotalPrice=newPrice*adults_qty+(int)(0.9*newPrice)*kids_qty+(int)(0.7*newPrice)*babys_qty;
        String updateSql = "UPDATE order_data " +
                "SET product_code='" + product_code + "', " + "total_price='" + newTotalPrice + "', " + "order_date='" + order_date + "' " +
                "where order_no='" + order_no + "' " +
                "UPDATE trip_data " +
                "SET remaining=remaining+'" + diff + "' " +
                "where product_code='" + originProductCode + "' " +
                "UPDATE trip_data " +
                "SET remaining=remaining-'" + diff + "' " +
                "where product_code='" + product_code + "';";

        ResultSet resultSet2 = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsUpdateProduct.execute();

//            System.out.println("Successfully updated 1 order: order_no=" + order_no + " !");
//            System.out.println("product_code has updated!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateAdultsQty(int order_no, int adults_qty, String order_date) {
        ResultSet resultSet = null;

        int product_code=0;
        int originAdultsQty=0;
        int kids_qty=0;
        int babys_qty=0;
        int price=0;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT od.product_code, od.adults_qty, od.kids_qty, od.babys_qty, td.price from order_data as od " +
                    "right join trip_data as td on td.product_code=od.product_code " +
                    "where od.order_no='" + order_no + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                product_code=resultSet.getInt(1);
                originAdultsQty=resultSet.getInt(2);
                kids_qty=resultSet.getInt(3);
                babys_qty=resultSet.getInt(4);
                price=resultSet.getInt(5);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        int diff=originAdultsQty-adults_qty;
        int newTotalPrice=price*adults_qty+(int)(0.9*price)*kids_qty+(int)(0.7*price)*babys_qty;
        String updateSql = "UPDATE order_data " +
                "SET adults_qty='" + adults_qty + "', " + "total_price='" + newTotalPrice + "', " + "order_date='" + order_date + "' " +
                "where order_no='" + order_no + "'" +
                "UPDATE trip_data " +
                "SET remaining=remaining+'" + diff + "' " +
                "where product_code='" + product_code + "';";

        ResultSet resultSet2 = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsUpdateProduct.execute();

//            System.out.println("Successfully updated 1 order: order_no=" + order_no + " !");
//            System.out.println("adults_qty has updated!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateKidsQty(int order_no, int kids_qty, String order_date) {
        ResultSet resultSet = null;

        int product_code=0;
        int adults_qty=0;
        int originKidsQty=0;
        int babys_qty=0;
        int price=0;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT od.product_code, od.adults_qty, od.kids_qty, od.babys_qty, td.price from order_data as od " +
                    "right join trip_data as td on td.product_code=od.product_code " +
                    "where od.order_no='" + order_no + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                product_code=resultSet.getInt(1);
                adults_qty=resultSet.getInt(2);
                originKidsQty=resultSet.getInt(3);
                babys_qty=resultSet.getInt(4);
                price=resultSet.getInt(5);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        int diff=originKidsQty-kids_qty;
        int newTotalPrice=price*adults_qty+(int)(0.9*price)*kids_qty+(int)(0.7*price)*babys_qty;
        String updateSql = "UPDATE order_data " +
                "SET kids_qty='" + kids_qty + "', " + "total_price='" + newTotalPrice + "', " + "order_date='" + order_date + "' " +
                "where order_no='" + order_no + "'" +
                "UPDATE trip_data " +
                "SET remaining=remaining+'" + diff + "' " +
                "where product_code='" + product_code + "';";

        ResultSet resultSet2 = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsUpdateProduct.execute();

//            System.out.println("Successfully updated 1 order: order_no=" + order_no + " !");
//            System.out.println("kids_qty has updated!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateBabysQty(int order_no, int babys_qty, String order_date) {
        ResultSet resultSet = null;

        int product_code=0;
        int adults_qty=0;
        int kids_qty=0;
        int originBabysQty=0;
        int price=0;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT od.product_code, od.adults_qty, od.kids_qty, od.babys_qty, td.price from order_data as od " +
                    "right join trip_data as td on td.product_code=od.product_code " +
                    "where od.order_no='" + order_no + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                product_code=resultSet.getInt(1);
                adults_qty=resultSet.getInt(2);
                kids_qty=resultSet.getInt(3);
                originBabysQty=resultSet.getInt(4);
                price=resultSet.getInt(5);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        int diff=originBabysQty-babys_qty;
        int newTotalPrice=price*adults_qty+(int)(0.9*price)*kids_qty+(int)(0.7*price)*babys_qty;
        String updateSql = "UPDATE order_data " +
                "SET babys_qty='" + babys_qty + "', " + "total_price='" + newTotalPrice + "', " + "order_date='" + order_date + "' " +
                "where order_no='" + order_no + "'" +
                "UPDATE trip_data " +
                "SET remaining=remaining+'" + diff + "' " +
                "where product_code='" + product_code + "';";

        ResultSet resultSet2 = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsUpdateProduct.execute();

//            System.out.println("Successfully updated 1 order: order_no=" + order_no + " !");
//            System.out.println("babys_qty has updated!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateWholeOrder(int order_no, int product_code, int adults_qty, int kids_qty, int babys_qty, String order_date) {
        UpdateProductCode(order_no,product_code,order_date);
        UpdateAdultsQty(order_no,adults_qty,order_date);
        UpdateKidsQty(order_no,kids_qty,order_date);
        UpdateBabysQty(order_no,babys_qty,order_date);
        //System.out.println("Successfully updated 1 order: order_no=" + order_no + " !");
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public int getProduct_code() {
        return product_code;
    }

    public void setProduct_code(int product_code) {
        this.product_code = product_code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProduct_key() {
        return product_key;
    }

    public void setProduct_key(String product_key) {
        this.product_key = product_key;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getAdults_qty() {
        return adults_qty;
    }

    public void setAdults_qty(int adults_qty) {
        this.adults_qty = adults_qty;
    }

    public int getKids_qty() {
        return kids_qty;
    }

    public void setKids_qty(int kids_qty) {
        this.kids_qty = kids_qty;
    }

    public int getBabys_qty() {
        return babys_qty;
    }

    public void setBabys_qty(int babys_qty) {
        this.babys_qty = babys_qty;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
}
