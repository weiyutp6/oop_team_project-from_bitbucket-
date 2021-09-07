package connectDB;

import java.sql.*;

public class Members {
    private String member_id;
    private String member_name;
    private String pwd;
    private String id_no;

    public Members(String member_id, String member_name, String pwd, String id_no) {
        this.member_id=member_id;
        this.member_name=member_name;
        this.pwd=pwd;
        this.id_no=id_no;
    }

    public Members(String member_id) {
        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT member_id, member_name, pwd, id_no from member_data " +
                    "where member_id='" + member_id + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                this.member_id=member_id;
                this.member_name=resultSet.getString(2);
                this.pwd=resultSet.getString(3);
                this.id_no=resultSet.getString(4);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean isThisMemberIdNotUsed(String member_id) {
        ResultSet resultSet = null;

        boolean check = true;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT member_id from member_data " +
                    "where member_id='" + member_id + "';";
            resultSet = statement.executeQuery(selectSql);

            // Store results from select statement

            while (resultSet.next()) {
                check = !member_id.equals(resultSet.getString(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public void registerMember() {
        String insertSql = "INSERT INTO member_data (member_id, member_name, pwd, id_no) " +
                "VALUES ('" + this.member_id + "', " + "'" + this.member_name + "', " + "'" + this.pwd + "', " + "'" + this.id_no + "');";

        ResultSet resultSet2 = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsertProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet2 = prepsInsertProduct.getGeneratedKeys();

            // Print the member_id of the inserted row.
//            while (resultSet2.next()) {
//                System.out.println("Successfully generated 1 member data: member_id=" + this.member_id + " !");
//            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdatePwd(String member_id, String newPwd) {
        String updateSql = "UPDATE member_data " +
                "SET pwd='" + newPwd + "' " +
                "where member_id='" + member_id + "';";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(Orders.connectionUrl);
             PreparedStatement prepsUpdateProduct = connection.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsUpdateProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet = prepsUpdateProduct.getGeneratedKeys();

            // Print the member_id of the updated row.
//            while (resultSet.next()) {
//                System.out.println("Successfully updated 1 member's pwd: member_id=" + member_id + " !");
//            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getMember_id() {
        return member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getId_no() {
        return id_no;
    }
}
