package esi.g55130.atl.bmr.main;

import esi.g55130.atl.bmr.model.BMRModel;
import esi.g55130.atl.bmr.view.BMRView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Ihab Tazi 55130 - ESI.
 */

public class Main extends Application {
    BMRView view;
    BMRModel model;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * JavaFX Entry point.
     *
     * @param primaryStage The primary stage of the app.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.model = new BMRModel();
        this.view = new BMRView(primaryStage, model);
        this.view.showStage();
    }

    @Override
    public void stop() throws Exception {
        this.view.callThankYouMessage();
        super.stop();
    }
}
