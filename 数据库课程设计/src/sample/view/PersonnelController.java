package sample.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Person;
import sample.util.DatabaseUtil;
import sample.util.QueryUtil;

import java.sql.Connection;

/**
 * @author 天逸流水
 * @date 2020/6/4 12:04
 */
public class PersonnelController {
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private TextField str;
    @FXML
    private Button find;
    @FXML
    private TableView<Person> personTableUp;
    @FXML
    private TableColumn<Person, String> noColumnUp;
    @FXML
    private TableColumn<Person, String> nameColumnUp;
    @FXML
    private TableColumn<Person, String> genderColumnUp;
    @FXML
    private TableColumn<Person, String> phoneColumnUp;
    @FXML
    private TableColumn<Person, String> positionColumnUp;

    @FXML
    private TableView<Person> personTableDown;
    @FXML
    private TableColumn<Person, String> noColumnDown;
    @FXML
    private TableColumn<Person, String> nameColumnDown;
    @FXML
    private TableColumn<Person, String> genderColumnDown;
    @FXML
    private TableColumn<Person, String> phoneColumnDown;
    @FXML
    private TableColumn<Person, String> positionColumnDown;

    private Main main;
    private Connection conn;
    private Stage rootStage;
    private QueryUtil queryUtil;
    private int flag = 0;

    @FXML
    private void initialize() {
        noColumnUp.setCellValueFactory(cellData -> cellData.getValue().noProperty());
        nameColumnUp.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        genderColumnUp.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        phoneColumnUp.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        positionColumnUp.setCellValueFactory(cellData -> cellData.getValue().positionProperty());

        personTableUp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
                flag = 1;
                // System.out.println("1");
            }
        });

        personTableDown.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
                flag = 2;
                // System.out.println("2");
            }
        });
    }

    public void setMain(Main main, DatabaseUtil databaseUtil, QueryUtil queryUtil, Stage rootStage) {
        this.main = main;
        this.conn = databaseUtil.getConn();
        this.queryUtil = queryUtil;
        this.rootStage = rootStage;
        personTableUp.setItems(queryUtil.getAllPersonData());
    }


    @FXML
    private void handleAdd() {
        main.showPersonAddLayout();
    }
    @FXML
    private void handleEdit() {
        // 修改
        // 首先找出选中的人，没有则报错
        Person selectedPerson;
        if (flag == 1) {
            selectedPerson = personTableUp.getSelectionModel().getSelectedItem();
        } else {
            selectedPerson = personTableDown.getSelectionModel().getSelectedItem();
        }

        if (selectedPerson != null) {
            // 修改
            main.showPersonEditLayout(selectedPerson);
            // 进入界面，信息默认打在文本域中，编号不能修改， 取消是不做任何操作，确定是进行修改。
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(rootStage);
            alert.setTitle("编辑错误");
            alert.setHeaderText("你没有选中某个人");
            alert.setContentText("请选择后重新点击修改");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleDelete() {
        // 删除
        Person selectedPerson;
        if (flag == 1) {
            selectedPerson = personTableUp.getSelectionModel().getSelectedItem();
        } else {
            selectedPerson = personTableDown.getSelectionModel().getSelectedItem();
        }
        if (selectedPerson != null) {
            main.showPersonDeleteLayout(selectedPerson);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(rootStage);
            alert.setTitle("删除错误");
            alert.setHeaderText("你没有选中某个人");
            alert.setContentText("请选择后重新点击删除");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleFind() {
        // 查找
        // 提取文本框中的文字，不需要有新页面，在下面的TableView中展示
        noColumnDown.setCellValueFactory(cellData -> cellData.getValue().noProperty());
        nameColumnDown.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        genderColumnDown.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        phoneColumnDown.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        positionColumnDown.setCellValueFactory(cellData -> cellData.getValue().positionProperty());

        personTableDown.setItems(queryUtil.getFindPersonData(str.getText()));
    }
}
