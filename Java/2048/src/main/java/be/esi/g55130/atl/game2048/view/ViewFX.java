package be.esi.g55130.atl.game2048.view;

import be.esi.g55130.atl.game2048.controller.ControllerFX;
import be.esi.g55130.atl.game2048.model.Direction;
import be.esi.g55130.atl.game2048.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;


/**
 * @author Ihab Tazi 55130 - ESI.
 */

public class ViewFX implements InterfaceView {
    private final Stage primaryStage;
    private GridPane mainGrid;
    private VBox tilesView;
    private MessageLog log;
    private ScoreLabel scoreLabel;
    private ControllerFX controller;
    private GridPane buttonBox;

    /**
     * Constructs the view on the primaryStage given as parameter.
     * Intended for the stage given in the main class.
     *
     * @param primaryStage Stage to build the view on.
     */
    public ViewFX(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("2048 Game - 55130 Ihab TAZI");
        this.primaryStage.setResizable(false);
        this.primaryStage.setMinWidth(1000);
        this.primaryStage.setMinHeight(750);
    }

    /**
     * Called to link this view to the controller in parameter (used for the
     * key presses and restart button handlers, and build the game board for
     * the first time using the game as parameter.
     *
     * @param controllerFX Controller to link to.
     * @param game         Game to represent in the view.
     */
    @Override
    public void linkToMC(ControllerFX controllerFX, Model game) {
        this.controller = controllerFX;
        this.setUpFXView(game);
    }

    @Override
    public String askPlayerName() {
        String response = JOptionPane.showInputDialog("What is your name ? ");
        if (response == null) {
            JOptionPane.showMessageDialog(null,
                    "Game will not start, thank you.");
            System.exit(0);
        }
        return response.isBlank() ? "Nemo" : response;
    }

    /**
     * Sets up the different nodes of the view using the game as model.
     *
     * @param game Game to model the view on.
     */
    private void setUpFXView(Model game) {
        //Main Grid Pane, contains all the other JavaFX nodes.
        mainGrid = new GridPane();
        mainGrid.setOnKeyPressed(controller::keyPressed);
        mainGrid.setVgap(2);
        mainGrid.setHgap(2);
        mainGrid.setAlignment(Pos.CENTER);
        mainGrid.requestFocus();
        //Game Board
        this.buildBoard(game);
        //Buttons
        buttonBox = new GridPane();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setHgap(5);
        buttonBox.setVgap(10);
        this.setUpRestartButton();
        this.setUpUndoButton();
        this.setUpHintButton();
        mainGrid.add(buttonBox, 1, 1);
        //Score Label
        scoreLabel = new ScoreLabel(game.getPlayerName(),
                game.getPlayerScore());
        mainGrid.add(scoreLabel, 0, 1);
        //Message log
        log = new MessageLog();
        log.addMessage("Welcome to 2048 " + game.getPlayerName() + " !");
        mainGrid.add(log, 1, 0);
        //Setting the JavaFX window with the pane.
        Scene scene = new Scene(mainGrid);
        scene.getStylesheets().add("/ConcertOne-Regular.ttf");
        scene.getStylesheets().add("/style.css");
        this.primaryStage.setScene(scene);
        this.mainGrid.requestFocus();
    }


    /**
     * Build the VBox containing four HBox-s representing each a row of 4
     * tiles, using the tiles it gets from the game.
     *
     * @param game Game to get the tiles from.
     */
    private void buildBoard(Model game) {
        //Deleting the existing board from the view
        mainGrid.getChildren().remove(tilesView);
        //Rebuilding the board.
        tilesView = new VBox();
        tilesView.setId("boardBox");
        for (int i = 0; i < 4; i++) {
            HBox newLineBox = new HBox();
            for (int j = 0; j < 4; j++) {
                newLineBox.getChildren()
                        .add(new TileView(game.getTileAt(i, j)
                                , game.getGameStatus()));
            }
            tilesView.getChildren().add(newLineBox);
        }
        //Re-adding the board to the view.
        mainGrid.add(tilesView, 0, 0);
    }

    /**
     * Method that displays the current state of the game, as in the current
     * tiles and their values, as well as the current score.
     *
     * @param game Game to display the state of.
     */
    @Override
    public void displayGame(Model game) {
        this.buildBoard(game);
        this.primaryStage.show();
    }

    /**
     * Not implemented in the JavaFX version. Never call this method with the
     * JavaFX version.
     *
     * @return null.
     */
    @Override
    public Direction askDirection() {
        //Unimplemented in the JavaFX version.
        return null;
    }

    /**
     * Displays a message by adding it to the message log on the right.
     *
     * @param msg Message to display.
     */
    @Override
    public void displayMessage(String msg) {
        this.log.addMessage(msg);
    }

    /**
     * Displays an info box containing information on how to play the game.
     */
    @Override
    public void displayHint() {
        Alert thankYouMessage = new Alert(Alert.AlertType.INFORMATION,
                "Game commands :  \n" +
                        "UP : up, 8 or Z keys\n" +
                        "LEFT : left, 4 or Q keys\n" +
                        "DOWN : down, 2 or S keys\n" +
                        "RIGHT : right, 6 or D keys\n" +
                        "\nThe aim of the game is to chain tiles together " +
                        "until reaching the target of 2048\n"
                        + "\n You can only undo the last valid move ONCE per " +
                        "move, so use the undo button wisely");
        thankYouMessage.setHeaderText("2048 HELP & COMMANDS");
        thankYouMessage.initStyle(StageStyle.DECORATED);
        thankYouMessage.showAndWait();
    }

    /**
     * Displays an error on the message log.
     *
     * @param errorMessage Error message to display.
     */
    @Override
    public void displayError(String errorMessage) {
        this.log.addErrorMessage(errorMessage);
    }

    /**
     * Method called when the game notifies it has changed.
     *
     * @param game Game to update according to.
     */
    @Override
    public void update(Model game) {
        this.displayGame(game);
        this.scoreLabel.updateScore(game.getPlayerScore());
    }

    /**
     * Sets up the restart button and adds it to the buttonBox.
     */
    private void setUpRestartButton() {
        Button restartButton = new Button("Restart");
        restartButton.setOnAction(this.controller::restartButtonHandler);
        buttonBox.add(restartButton, 0, 0);
        restartButton.setId("restartButton");
    }

    /**
     * Sets up the hint button and adds it to the buttonBox.
     */
    private void setUpHintButton() {
        Button hintButton = new Button("Help-Commands");
        hintButton.setOnAction(this::hintButtonHandler);
        hintButton.setId("hintButton");
        buttonBox.add(hintButton, 0, 1, 2, 1);
    }

    /**
     * Sets up the undo button and adds it to the buttonBox.
     */
    private void setUpUndoButton() {
        Button undoButton = new Button("Undo");
        undoButton.setOnAction(this.controller::undoButtonHandler);
        undoButton.setId("undoButton");
        buttonBox.add(undoButton, 1, 0);
    }

    /**
     * Handles the event of a click on the hint button.
     *
     * @param event Event to handle.
     */
    private void hintButtonHandler(ActionEvent event) {
        this.displayHint();
    }

    /**
     * Used by the controller to clear the messageLog.
     */
    public void clearLog() {
        this.log.clear();
    }

    /**
     * Updates the score displayed in the view.
     *
     * @param newScore The new score to display.
     */
    public void updateScoreLabel(int newScore) {
        this.scoreLabel.updateScore(newScore);
    }

    /**
     * Requests the focus from the buttons to the mainGrid, called by the
     * button handlers at the end of the action.
     */
    public void requestGridFocus() {
        this.mainGrid.requestFocus();
    }
}

