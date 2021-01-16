package esi.g55130.atl.bmr.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.StageStyle;

/**
 * Class representing the top menu bar and its contents.
 */
public class BMRMenu extends MenuBar {

    /**
     * Constructor for the menu bar and its contents.
     */
    public BMRMenu() {
        MenuItem menuOption_exit = new MenuItem("Exit");
        menuOption_exit.setOnAction(event -> {
            this.callThankYouMessage();
            System.exit(0);
        });
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().add(menuOption_exit);
        this.getMenus().add(fileMenu);
    }

    /**
     * Displays a thank you when the exit button is called.s
     */
    private void callThankYouMessage() {
        Alert thankYouMessage = new Alert(AlertType.INFORMATION,
                "Merci d'avoir utilisé le calculateur BMR-Calories ! ");
        thankYouMessage.setHeaderText("Fin du Programme");
        thankYouMessage.initStyle(StageStyle.UTILITY);
        thankYouMessage.showAndWait();
    }


}
