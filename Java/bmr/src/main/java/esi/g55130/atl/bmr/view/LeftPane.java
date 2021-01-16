package esi.g55130.atl.bmr.view;

import esi.g55130.atl.bmr.model.LifeStyle;
import esi.g55130.atl.bmr.model.Sex;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.function.UnaryOperator;

public class LeftPane extends GridPane {
    private final HeightText heightText;
    private final TextField ageText;
    private final TextField weightText;
    private final ToggleGroup sexChoice;
    private final ChoiceBox<LifeStyle> lifeStyleChoice;

    public LeftPane() {
        UnaryOperator<TextFormatter.Change> integerFilter_age = change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        Label leftPaneTitle = new Label("Données :");
        leftPaneTitle.setUnderline(true);
        //<<<<<<<<<<HEIGHT LABEL AND TEXTFIELD>>>>>>>>>>
        Label heightLabel = new Label("Taille (cm) ");
        this.heightText = new HeightText();
        //<<<<<<<<<<WEIGHT LABEL AND TEXTFIELD>>>>>>>>>>
        Label weightLabel = new Label("Poids (kg) ");
        weightText = new WeightText();

        //<<<<<<<<<<AGE LABEL AND TEXTFIELD>>>>>>>>>>
        Label ageLabel = new Label("Âge (années) ");
        ageText = new TextField();
        ageText.setPromptText("Âge en années...");
        //Accepting only numbers :
        ageText.setTextFormatter(new TextFormatter<String>(integerFilter_age));
        //<<<<<<<<<<SEX LABEL AND TOGGLE GROUP>>>>>>>>>>
        Label sexLabel = new Label("Sexe");
        sexChoice = new ToggleGroup();
        RadioButton sexFemaleChoice = new RadioButton(Sex.FEMALE.getDescription());
        sexFemaleChoice.setToggleGroup(sexChoice);
        RadioButton sexMaleChoice = new RadioButton(Sex.MALE.getDescription());
        sexMaleChoice.setToggleGroup(sexChoice);
        sexChoice.selectToggle(sexFemaleChoice);
        //<<<<<<<<<<LIFESTYLE LABEL AND CHOICE BOX>>>>>>>>>>
        Label lifeStyleLabel = new Label("Style de vie");
        lifeStyleChoice = new ChoiceBox<LifeStyle>(FXCollections.observableArrayList(LifeStyle.values()));
        lifeStyleChoice.setTooltip(new Tooltip("Indiquez le style de vie"));
        lifeStyleChoice.setValue(LifeStyle.ACTIVE);
        //<<<<<<<<<<<<<<<POPULATING THE LEFT PANE>>>>>>>>>>>>>>>>>>>>>>>
        this.add(heightLabel, 0, 1);
        this.add(leftPaneTitle, 0, 0, 2, 1);
        this.add(heightText, 1, 1, 2, 1);
        this.add(weightLabel, 0, 2);
        this.add(weightText, 1, 2, 2, 1);
        this.add(ageLabel, 0, 3);
        this.add(ageText, 1, 3, 2, 1);
        this.add(sexLabel, 0, 4);
        this.add(sexFemaleChoice, 1, 4, 1, 1);
        this.add(sexMaleChoice, 2, 4, 1, 1);
        this.add(lifeStyleLabel, 0, 5);
        this.add(lifeStyleChoice, 1, 5, 2, 1);
        this.setVgap(50);
        this.setHgap(5);
    }

    protected double getUserHeight() {
        if (this.heightText.getText().isEmpty()) {
            throw new IllegalStateException("Height textField is empty !");
        }
        return Double.parseDouble(this.heightText.getText());
    }

    protected double getUserWeight() {
        if (this.weightText.getText().isEmpty()) {
            throw new IllegalStateException("Weight textField is empty !");
        }
        return Double.parseDouble(this.weightText.getText());
    }

    protected int getUserAge() {
        if (this.ageText.getText().isEmpty()) {
            throw new IllegalStateException("Age textField is empty !");
        }
        return Integer.parseInt(this.ageText.getText());
    }

    protected boolean isAFieldEmpty() {
        return ageText.getText().isEmpty()
                || heightText.getText().isEmpty()
                || weightText.getText().isEmpty();
    }

    protected Sex getUserSex() {
        return ((RadioButton) this.sexChoice.getSelectedToggle()).getText().equals("Femme") ? Sex.FEMALE : Sex.MALE;
    }

    protected LifeStyle getUserLifeStyle() {
        return this.lifeStyleChoice.getValue();
    }

    protected boolean aFieldIsZero() {
        return this.getUserHeight() == 0
                || this.getUserWeight() == 0
                || this.getUserAge() == 0;
    }

    protected void clearAll() {
        heightText.clear();
        weightText.clear();
        ageText.clear();
    }

}
