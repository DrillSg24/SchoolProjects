package esi.g55130.atl.bmr.view;

/**
 * Interface defining what an observable must at least have as methods.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public interface Observable {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
