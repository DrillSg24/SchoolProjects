package esi.g55130.atl.bmr.view;

import esi.g55130.atl.bmr.model.BMRModel;

/**
 * Interface defining what an observer should at least have as methods.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public interface Observer {
    void update(BMRModel bmrModel);
}
