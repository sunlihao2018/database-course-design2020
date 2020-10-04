package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Production;
import sample.model.ProductionLog;
import sample.util.QueryUtil;

/**
 * @author 天逸流水
 * @date 2020/6/14 2:06
 */
public class ProductionController {
    @FXML
    private TableView<Production> pro;
    @FXML
    private TableColumn<Production, String> prono;
    @FXML
    private TableColumn<Production, String> proname;
    @FXML
    private TableColumn<Production, String> proamount;
    @FXML
    private TableColumn<Production, String> prounit;
    @FXML
    private TableView<ProductionLog> log;
    @FXML
    private TableColumn<ProductionLog, String> logno;
    @FXML
    private TableColumn<ProductionLog, String> logname;
    @FXML
    private TableColumn<ProductionLog, String> logwhere;
    @FXML
    private TableColumn<ProductionLog, String> logamount;
    @FXML
    private TableColumn<ProductionLog, String> logunit;
    @FXML
    private TableColumn<ProductionLog, String> logtime;
    @FXML
    private TableColumn<ProductionLog, String> logexplain;

    private Main main;
    private Stage rootStage;
    private QueryUtil queryUtil;

    public void setMain(Main main, Stage rootStage, QueryUtil queryUtil) {
        this.main = main;
        this.rootStage = rootStage;
        this.queryUtil = queryUtil;
        pro.setItems(queryUtil.getAllProductionData());
        log.setItems(queryUtil.getAllProductionLogData());
    }

    @FXML
    public void initialize() {
        prono.setCellValueFactory(cellData -> cellData.getValue().noProperty());
        proname.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        proamount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        prounit.setCellValueFactory(cellData -> cellData.getValue().unitProperty());

        logno.setCellValueFactory(cellData -> cellData.getValue().noProperty());
        logname.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        logwhere.setCellValueFactory(cellData -> cellData.getValue().whereProperty());
        logamount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        logunit.setCellValueFactory(cellData -> cellData.getValue().unitProperty());
        logtime.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        logexplain.setCellValueFactory(cellData -> cellData.getValue().explainProperty());
    }

    @FXML
    private void handleIn() {
        main.showProductionAddLayout("入库");
        pro.setItems(queryUtil.getAllProductionData());
        log.setItems(queryUtil.getAllProductionLogData());
    }

    @FXML
    private void handleOut() {
        main.showProductionAddLayout("出库");
        pro.setItems(queryUtil.getAllProductionData());
        log.setItems(queryUtil.getAllProductionLogData());
    }
}
