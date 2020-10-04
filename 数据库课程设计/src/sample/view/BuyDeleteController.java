package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.model.Buy;
import sample.util.QueryUtil;

/**
 * @author 天逸流水
 * @date 2020/6/19 10:57
 */
public class BuyDeleteController {
    @FXML
    private Label no;
    @FXML
    private Label factory;
    @FXML
    private Label raw;
    @FXML
    private Label size;
    @FXML
    private Label amount;
    @FXML
    private Label unit;
    @FXML
    private Label price;
    @FXML
    private Label allprice;
    @FXML
    private Label time;

    Stage buyDeleteStage;
    QueryUtil queryUtil;
    Buy buy;

    public void setMain(Stage buyDeleteStage, QueryUtil queryUtil, Buy buy) {
        this.buyDeleteStage = buyDeleteStage;
        this.queryUtil = queryUtil;
        this.buy = buy;
    }

    @FXML
    private void handleCancel() {
        buyDeleteStage.close();
    }
    @FXML
    private void handleOK() {
        // 删除
        queryUtil.buyDelete(buy);
        buyDeleteStage.close();
        queryUtil.getAllBuyData();
    }
}
