package be.esi.g55130.atl.game2048.view;

import javafx.scene.layout.VBox;

/**
 * Class representing the message log on the right of the game.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class MessageLog extends VBox {

    /**
     * Creates and formats the message log, a VBox.
     */
    public MessageLog() {
        super();
        this.setMinSize(400, 600);
        this.setMaxSize(400, 600);
        this.setId("messageLog");
    }

    /**
     * Creates a MessageLabel with the message string, and adds it to the log
     * . If the log already has 10 messages (the max amount supported before
     * visual glitches happen), it deletes the message at the top.
     *
     * @param msg Message to display on the log.
     */
    public void addMessage(String msg) {
        if (this.getChildren().size() == 10) {
            this.getChildren().remove(0);
        }
        MessageLabel newLabel = new MessageLabel(msg);
        newLabel.setId("messageLabel");
        this.getChildren().add(newLabel);
    }

    /**
     * Clears all the messages in the log.
     */
    public void clear() {
        this.getChildren().clear();
    }

    /**
     * Displays a message as an error on the log. Formatted with red font for
     * visual attention.
     *
     * @param errorMessage ErrorMessage to display on the log.
     */
    public void addErrorMessage(String errorMessage) {
        if (this.getChildren().size() == 10) {
            this.getChildren().remove(0);
        }
        MessageLabel newLabel = new MessageLabel(errorMessage);
        newLabel.setId("errorLabel");
        this.getChildren().add(newLabel);
    }
}
