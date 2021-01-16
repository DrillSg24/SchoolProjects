package esi.g55130.atl.bmr.model;

/**
 * Enumeration defining the different lifeStyle values.
 * A lifeStyle has a related factor to multiply the basal metabolic rate (BMR) by.
 */
public enum LifeStyle {
    SEDENTARY("Sédentaire", 1.2), A_BIT_ACTIVE("Peu actif", 1.375),
    ACTIVE("Actif", 1.55), ACTIVE_A_LOT("Fort actif", 1.725),
    EXTREMELY_ACTIVE("Extrêmement actif", 1.9);

    private final String description;
    private final double bmrFactor;

    LifeStyle(String description, double bmrFactor) {
        this.description = description;
        this.bmrFactor = bmrFactor;
    }

    /**
     * Gets the factor to multiply the bmr by in the calories formula.
     *
     * @return the bmrFactor, a double.
     */
    public double getBmrFactor() {
        return bmrFactor;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
