package sample.util;

import javafx.scene.control.Alert;

import java.sql.*;

/**
 * @author 天逸流水
 * @date 2020/6/2 22:07
 */
public class DatabaseUtil {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://你的数据库IP:3306/数据库课程设计?useUnicode=true&characterEncoding=utf-8";
    private static final String USER = "账号";
    private static final String PASS = "密码";
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;


    public DatabaseUtil() {
        connectDatabase();
    }

    public Connection getConn() {
        return conn;
    }

    /**
    public static void main(String[] args) {
        Connection conn;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "select * from 人员表";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String sid = rs.getString(1);
                String name = rs.getString(2);
                String gender = rs.getString(3);

                System.out.println(sid);
                System.out.println(name);
                System.out.println(gender);

            }
            rs.close();
            stmt.close();
            conn.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
*/
    private void connectDatabase() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            // alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("进出库管理系统");
            alert.setHeaderText("数据库连接失败");
            alert.setContentText("请修改账号或密码后重新登录");

            alert.showAndWait();
        }
    }

    public ResultSet query(String sql) {
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public void close() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
