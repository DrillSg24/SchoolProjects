package be.esi.g55130.atl.game2048.view;

import be.esi.g55130.atl.game2048.controller.ControllerFX;
import be.esi.g55130.atl.game2048.model.Direction;
import be.esi.g55130.atl.game2048.model.Model;

/**
 * Interface representing what methods the view class should at least have.
 * Used by the controller using the implementation in View Or ViewFX class.
 * Is also an observer of the model, and changes accordingly when notified.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public interface InterfaceView extends Observer {

    void displayGame(Model game);

    Direction askDirection();

    void displayMessage(String msg);

    void displayHint();

    void displayError(String errorMessage);

    void linkToMC(ControllerFX controllerFX, Model game);

    String askPlayerName();
}
