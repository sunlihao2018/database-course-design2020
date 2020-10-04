package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.util.QueryUtil;

/**
 * @author 天逸流水
 * @date 2020/6/19 10:55
 */
public class BuyAddController {
    @FXML
    private TextField factory;
    @FXML
    private TextField raw;
    @FXML
    private TextField size;
    @FXML
    private TextField amount;
    @FXML
    private TextField unit;
    @FXML
    private TextField price;
    @FXML
    private TextField allprice;

    Stage buyAddStage;
    QueryUtil queryUtil;

    public void setMain(Stage buyAddStage, QueryUtil queryUtil) {
        this.buyAddStage = buyAddStage;
        this.queryUtil = queryUtil;
    }

    @FXML
    private void handleCancel() {
        buyAddStage.close();
    }

    @FXML
    private void handleOK() {
        String factory = this.factory.getText();
        String raw = this.raw.getText();
        String size = this.size.getText();
        float amount = Float.parseFloat(this.amount.getText());
        String unit = this.unit.getText();
        float price = Float.parseFloat(this.price.getText());
        float allprice = Float.parseFloat(this.allprice.getText());
        if (factory == null || raw == null || size == null || this.amount.getText() == null || unit == null || this.price.getText() == null || this.allprice.getText() == null) {
            // 有空的
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(buyAddStage);
            alert.setTitle("采购订单添加");
            alert.setHeaderText("你输入的信息不完善");
            alert.setContentText("请补全信息后重新添加");
            alert.showAndWait();
        } else {
            if (queryUtil.buyAdd(factory, raw, size, amount, unit, price, allprice)) {
                buyAddStage.close();
                queryUtil.getAllBuyData();
            }
        }

    }
}
