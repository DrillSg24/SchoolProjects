package be.esi.g55130.atl.game2048.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class representing a message that is to be added to the right log of the
 * game.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class MessageLabel extends Label {

    /**
     * Creates a label with the current time, and the message given in
     * parameters.
     *
     * @param message The message to show in the label.
     */
    public MessageLabel(String message) {
        super();
        Date time = new Date(System.currentTimeMillis());
        String currentTime = new SimpleDateFormat("HH:mm:ss")
                .format(time);
        this.setText(currentTime + " - " + message);
        this.setMinSize(398, 58);
        this.setMaxSize(400, 60);
        this.setAlignment(Pos.CENTER);
    }
}
