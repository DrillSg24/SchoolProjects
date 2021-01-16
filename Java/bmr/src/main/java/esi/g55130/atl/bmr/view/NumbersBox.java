package esi.g55130.atl.bmr.view;

import esi.g55130.atl.bmr.model.BMRModel;
import esi.g55130.atl.bmr.model.LifeStyle;
import esi.g55130.atl.bmr.model.Sex;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.util.ArrayList;

/**
 * @author Ihab Tazi 55130 - ESI.
 */

public class NumbersBox extends VBox implements Observable {
    private final RightPane rightPane;
    private final LeftPane leftPane;
    private final BMRModel bmrModel;
    private final ArrayList<Observer> observers;


    public NumbersBox(BMRModel bmrModel) {
        this.observers = new ArrayList<>();
        this.bmrModel = bmrModel;
        this.setSpacing(15);
        //<<<<<<<SUB HBOX>>>>>>>
        HBox numbersSubBox = new HBox();
        numbersSubBox.setPadding(new Insets(40));
        numbersSubBox.setSpacing(75);

        //<<<<<<<<<LEFT AND RIGHT PANE>>>>>>>>>
        rightPane = new RightPane();
        leftPane = new LeftPane();

        //<<<<<<<<BUTTONS>>>>>>>>>>
        Button calculateButton = new Button("Calculer BMR");
        calculateButton.setMinWidth(250);
        calculateButton.setMaxWidth(600);

        Button clearButton = new Button("Effacer les données");
        clearButton.setMinWidth(250);
        clearButton.setMaxWidth(600);

        //Button Handling
        calculateButton.setOnAction(this::calculateButtonHandler);
        clearButton.setOnAction(this::clearButtonHandler);
        //<<<<<<<<<<<POPULATING THE BOXES>>>>>>>>>>>>
        numbersSubBox.getChildren().addAll(leftPane, rightPane);
        this.getChildren().addAll(numbersSubBox, calculateButton, clearButton);
        this.setOnKeyPressed(this::enterPressed);
    }

    /**
     * Defines the behaviour when the calculate button is clicked.
     *
     * @param event Event to handle.
     */
    private void calculateButtonHandler(ActionEvent event) {
        if (this.leftPane.isAFieldEmpty()) {
            this.rightPane.formatResults_Error();
        } else {
            double height = leftPane.getUserHeight();
            double weight = leftPane.getUserWeight();
            int age = leftPane.getUserAge();
            Sex sex = leftPane.getUserSex();
            LifeStyle lifeStyle = leftPane.getUserLifeStyle();
            try {
                bmrModel.buildPersonModel(height, weight, age, lifeStyle, sex);
                double bmr = this.bmrModel.getBMR();
                if (bmr > 0) {
                    notifyObservers();
                }
                this.rightPane.formatBMRResult_Success(bmr);
                try {
                    double calories = this.bmrModel.getCalories();
                    this.rightPane.formatCaloriesResults_Success(calories);
                } catch (IllegalStateException negativeBMRException) {
                    this.callNegativeBMRAlert();
                }
            } catch (IllegalStateException aFieldIsZeroException) {
                this.callZeroValueAlert();
            }
        }
    }

    /**
     * Defines the behaviour when the clear button is clicked.
     *
     * @param event Event to handle.
     */
    private void clearButtonHandler(ActionEvent event) {
        this.leftPane.clearAll();
        this.rightPane.clearAll();
    }


    /**
     * Binds the enter button to the calculate method.
     *
     * @param keyEvent Keyboard Event.
     */
    private void enterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            calculateButtonHandler(new ActionEvent());
        }
    }

    /**
     * Shows an error message when the user invalidly gives a null value for either the age, height or weight.
     */
    public void callZeroValueAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR,
                "La taille, le poids et l'âge ne peuvent pas être nuls ! ");
        alert.setHeaderText("Erreur d'entrée utilisateur");
        alert.initStyle(StageStyle.UTILITY);
        alert.show();
    }

    /**
     * Shows an error message when the calculated BMR results in a negative value,
     * the BMR result is still displayed, but the calories cannot be calculated.
     */
    public void callNegativeBMRAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR,
                "Le BMR calculé est négatif ! Impossible de calculer les calories...");
        alert.setHeaderText("Atteint les limites de la formule");
        alert.initStyle(StageStyle.UTILITY);
        alert.show();
        this.rightPane.clearCaloriesTextField();
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer Observer to add.
     */
    @Override
    public void registerObserver(Observer observer) {
        if (!this.observers.contains(observer))
            this.observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer Observer to remove.
     */
    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * Notifies the observers and tells them to update themselves accordingly when called.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer :
                this.observers) {
            observer.update(this.bmrModel);
        }
    }
}
