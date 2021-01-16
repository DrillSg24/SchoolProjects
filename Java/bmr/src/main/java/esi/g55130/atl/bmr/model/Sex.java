package esi.g55130.atl.bmr.model;

/**
 * Enumeration of the different sexes. A sex has a description (value displayed in the buttons),
 * and factors for the bmr formula.
 */

public enum Sex {
    FEMALE("Femme", new double[]{1.8, 9.6, 4.7, 655}), MALE("Homme", new double[]{5, 13.7, 6.8, 66});
    private final String description;
    private final double[] factors;


    Sex(String description, double[] factors) {
        this.description = description;
        this.factors = factors;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Gets the factor to multiply the height by in the bmr formula.
     *
     * @return the heightFactor, a double.
     */
    public double getHeightFactor() {
        return this.factors[0];
    }

    /**
     * Gets the factor to multiply the weight by in the bmr formula.
     *
     * @return the weightFactor, a double.
     */
    public double getWeightFactor() {
        return this.factors[1];
    }

    /**
     * Gets the factor to multiply the age by in the bmr formula.
     *
     * @return the ageFactor, a double.
     */
    public double getAgeFactor() {
        return this.factors[2];
    }

    /**
     * Gets the offset in the bmr formula.
     *
     * @return the offset, a double.
     */
    public double getOffset() {
        return this.factors[3];
    }
}
