package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Person;
import sample.util.QueryUtil;

/**
 * @author 天逸流水
 * @date 2020/6/6 18:41
 */
public class PersonDeleteController {
    @FXML
    private Label no;
    @FXML
    private Label name;
    @FXML
    private Label gender;
    @FXML
    private Label phone;
    @FXML
    private Label position;
    private Main main;
    private Stage personDeleteStage;
    private QueryUtil queryUtil;
    private Person person;

    public void setMain(Main main, Stage personDeleteStage) {
        this.main = main;
        this.personDeleteStage = personDeleteStage;
        this.queryUtil = main.getQueryUtil();
    }

    public void setPerson (Person person) {
        this.person = person;

        this.no.setText(person.getNo());
        this.name.setText(person.getName());
        this.gender.setText(person.getGender());
        this.phone.setText(person.getPhone());
        this.position.setText(person.getPosition());
    }

    @FXML
    private void handleCancel() {
        personDeleteStage.close();
        // 关闭窗口
    }

    @FXML
    private void handleOK() {
        queryUtil.personDelete(person);
        personDeleteStage.close();
        queryUtil.getAllPersonData();
    }
}
