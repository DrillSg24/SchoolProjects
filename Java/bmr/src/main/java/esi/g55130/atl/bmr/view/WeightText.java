package esi.g55130.atl.bmr.view;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

/**
 * Class representing a TextField for the height in the app.
 * This text field only allows digits and one comma.
 */
public class WeightText extends TextField {

    private int commaCounter;

    /**
     * Filter to not accept any non-integer values in chosen text fields.
     */
    UnaryOperator<TextFormatter.Change> integerFilter = change -> {
        String input = change.getText();
        if (input.matches("[0-9.]*")) {
            if (input.matches("[.]") && commaCounter != 0) {
                return null;
            } else if (input.matches("[.]")) {
                commaCounter++;
            }
            return change;
        }
        return null;
    };

    /**
     * Sets up the new specific characters for the textfield for the weight.
     */
    public WeightText() {
        this.commaCounter = 0;
        this.setPromptText("Poids en kg...");
        //Accepting only numbers :
        this.setTextFormatter(new TextFormatter<String>(integerFilter));
    }

}
