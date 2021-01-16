package be.esi.g55130.atl.game2048.main;

import be.esi.g55130.atl.game2048.controller.Controller;
import be.esi.g55130.atl.game2048.controller.ControllerFX;
import be.esi.g55130.atl.game2048.model.Game;
import be.esi.g55130.atl.game2048.model.Model;
import be.esi.g55130.atl.game2048.util.Util;
import be.esi.g55130.atl.game2048.view.InterfaceView;
import be.esi.g55130.atl.game2048.view.View;
import be.esi.g55130.atl.game2048.view.ViewFX;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Main class of the game.
 * Creates a view and game and passes their facades to controller which
 * starts the game.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class Main extends Application {

    /**
     * Main entry point to the program, asks the user for the version he
     * wants to test.
     *
     * @param args Console arguments.
     */
    public static void main(String[] args) {
        int choice = Util.askVersion();
        if (choice == 1) {
            mainFX(args);
        } else if (choice == 0) {
            mainConsole();
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Game will not start, thank you.");
            System.exit(0);
        }
    }

    /**
     * JavaFX entry point, calls the overridden start method.
     *
     * @param args Console Arguments
     */
    public static void mainFX(String[] args) {
        launch(args);
    }

    /**
     * Entry point of the console version of the game.
     */
    public static void mainConsole() {
        InterfaceView view = new View();
        Model game = new Game(view.askPlayerName());
        Controller controller = new Controller(view, game);
        controller.startGame();
    }

    /**
     * Sets up the MVC Components of the JavaFX version.
     *
     * @param primaryStage the primary stage of the game.
     */
    @Override
    public void start(Stage primaryStage) {
        InterfaceView view = new ViewFX(primaryStage);
        Model game = new Game(view.askPlayerName());
        ControllerFX controllerFX = new ControllerFX(view, game);
        view.linkToMC(controllerFX, game);
        controllerFX.startGame();
    }

}
