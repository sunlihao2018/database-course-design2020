package sample.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.*;

import java.sql.*;

/**
 * @author 天逸流水
 * @date 2020/6/2 22:56
 */
public class QueryUtil {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private ObservableList<Person> personDataUp = FXCollections.observableArrayList();
    private ObservableList<Person> personDataDown = FXCollections.observableArrayList();
    private ObservableList<Raw> rawData = FXCollections.observableArrayList();
    private ObservableList<RawLog> rawLogData = FXCollections.observableArrayList();
    private ObservableList<Production> productionData = FXCollections.observableArrayList();
    private ObservableList<ProductionLog> productionLogData = FXCollections.observableArrayList();
    private ObservableList<Buy> buyDataUp = FXCollections.observableArrayList();
    private ObservableList<Buy> buyDataDown = FXCollections.observableArrayList();

    public QueryUtil(Connection conn) {
        try {
            this.conn = conn;
            stmt = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<Person> getAllPersonData() {
        try {
            personDataUp.clear();
            String sql = "select * from 人员表";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String no = rs.getString(1);
                String name = rs.getString(2);
                String gender = rs.getString(3);
                String phone = rs.getString(4);
                String position = rs.getString(5);

                personDataUp.add(new Person(no, name, gender, phone, position));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personDataUp;
    }

    public boolean personAdd(Person person) {
        try {
            String person_no = person.getNo();
            String sql1 = "select count(*) from 人员表 where 人员编号 = '" + person_no + "'";
            rs = stmt.executeQuery(sql1);
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            if (count == 1) {
                // 存在
                return false;
            } else {
                // 插入
                String sql2 = "insert into 人员表 values(?,?,?,?,?)";
                preparedStatement = conn.prepareStatement(sql2);
                preparedStatement.setString(1, person.getNo());
                preparedStatement.setString(2, person.getName());
                preparedStatement.setString(3, person.getGender());
                preparedStatement.setString(4, person.getPhone());
                preparedStatement.setString(5, person.getPosition());

                return preparedStatement.executeUpdate() == 1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean personEdit(Person person) {
        try {
            String person_no = person.getNo();
            String sql = "update 人员表 set 姓名=?, 性别=?, 电话=?, 职位=? where 人员编号=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getGender());
            preparedStatement.setString(3, person.getPhone());
            preparedStatement.setString(4, person.getPosition());
            preparedStatement.setString(5, person_no);

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean personDelete(Person person) {
        try {
            String sql = "delete from 人员表 where 人员编号=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, person.getNo());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ObservableList<Person> getFindPersonData(String str) {
        try {
            personDataDown.clear();
            String sql = "select * from 人员表 where 姓名 like \"%" + str + "%\"";
            Statement st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String no = rs.getString(1);
                String name = rs.getString(2);
                String gender = rs.getString(3);
                String phone = rs.getString(4);
                String position = rs.getString(5);

                personDataDown.add(new Person(no, name, gender, phone, position));
            }
        } catch (SQLException throwables) {
            System.out.println("错了");
            throwables.printStackTrace();
        }
        return personDataDown;
    }

    public ObservableList<Raw> getAllRawData() {
        try {
            rawData.clear();
            String sql = "select * from 原材料总仓库";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String no = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String size = rs.getString(3);
                String amount = String.valueOf(rs.getFloat(4));
                String unit = rs.getString(5);

                rawData.add(new Raw(no, name, size, amount, unit));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rawData;
    }

    public ObservableList<RawLog> getAllRawLogData() {
        try {
            rawLogData.clear();
            String sql = "select `原材料进出库日志记录`.`原材料日志编号`, `原材料总仓库`.`原材料名称`, `原材料进出库日志记录`.`进库或去向`, `原材料进出库日志记录`.`规格`, `原材料进出库日志记录`.`数量`, `原材料进出库日志记录`.`单位`, `原材料进出库日志记录`.`日期`, `原材料进出库日志记录`.`说明`\n" +
                    "from `原材料进出库日志记录`, `原材料总仓库`\n" +
                    "where `原材料进出库日志记录`.`原材料编号` = `原材料总仓库`.`原材料编号`";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String rawlog = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String where = rs.getString(3);
                String size = rs.getString(4);
                String amount = String.valueOf(rs.getFloat(5));
                String unit = rs.getString(6);
                String time = rs.getString(7);
                String explain = rs.getString(8);

                rawLogData.add(new RawLog(rawlog, name, where, size, amount, unit, time, explain));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rawLogData;
    }

    public boolean rawLogAdd(int no, String where, float amount, String explain) {
        try {
            String sql1 = "select * from 原材料总仓库 where 原材料编号 = " + no;
            rs = stmt.executeQuery(sql1);
            String guige = "";
            String danwei = "";
            while (rs.next()) {
                guige = rs.getString(3);
                danwei = rs.getString(5);
            }
            String sql2 = "insert into `原材料进出库日志记录` (原材料编号, 进库或去向, 规格, 数量, 单位, 日期, 说明) values (?, ?, ?, ?, ?, NOW(), ?)";
            preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setInt(1, no);
            preparedStatement.setString(2, where);
            preparedStatement.setString(3, guige);
            preparedStatement.setFloat(4, amount);
            preparedStatement.setString(5, danwei);
            preparedStatement.setString(6, explain);

            preparedStatement.executeUpdate();

            if (where.equals("入库")) {
                String sql3 = "update 原材料总仓库 set 数量 = 数量 + ? where 原材料编号 = ?";
                preparedStatement = conn.prepareStatement(sql3);
                preparedStatement.setFloat(1, amount);
                preparedStatement.setInt(2, no);

                return preparedStatement.executeUpdate() == 1;
            } else {
                String sql3 = "update 原材料总仓库 set 数量 = 数量 - ? where 原材料编号 = ?";
                preparedStatement = conn.prepareStatement(sql3);
                preparedStatement.setFloat(1, amount);
                preparedStatement.setInt(2, no);

                return preparedStatement.executeUpdate() == 1;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ObservableList<Production> getAllProductionData() {
        try {
            productionData.clear();
            String sql = "select * from 成品仓库";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String no = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String amount = String.valueOf(rs.getInt(3));
                String unit = rs.getString(4);

                productionData.add(new Production(no, name, amount, unit));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productionData;
    }

    public ObservableList<ProductionLog> getAllProductionLogData() {
        try {
            productionLogData.clear();
            String sql = "select `成品仓库进出库日志记录`.`产品日志编号`, `成品仓库`.`产品名称`, `成品仓库进出库日志记录`.`来源或出库`, `成品仓库进出库日志记录`.`数量`, `成品仓库进出库日志记录`.`单位`, `成品仓库进出库日志记录`.`时间`, `成品仓库进出库日志记录`.`说明`\n" +
                    "from `成品仓库`, `成品仓库进出库日志记录`\n" +
                    "where `成品仓库进出库日志记录`.`产品编号` = `成品仓库`.`产品编号`";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String no = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String where = rs.getString(3);
                String amount = String.valueOf(rs.getInt(4));
                String unit = rs.getString(5);
                String time = rs.getString(6);
                String explain = rs.getString(7);

                productionLogData.add(new ProductionLog(no, name, where, amount, unit, time, explain));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productionLogData;
    }

    public boolean productionLogAdd(int no, String where, int amount, String explain) {
        try {
            String sql1 = "select * from 成品仓库 where 产品编号 = " + no;
            rs = stmt.executeQuery(sql1);
            String danwei = "";
            while (rs.next()) {
                danwei = rs.getString(4);
            }
            String sql2 = "insert into `成品仓库进出库日志记录` (产品编号, 来源或出库, 数量, 单位, 时间, 说明) values (?, ?, ?, ?, NOW(), ?)";
            preparedStatement = conn.prepareStatement(sql2);
            preparedStatement.setInt(1, no);
            preparedStatement.setString(2, where);
            preparedStatement.setInt(3, amount);
            preparedStatement.setString(4, danwei);
            preparedStatement.setString(5, explain);

            preparedStatement.executeUpdate();

            if (where.equals("入库")) {
                String sql3 = "update 成品仓库 set 数量 = 数量 + ? where 产品编号 = ?";
                preparedStatement = conn.prepareStatement(sql3);
                preparedStatement.setInt(1, amount);
                preparedStatement.setInt(2, no);

                return preparedStatement.executeUpdate() == 1;
            } else {
                String sql3 = "update 成品仓库 set 数量 = 数量 - ? where 产品编号 = ?";
                preparedStatement = conn.prepareStatement(sql3);
                preparedStatement.setInt(1, amount);
                preparedStatement.setInt(2, no);

                return preparedStatement.executeUpdate() == 1;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ObservableList<Buy> getAllBuyData() {
        try {
            buyDataUp.clear();
            String sql = "select * from 采购订单";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String no = String.valueOf(rs.getInt(1));
                String factory = rs.getString(2);
                String raw = rs.getString(3);
                String size = rs.getString(4);
                String amount = String.valueOf(rs.getFloat(5));
                String unit = rs.getString(6);
                String price = String.valueOf(rs.getFloat(7));
                String allprice = String.valueOf(rs.getFloat(8));
                String time = rs.getString(9);

                buyDataUp.add(new Buy(no, factory, raw, size, amount, unit, price, allprice, time));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return buyDataUp;
    }

    public ObservableList<Buy> getFindBuyData(String str) {
        try {
            buyDataDown.clear();
            String sql = "select * from 采购订单 where 原材料名称 like \"%" + str + "%\"";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String no = String.valueOf(rs.getInt(1));
                String factory = rs.getString(2);
                String raw = rs.getString(3);
                String size = rs.getString(4);
                String amount = String.valueOf(rs.getFloat(5));
                String unit = rs.getString(6);
                String price = String.valueOf(rs.getFloat(7));
                String allprice = String.valueOf(rs.getFloat(8));
                String time = rs.getString(9);

                buyDataDown.add(new Buy(no, factory, raw, size, amount, unit, price, allprice, time));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return buyDataDown;
    }

    public boolean buyAdd(String factory, String raw, String size, Float amount, String unit, Float price, Float allprice) {
        try {
            String sql = "insert into `采购订单` (厂家, 原材料名称, 规格, 数量, 单位, 单价, 总价, 时间) values(?, ?, ?, ?, ?, ?, ?, Now())";
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, factory);
            preparedStatement.setString(2, raw);
            preparedStatement.setString(3, size);
            preparedStatement.setFloat(4, amount);
            preparedStatement.setString(5, unit);
            preparedStatement.setFloat(6, price);
            preparedStatement.setFloat(7, allprice);

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean buyDelete(Buy buy) {
        try {
            String sql = "delete from 采购订单 where 采购订单编号=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, buy.getNo());

            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
