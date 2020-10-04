package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.model.RawLog;

/**
 * @author 天逸流水
 * @date 2020/6/14 0:39
 */
public class RawLogDetailController {
    @FXML
    private Label rawlog;
    @FXML
    private Label name;
    @FXML
    private Label where;
    @FXML
    private Label size;
    @FXML
    private Label amount;
    @FXML
    private Label unit;
    @FXML
    private Label time;
    @FXML
    private Label explain;

    Stage rawLogDetailStage;

    public void setMain(RawLog rawLog, Stage rawLogDetailStage) {
        this.rawLogDetailStage = rawLogDetailStage;
        rawlog.setText(rawLog.getLogno());
        name.setText(rawLog.getRawno());
        where.setText(rawLog.getWhere());
        size.setText(rawLog.getSize());
        amount.setText(rawLog.getAmount());
        unit.setText(rawLog.getUnit());
        time.setText(rawLog.getTime());
        explain.setText(rawLog.getExplain());
    }

    public void handleOK() {
        rawLogDetailStage.close();
    }
}
