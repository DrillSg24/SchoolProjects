package esi.g55130.atl.bmr.model;

public class BMRModel {
    private Person person;

    public BMRModel() {
        this.person = new Person();
    }

    public void buildPersonModel(double height, double weight, int age, LifeStyle lifeStyle, Sex sex) {
        this.person = new Person(height, weight, age, lifeStyle, sex);
    }

    /**
     * Calculates the calories needed by the patient to keep his body mass.
     *
     * @return The calories count needed to keep the body mass.
     */
    public double getBMR() {
        double height = this.person.getHeight();
        double weight = this.person.getWeight();
        int age = this.person.getAge();

        double heightFactor = this.person.getSex().getHeightFactor();
        double weightFactor = this.person.getSex().getWeightFactor();
        double ageFactor = this.person.getSex().getAgeFactor();
        double offset = this.person.getSex().getOffset();

        return heightFactor * height + weightFactor * weight - ageFactor * age + offset;
    }

    /**
     * Calculating the basal metabolic rate, using the formula given.
     *
     * @return The Basal metabolic rate as a double.
     */
    public double getCalories() {
        double bmr = getBMR();
        LifeStyle lifeStyle = this.person.getLifeStyle();
        if (bmr <= 0) {
            throw new IllegalStateException("BMR négatif !" + bmr);
        }
        return lifeStyle.getBmrFactor() * bmr;
    }

    public double getHeight() {
        return this.person.getHeight();
    }

    public double getWeight() {
        return this.person.getWeight();
    }

    public Sex getSex() {
        return this.person.getSex();
    }
}
