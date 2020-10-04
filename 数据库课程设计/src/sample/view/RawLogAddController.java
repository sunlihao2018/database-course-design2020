package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.util.QueryUtil;

/**
 * @author 天逸流水
 * @date 2020/6/14 1:06
 */
public class RawLogAddController {
    @FXML
    private TextField no;
    @FXML
    private Label where;
    @FXML
    private TextField amount;
    @FXML
    private TextField explain;

    Stage rawLogAddStage;
    QueryUtil queryUtil;

    public void setMain(Stage rawLogAddStage, String where, QueryUtil queryUtil) {
        this.rawLogAddStage = rawLogAddStage;
        this.where.setText(where);
        this.queryUtil = queryUtil;
    }
    @FXML
    private void handleCancel() {
        rawLogAddStage.close();
    }
    @FXML
    private void handleOK() {
        int no = Integer.parseInt(this.no.getText());
        String where = this.where.getText();
        float amount = Float.parseFloat(this.amount.getText());
        String explain = this.explain.getText();
        if (queryUtil.rawLogAdd(no, where, amount, explain)) {
            // System.out.println("成功");
            rawLogAddStage.close();
            queryUtil.getAllRawData();
            queryUtil.getAllRawLogData();
        } else {
            // System.out.println("失败");
        }

    }
}
