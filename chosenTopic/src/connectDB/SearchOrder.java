package connectDB;

import java.sql.*;

public class SearchOrder extends Orders {
    private static String[] getDBOrderData(int order_no) {

        ResultSet resultSet = null;

        String[] result=new String[12];

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "DECLARE @order_no nchar(10);" +
                    "set @order_no=" + order_no + ";" +
                    "SELECT od.order_no, od.member_id, od.product_code,td.title,td.product_key, td.start_date, td.end_date, od.adults_qty,od.kids_qty, od.babys_qty, od.total_price, od.order_date from order_data as od " +
                    "left join member_data as md on od.member_id = md.member_id " +
                    "left join trip_data as td on od.product_code = td.product_code " +
                    "where od.order_no = @order_no;";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                for(int i=0;i<12;i++) {
                    result[i]=resultSet.getString(i+1);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int[] getOrderNos(String member_id) {
        ResultSet resultSet = null;

        int numberOfOrderNos=0;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT COUNT(member_id) from order_data " +
                    "where member_id='" + member_id + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                numberOfOrderNos=resultSet.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet2 = null;

        int[] orderNos = new int[numberOfOrderNos];
        int i=0;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT order_no, member_id from order_data " +
                    "where member_id='" + member_id + "';";
            resultSet2 = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet2.next()) {
                orderNos[i]=resultSet2.getInt(1);
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return orderNos;
    }

    public Orders orderContents (int order_no) {
        Orders order=new Orders(order_no);
        String[] contents=SearchOrder.getDBOrderData(order_no);
        order.setMember_id(contents[1]);
        order.setProduct_code(Integer.parseInt(contents[2]));
        order.setTitle(contents[3]);
        order.setProduct_key(contents[4]);
        order.setStart_date(contents[5]);
        order.setEnd_date(contents[6]);
        order.setAdults_qty(Integer.parseInt(contents[7]));
        order.setKids_qty(Integer.parseInt(contents[8]));
        order.setBabys_qty(Integer.parseInt(contents[9]));
        order.setTotal_price(Integer.parseInt(contents[10]));
        order.setOrder_date(contents[11]);
        return order;
    }
}