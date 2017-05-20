package models;

import java.util.Date;

/**
 * Created by John on 24/04/2017.
 */
public class Assessment {
    private double weight, chest, thigh, upperArm, waist, hips;
    private String comment;
    private Trainer trainer;

    public Assessment(double weight, double chest, double thigh, double upperArm,
                      double waist, double hips, Trainer trainer, String comment) {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.trainer = trainer;
        this.comment = comment;
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

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String getComment() {
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
                "  Comment: " + getComment() +
                "  Trainer: " + trainer.getName();
    }
}
