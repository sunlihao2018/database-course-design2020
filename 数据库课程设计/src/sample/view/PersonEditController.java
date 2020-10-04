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
 * @date 2020/6/6 10:30
 */
public class PersonEditController {
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
    private Stage personEditStage;
    private QueryUtil queryUtil;
    private Person person;

    public void setMain (Main main, Stage personEditStage) {
        this.main = main;
        this.personEditStage = personEditStage;
        this.queryUtil = main.getQueryUtil();
    }

    public void setPerson(Person person) {
        this.person = person;

        no.setText(person.getNo());
        name.setText(person.getName());
        gender.getSelectionModel().select(person.getGender());
        phone.setText(person.getPhone());
        position.setText(person.getPosition());
    }

    @FXML
    private void initialize() {
        gender.getItems().addAll("男", "女");
    }
    @FXML
    private void handleCancel() {
        personEditStage.close();
        // 关闭窗口
    }
    @FXML
    private void handelEdit() {
        String name = this.name.getText();
        String gender = this.gender.getValue();
        String phone = this.phone.getText();
        String position = this.position.getText();

        if (name.equals(person.getName()) && gender.equals(person.getGender()) && phone.equals(person.getPhone()) && position.equals(person.getPosition())) {
            // 没修改
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(personEditStage);
            alert.setTitle("编辑错误");
            alert.setHeaderText("没有任何值的修改");
            alert.setContentText("请修改后重新点击修改");

            alert.showAndWait();
        } else {
            // 修改
            Person newPerson = new Person(person.getNo(), name, gender, phone, position);
            boolean ok = queryUtil.personEdit(newPerson);
            if (ok) {
                personEditStage.close();
                queryUtil.getAllPersonData();
            } else {
                System.out.println("更新失败");
            }
        }
    }
}
