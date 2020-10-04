package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Raw;
import sample.model.RawLog;
import sample.util.QueryUtil;

/**
 * @author 天逸流水
 * @date 2020/6/13 16:17
 */
public class RawController {
    @FXML
    private TableView<Raw> row;
    @FXML
    private TableColumn<Raw, String> rowno;
    @FXML
    private TableColumn<Raw, String> rowname;
    @FXML
    private TableColumn<Raw, String> rowsize;
    @FXML
    private TableColumn<Raw, String> rowamount;
    @FXML
    private TableColumn<Raw, String> rowunit;
    @FXML
    private TableView<RawLog> rowlog;
    @FXML
    private TableColumn<RawLog, String> rowlogno;
    @FXML
    private TableColumn<RawLog, String> rowlogname;
    @FXML
    private TableColumn<RawLog, String> rowlogwhere;
    @FXML
    private TableColumn<RawLog, String> rowlogsize;
    @FXML
    private TableColumn<RawLog, String> rowlogamount;
    @FXML
    private TableColumn<RawLog, String> rowlogunit;
    @FXML
    private TableColumn<RawLog, String> rowlogtime;
    @FXML
    private TableColumn<RawLog, String> rowlogexplain;

    private Main main;
    private Stage rootStage;
    private QueryUtil queryUtil;

    public void setMain(Main main, Stage rootStage, QueryUtil queryUtil) {
        this.main = main;
        this.rootStage = rootStage;
        this.queryUtil = queryUtil;
        row.setItems(queryUtil.getAllRawData());
        rowlog.setItems(queryUtil.getAllRawLogData());
    }
    @FXML
    public void initialize() {
        rowno.setCellValueFactory(cellData -> cellData.getValue().noProperty());
        rowname.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        rowsize.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        rowamount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        rowunit.setCellValueFactory(cellData -> cellData.getValue().unitProperty());

        rowlogno.setCellValueFactory(cellData -> cellData.getValue().lognoProperty());
        rowlogname.setCellValueFactory(cellData -> cellData.getValue().rawnoProperty());
        rowlogwhere.setCellValueFactory(cellData -> cellData.getValue().whereProperty());
        rowlogsize.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        rowlogamount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        rowlogunit.setCellValueFactory(cellData -> cellData.getValue().unitProperty());
        rowlogtime.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        rowlogexplain.setCellValueFactory(cellData -> cellData.getValue().explainProperty());
    }

    @FXML
    private void handleIn() {
        main.showRawLogAddLayout("入库");
        row.setItems(queryUtil.getAllRawData());
        rowlog.setItems(queryUtil.getAllRawLogData());
    }

    @FXML
    private void handleOut() {
        main.showRawLogAddLayout("出库");
        row.setItems(queryUtil.getAllRawData());
        rowlog.setItems(queryUtil.getAllRawLogData());
    }

    @FXML
    private void handleDetail() {
        RawLog selectedRawLog = rowlog.getSelectionModel().getSelectedItem();
        if (selectedRawLog != null) {
            main.showRawLogDetailLayout(selectedRawLog);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(rootStage);
            alert.setTitle("展示细节错误");
            alert.setHeaderText("你没有选中某个记录");
            alert.setContentText("请选择后重新点击修改");

            alert.showAndWait();
        }
    }
}
