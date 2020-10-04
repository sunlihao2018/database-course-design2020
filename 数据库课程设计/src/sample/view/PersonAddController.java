package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Person;
import sample.util.QueryUtil;

/**
 * @author 天逸流水
 * @date 2020/6/5 9:21
 */
public class PersonAddController {
    @FXML
    private TextField no;
    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private TextField phone;
    @FXML
    private TextField position;
    @FXML
    private Button cancel;
    @FXML
    private Button add;

    private Main main;
    private Stage personAddStage;
    private QueryUtil queryUtil;

    public void setMain(Main main, Stage personAddStage) {
        this.main = main;
        this.personAddStage = personAddStage;
        this.queryUtil = main.getQueryUtil();
    }
    @FXML
    private void initialize() {
        gender.getItems().addAll("男", "女");
    }
    @FXML
    private void handleCancel() {
        personAddStage.close();
        // 关闭窗口
    }
    @FXML
    private void handleAdd() {

        String strno = no.getText();
        String strname = name.getText();
        String strgender = gender.getValue();
        String strphone = phone.getText();
        String strposition = position.getText();
        if (strno.equals("") || strname.equals("") || strgender == null || strphone.equals("") || strposition.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(personAddStage);
            alert.setTitle("人事添加");
            alert.setHeaderText("你输入的信息不完善");
            alert.setContentText("请补全信息后重新添加");
            alert.showAndWait();
        } else {
            // 查询数据库中有没有，没有就可以添加，有就报错。
            Person person = new Person(strno, strname, strgender, strphone, strposition);
            boolean ok = queryUtil.personAdd(person);
            if (ok) {
                System.out.println("插入成功");
                // 关闭窗口
                personAddStage.close();
                // 刷新
                queryUtil.getAllPersonData();
            } else {
                System.out.println("插入失败");
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.initOwner(personAddStage);
                alert1.setTitle("人事添加");
                alert1.setHeaderText("添加人员失败");
                alert1.setContentText("可能想要添加的人员的编号已经存在");
                alert1.showAndWait();
            }
        }
    }
}
