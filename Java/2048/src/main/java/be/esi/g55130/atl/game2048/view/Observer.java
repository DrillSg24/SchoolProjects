package be.esi.g55130.atl.game2048.view;

import be.esi.g55130.atl.game2048.model.Model;

/**
 * Interface representing an object that observes a model, and has a method
 * to update itself accordingly, the update() is implemented differently in
 * the FX and the console versions.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public interface Observer {
    void update(Model game);
}
