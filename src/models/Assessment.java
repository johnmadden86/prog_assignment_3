package models;

public class Assessment {
    private double weight, chest, thigh, upperArm, waist, hips;
    private String comment;
    private Trainer trainer;

    public Assessment(double weight, double chest, double thigh, double upperArm,
                      double waist, double hips, Trainer trainer, String comment) {
        setWeight(weight);
        setChest(chest);
        setThigh(thigh);
        setUpperArm(upperArm);
        setWaist(waist);
        setHips(hips);
        setTrainer(trainer);
        setComment(comment);
    }

    double getWeight() {
        return weight;
    }

    private void setWeight(double weight) {
        this.weight = weight;
    }

    double getChest() {
        return chest;
    }

    private void setChest(double chest) {
        this.chest = chest;
    }

    double getThigh() {
        return thigh;
    }

    private void setThigh(double thigh) {
        this.thigh = thigh;
    }

    double getUpperArm() {
        return upperArm;
    }

    private void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    double getWaist() {
        return waist;
    }

    private void setWaist(double waist) {
        this.waist = waist;
    }

    double getHips() {
        return hips;
    }

    private void setHips(double hips) {
        this.hips = hips;
    }

    Trainer getTrainer() {
        return trainer;
    }

    private void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString() {
        return  "Weight: " + getWeight() +
                "  Chest: " + getChest() +
                "  Thigh: " + getThigh() +
                "  Upper Arm: " + getUpperArm() +
                "  Waist: " + getWaist() +
                "  Hips: " + getHips() +
                "  Comment: " + getComment() +
                "  Trainer: " + trainer.getName();
    }
}
