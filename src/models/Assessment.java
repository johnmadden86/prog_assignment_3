package models;

/**
 * Created by John on 24/04/2017.
 */
public class Assessment {
    private double weight;
    private double chest;
    private double thigh;
    private double upperArm;
    private double waist;
    private Trainer trainer;

    public Assessment(double weight, double chest, double thigh, double upperArm, double waist, Trainer trainer) {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.trainer = trainer;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getUpperArm() {
        return upperArm;
    }

    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "weight=" + weight +
                ", chest=" + chest +
                ", thigh=" + thigh +
                ", upperArm=" + upperArm +
                ", waist=" + waist +
                ", trainer=" + trainer +
                '}';
    }
}
