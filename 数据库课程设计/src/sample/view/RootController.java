package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Main;

/**
 * @author 天逸流水
 * @date 2020/6/2 23:58
 */
public class RootController {
    @FXML
    private Text user;
    @FXML
    private Button buy;
    @FXML
    private Button personnel;
    @FXML
    private Button raw;
    @FXML
    private Button export;
    @FXML
    private Button production;
    @FXML
    private Button order;
    private Main main;
    private Stage rootStage;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setRootStage(Stage rootStage) {
        this.rootStage = rootStage;
    }

    @FXML
    private void handleBuy() {
        main.showBuyLayout();
    }

    @FXML
    private void handlePersonnel() {
        main.showPersonnelLayout();
    }

    @FXML
    private void handleRaw() {
        main.showRawLayout();
    }

    @FXML
    private void handleProduction() {
        main.showProductionLayout();
    }

    @FXML
    private void handleWork() {

    }

    @FXML
    private void handleOrder() {

    }
}
