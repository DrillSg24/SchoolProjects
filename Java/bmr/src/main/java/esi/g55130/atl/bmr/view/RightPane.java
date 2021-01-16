package esi.g55130.atl.bmr.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;

public class RightPane extends GridPane {
    private final TextField bmrTextField;
    private final TextField caloriesTextField;

    public RightPane() {
        //RIGHT PANE
        Label rightPaneTitle = new Label("Résultats :");
        rightPaneTitle.setUnderline(true);
        //<<<<<<<<<<<<<<<<<<BMR LABEL AND TEXTFIELD>>>>>>>>>>>>>>>
        Label bmrLabel = new Label("BMR ");
        bmrTextField = new TextField();
        bmrTextField.setEditable(false);
        bmrTextField.setPromptText("Résultats du BMR...");
        //<<<<<<<<<<<<<<<<<<CALORIES LABEL AND TEXTFIELD>>>>>>>>>>>>>>>
        Label caloriesLabel = new Label("Calories ");
        caloriesTextField = new TextField();
        caloriesTextField.setEditable(false);
        caloriesTextField.setPromptText("Dépense en calories");
        //<<<<<<<<<<<<<<<POPULATING THE RIGHT PANE>>>>>>>>>>>>>>>>>>>
        this.add(rightPaneTitle, 0, 0, 2, 1);
        this.add(bmrLabel, 0, 1);
        this.add(bmrTextField, 1, 1);
        this.add(caloriesLabel, 0, 2);
        this.add(caloriesTextField, 1, 2);
        this.setVgap(50);
        this.setHgap(5);
    }

    protected void formatResults_Error() {
        bmrTextField.setText("Erreur !");
        bmrTextField.setStyle("-fx-text-fill: #ff0000");
        caloriesTextField.setText("Erreur !");
        caloriesTextField.setStyle("-fx-text-fill: #ff0000");
    }

    protected void formatBMRResult_Success(double bmr) {
        DecimalFormat df = new DecimalFormat("#.##");
        bmrTextField.setText(df.format(bmr));
        bmrTextField.setStyle("-fx-text-fill: green");
    }

    protected void formatCaloriesResults_Success(double calories) {
        DecimalFormat df = new DecimalFormat("#.##");
        caloriesTextField.setText(df.format(calories));
        caloriesTextField.setStyle("-fx-text-fill: green");
    }

    protected void clearAll() {
        this.bmrTextField.clear();
        this.caloriesTextField.clear();
    }

    protected void clearCaloriesTextField() {
        this.caloriesTextField.clear();
    }

}
