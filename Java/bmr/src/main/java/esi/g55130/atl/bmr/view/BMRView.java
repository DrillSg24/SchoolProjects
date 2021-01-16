package esi.g55130.atl.bmr.view;

import esi.g55130.atl.bmr.model.BMRModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class BMRView {

    private final Stage primaryStage;

    public BMRView(Stage stage, BMRModel bmrModel) {
        this.primaryStage = stage;
        this.setUpView(bmrModel);
    }

    /**
     * Sets up the different view components.
     *
     * @param bmrModel Model to build view with.
     */
    private void setUpView(BMRModel bmrModel) {
        primaryStage.setTitle("Calcul du BMR");
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(1200);
        primaryStage.setResizable(false);

        //SETTING THE DIFFERENT PANES OF THE APP
        //<<<<<<<MAIN VBOX>>>>>>>>>
        VBox mainBox = new VBox();
        HBox subBox = new HBox(30);
        //<<<<<<<The box with all the numbers>>>>>>>>>
        NumbersBox numbersBox = new NumbersBox(bmrModel);
        //<<<<<<<<< Defining the charts >>>>>>>>>>>
        BMRChart bmrVsWeightChart = new BMRChart_bmrVsWeight();
        BMRChart caloriesVsWeightChart = new BMRChart_calVsWeight();
        BMRChart bmrVsHeightChart = new BMRChart_bmrVsHeight();
        //Registering the charts as observers of the numbers box.
        numbersBox.registerObserver(bmrVsWeightChart);
        numbersBox.registerObserver(caloriesVsWeightChart);
        numbersBox.registerObserver(bmrVsHeightChart);
        //<<<<<<<<< Pane of the different charts >>>>>>>>
        ChartsTabPane tabPane = new ChartsTabPane(bmrVsWeightChart, caloriesVsWeightChart, bmrVsHeightChart);
        //<<<<<<<<MENU>>>>>>>>>>
        BMRMenu menuBar = new BMRMenu();
        //Populating the subBox
        subBox.getChildren().addAll(numbersBox, tabPane);
        subBox.setAlignment(Pos.CENTER);
        //Populating the main box
        mainBox.getChildren().addAll(menuBar, subBox);
        Scene scene = new Scene(mainBox);
        primaryStage.setScene(scene);
    }

    /**
     * Displays a thank you message when program is closed.
     */
    public void callThankYouMessage() {
        Alert thankYouMessage = new Alert(Alert.AlertType.INFORMATION,
                "Merci d'avoir utilisé le calculateur BMR-Calories ! ");
        thankYouMessage.setHeaderText("Fin du Programme");
        thankYouMessage.initStyle(StageStyle.UTILITY);
        thankYouMessage.showAndWait();
    }

    /**
     * Called in the main class to show the stage.
     */
    public void showStage() {
        primaryStage.show();
    }
}
