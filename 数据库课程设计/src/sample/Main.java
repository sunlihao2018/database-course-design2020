package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Buy;
import sample.model.Person;
import sample.model.RawLog;
import sample.util.DatabaseUtil;
import sample.util.QueryUtil;
import sample.view.*;

import java.io.IOException;

public class Main extends Application {

    private Stage loginStage;
    private Stage rootStage;
    private BorderPane rootLayout;
    private DatabaseUtil databaseUtil;
    private QueryUtil queryUtil;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage loginStage) {
        this.loginStage = loginStage;
        this.loginStage.setTitle("进出库管理系统");
        this.databaseUtil = new DatabaseUtil();
        this.queryUtil = new QueryUtil(this.databaseUtil.getConn());
        showLoginLayout();
    }

    @Override
    public void stop() {
        databaseUtil.close();
    }

    public QueryUtil getQueryUtil() {
        return queryUtil;
    }

    public void showLoginLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Login.fxml"));
            AnchorPane loginLayout = (AnchorPane) loader.load();

            LoginController controller = loader.getController();
            controller.setMain(this, databaseUtil);

            // Show the scene containing the root layout.
            Scene scene = new Scene(loginLayout);
            loginStage.setScene(scene);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Root.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();
            this.rootLayout = rootLayout;

            // Create the root Stage.
            Stage rootStage = new Stage();
            rootStage.setTitle("进出货物管理系统");
            Scene scene = new Scene(rootLayout);
            rootStage.setScene(scene);
            this.rootStage = rootStage;


            RootController controller = loader.getController();
            // controller.setRootStage(rootStage);
            controller.setMain(this);


            // Show the dialog and wait until the user closes it
            loginStage.close();
            // primaryStage.setScene();
            rootStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonnelLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Personnel.fxml"));
            BorderPane personnelLayout = (BorderPane) loader.load();

            rootLayout.setCenter(personnelLayout);

            PersonnelController personnelController = loader.getController();
            personnelController.setMain(this, databaseUtil, queryUtil, rootStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonAddLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/PersonAdd.fxml"));
            AnchorPane personAddLayout = (AnchorPane) loader.load();

            Stage personAddStage = new Stage();
            personAddStage.setTitle("人事添加");
            personAddStage.initModality(Modality.WINDOW_MODAL);
            personAddStage.initOwner(rootStage);
            Scene scene = new Scene(personAddLayout);
            personAddStage.setScene(scene);

            PersonAddController personAddController = loader.getController();
            personAddController.setMain(this, personAddStage);

            personAddStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonEditLayout(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/PersonEdit.fxml"));
            AnchorPane personEditLayout = (AnchorPane) loader.load();

            Stage personEditStage = new Stage();
            personEditStage.setTitle("人事修改");
            personEditStage.initModality(Modality.WINDOW_MODAL);
            personEditStage.initOwner(rootStage);
            Scene scene = new Scene(personEditLayout);
            personEditStage.setScene(scene);

            PersonEditController personEditController = loader.getController();
            personEditController.setMain(this, personEditStage);
            personEditController.setPerson(person);

            personEditStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonDeleteLayout(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/PersonDelete.fxml"));
            AnchorPane personDeleteLayout = (AnchorPane) loader.load();

            Stage personDeleteStage = new Stage();
            personDeleteStage.setTitle("删除");
            personDeleteStage.initModality(Modality.WINDOW_MODAL);
            personDeleteStage.initOwner(rootStage);
            Scene scene = new Scene(personDeleteLayout);
            personDeleteStage.setScene(scene);

            PersonDeleteController personDeleteController = loader.getController();
            personDeleteController.setMain(this, personDeleteStage);
            personDeleteController.setPerson(person);

            personDeleteStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRawLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Raw.fxml"));
            BorderPane rawLayout = (BorderPane) loader.load();

            rootLayout.setCenter(rawLayout);

            RawController rawController = loader.getController();
            rawController.setMain(this, rootStage, queryUtil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRawLogDetailLayout(RawLog rawLog) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RawLogDetail.fxml"));
            AnchorPane rawLogDetailLayout = (AnchorPane) loader.load();

            Stage rawLogDetailStage = new Stage();
            rawLogDetailStage.setTitle("入库日志详细记录");
            rawLogDetailStage.initModality(Modality.WINDOW_MODAL);
            rawLogDetailStage.initOwner(rootStage);
            Scene scene = new Scene(rawLogDetailLayout);
            rawLogDetailStage.setScene(scene);

            RawLogDetailController rawLogDetailController = loader.getController();
            rawLogDetailController.setMain(rawLog, rawLogDetailStage);

            rawLogDetailStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRawLogAddLayout(String where) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RawLogAdd.fxml"));
            AnchorPane rawLogAddLayout = (AnchorPane) loader.load();

            Stage rawLogAddStage = new Stage();
            rawLogAddStage.setTitle("日志记录");
            rawLogAddStage.initModality(Modality.WINDOW_MODAL);
            rawLogAddStage.initOwner(rootStage);
            Scene scene = new Scene(rawLogAddLayout);
            rawLogAddStage.setScene(scene);

            RawLogAddController rawLogAddController = loader.getController();
            rawLogAddController.setMain(rawLogAddStage, where, queryUtil);

            rawLogAddStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProductionLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Production.fxml"));
            BorderPane productionLayout = (BorderPane) loader.load();

            rootLayout.setCenter(productionLayout);

            ProductionController productionController = loader.getController();
            productionController.setMain(this, rootStage, queryUtil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showProductionAddLayout(String where) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ProductionLogAdd.fxml"));
            AnchorPane productionLogAddLayout = (AnchorPane) loader.load();

            Stage productionLogAddStage = new Stage();
            productionLogAddStage.setTitle("产品日志记录");
            productionLogAddStage.initModality(Modality.WINDOW_MODAL);
            productionLogAddStage.initOwner(rootStage);
            Scene scene = new Scene(productionLogAddLayout);
            productionLogAddStage.setScene(scene);

            ProductionLogAddController productionLogAddController = loader.getController();
            productionLogAddController.setMain(productionLogAddStage, where, queryUtil);

            productionLogAddStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBuyLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Buy.fxml"));
            BorderPane buyLayout = (BorderPane) loader.load();

            rootLayout.setCenter(buyLayout);

            BuyController buyController = loader.getController();
            buyController.setMain(this, rootStage, queryUtil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBuyAddLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/BuyAdd.fxml"));
            AnchorPane buyAddLayout = (AnchorPane) loader.load();

            Stage buyAddStage = new Stage();
            buyAddStage.setTitle("采购订单添加");
            buyAddStage.initModality(Modality.WINDOW_MODAL);
            buyAddStage.initOwner(rootStage);
            Scene scene = new Scene(buyAddLayout);
            buyAddStage.setScene(scene);

            BuyAddController buyAddController = loader.getController();
            buyAddController.setMain( buyAddStage, queryUtil);

            buyAddStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBuyDeleteLayout(Buy buy) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/BuyDelete.fxml"));
            AnchorPane buyDeleteLayout = (AnchorPane) loader.load();

            Stage buyDeleteStage = new Stage();
            buyDeleteStage.setTitle("删除");
            buyDeleteStage.initModality(Modality.WINDOW_MODAL);
            buyDeleteStage.initOwner(rootStage);
            Scene scene = new Scene(buyDeleteLayout);
            buyDeleteStage.setScene(scene);

            BuyDeleteController buyDeleteController = loader.getController();
            buyDeleteController.setMain(buyDeleteStage, queryUtil, buy);

            buyDeleteStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

