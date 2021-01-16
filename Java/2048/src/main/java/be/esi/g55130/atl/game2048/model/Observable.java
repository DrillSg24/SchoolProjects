package be.esi.g55130.atl.game2048.model;

import be.esi.g55130.atl.game2048.view.Observer;

/**
 * @author Ihab Tazi 55130 - ESI.
 */

public interface Observable {
    /**
     * Register an observer to this observable.
     *
     * @param observer Observer to register to this observable.
     */
    void registerObserver(Observer observer);

    /**
     * Unregisters the observer from this observable.
     *
     * @param observer Observer to unregister.
     */
    void removeObserver(Observer observer);

    /**
     * Notifies the observers that they need to adapt to a change in this
     * observable.
     */
    void notifyObservers();
}
