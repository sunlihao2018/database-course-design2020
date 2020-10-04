package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Main;
import sample.util.DatabaseUtil;

import java.sql.ResultSet;


/**
 * @author 天逸流水
 * @date 2020/6/2 21:51
 */
public class LoginController {
    @FXML
    private TextField accountText;
    @FXML
    private PasswordField passwordText;
    private DatabaseUtil databaseUtil;
    private Main main;

    public void setMain(Main main, DatabaseUtil databaseUtil) {
        this.main = main;
        this.databaseUtil = databaseUtil;
    }

    @FXML
    private void handleLogin() {
        String account = accountText.getText();
        String password = passwordText.getText();
        String sql = "select account, password from user";
        try {
            ResultSet rs = databaseUtil.query(sql);
            while (rs.next()) {
                String user = rs.getString(1);
                String pa = rs.getString(2);

                if (user.equals(account) && pa.equals(password)) {
                    // System.out.println(user + pa);
                    main.showRootLayout();
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            // alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("进出库管理系统");
            alert.setHeaderText("你输入的账号或密码不正确");
            alert.setContentText("请修改账号或密码后重新登录");

            alert.showAndWait();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
