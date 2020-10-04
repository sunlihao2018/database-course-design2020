package sample.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.model.Buy;
import sample.util.QueryUtil;

import java.awt.*;

/**
 * @author 天逸流水
 * @date 2020/6/19 9:56
 */
public class BuyController {
    @FXML
    private TextField str;
    @FXML
    private TableView<Buy> buyTableUp;
    @FXML
    private TableColumn<Buy, String> noColumnUp;
    @FXML
    private TableColumn<Buy, String> factoryColumnUp;
    @FXML
    private TableColumn<Buy, String> rawColumnUp;
    @FXML
    private TableColumn<Buy, String> sizeColumnUp;
    @FXML
    private TableColumn<Buy, String> amountColumnUp;
    @FXML
    private TableColumn<Buy, String> unitColumnUp;
    @FXML
    private TableColumn<Buy, String> priceColumnUp;
    @FXML
    private TableColumn<Buy, String> allpriceColumnUp;
    @FXML
    private TableColumn<Buy, String> timepriceColumnUp;

    @FXML
    private TableView<Buy> buyTableDown;
    @FXML
    private TableColumn<Buy, String> noColumnDown;
    @FXML
    private TableColumn<Buy, String> factoryColumnDown;
    @FXML
    private TableColumn<Buy, String> rawColumnDown;
    @FXML
    private TableColumn<Buy, String> sizeColumnDown;
    @FXML
    private TableColumn<Buy, String> amountColumnDown;
    @FXML
    private TableColumn<Buy, String> unitColumnDown;
    @FXML
    private TableColumn<Buy, String> priceColumnDown;
    @FXML
    private TableColumn<Buy, String> allpriceColumnDown;
    @FXML
    private TableColumn<Buy, String> timepriceColumnDown;

    private Main main;
    private Stage rootStage;
    private QueryUtil queryUtil;
    private int flag = 0;

    public void setMain(Main main, Stage rootStage, QueryUtil queryUtil) {
        this.main = main;
        this.rootStage = rootStage;
        this.queryUtil = queryUtil;
        // 显示数据
        buyTableUp.setItems(queryUtil.getAllBuyData());
    }

    public void initialize() {
        noColumnUp.setCellValueFactory(cellData -> cellData.getValue().noProperty());
        factoryColumnUp.setCellValueFactory(cellData -> cellData.getValue().factoryProperty());
        rawColumnUp.setCellValueFactory(cellData -> cellData.getValue().rawProperty());
        sizeColumnUp.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        amountColumnUp.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        unitColumnUp.setCellValueFactory(cellData -> cellData.getValue().unitProperty());
        priceColumnUp.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        allpriceColumnUp.setCellValueFactory(cellData -> cellData.getValue().allpriceProperty());
        timepriceColumnUp.setCellValueFactory(cellData -> cellData.getValue().timeProperty());

        buyTableUp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Buy>() {
            @Override
            public void changed(ObservableValue<? extends Buy> observable, Buy oldValue, Buy newValue) {
                flag = 1;
            }
        });

        buyTableDown.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Buy>() {
            @Override
            public void changed(ObservableValue<? extends Buy> observable, Buy oldValue, Buy newValue) {
                flag = 2;
            }
        });
    }

    @FXML
    private void handleAdd() {
        // 显示添加订单
        main.showBuyAddLayout();
    }
    @FXML
    private void handleDelete() {
        Buy selectedBuy;
        if (flag == 1) {
            selectedBuy = buyTableUp.getSelectionModel().getSelectedItem();
        } else {
            selectedBuy = buyTableDown.getSelectionModel().getSelectedItem();
        }
        if (selectedBuy != null) {
            // 显示删除界面
            main.showBuyDeleteLayout(selectedBuy);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(rootStage);
            alert.setTitle("删除错误");
            alert.setHeaderText("你没有选中某个购买订单");
            alert.setContentText("请选择后重新点击删除");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleFind() {
        noColumnDown.setCellValueFactory(cellData -> cellData.getValue().noProperty());
        factoryColumnDown.setCellValueFactory(cellData -> cellData.getValue().factoryProperty());
        rawColumnDown.setCellValueFactory(cellData -> cellData.getValue().rawProperty());
        sizeColumnDown.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        amountColumnDown.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        unitColumnDown.setCellValueFactory(cellData -> cellData.getValue().unitProperty());
        priceColumnDown.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        allpriceColumnDown.setCellValueFactory(cellData -> cellData.getValue().allpriceProperty());
        timepriceColumnDown.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        // 显示数据
        buyTableDown.setItems(queryUtil.getFindBuyData(str.getText()));
    }
}
