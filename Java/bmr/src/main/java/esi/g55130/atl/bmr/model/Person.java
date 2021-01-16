package esi.g55130.atl.bmr.model;

public class Person {
    private final LifeStyle lifeStyle;
    private final Sex sex;
    private double height, weight;
    private int age;

    public Person() {
        this.lifeStyle = LifeStyle.ACTIVE;
        this.sex = Sex.FEMALE;
    }

    public Person(double height, double weight, int age, LifeStyle lifeStyle, Sex sex) {
        if (sex == null) {
            throw new IllegalArgumentException("Sex specified is invalid ! ");
        } else if (lifeStyle == null) {
            throw new IllegalArgumentException("LifeStyle specified is invalid ! ");
        } else if (height < 0 || weight < 0 || age < 0) {
            throw new IllegalArgumentException(
                    "Params must be strictly positive :" + height + ", " + weight + ", " + age);
        } else if (height == 0 || weight == 0 || age == 0) {
            throw new IllegalStateException(
                    "Height : " + height + ", weight : " + weight + ", age : " + age + " cannot be equal to zero");
        }
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.lifeStyle = lifeStyle;
        this.sex = sex;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public LifeStyle getLifeStyle() {
        return lifeStyle;
    }

    public Sex getSex() {
        return sex;
    }
}
