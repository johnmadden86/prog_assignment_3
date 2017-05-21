package models;

/**
 * Class to hold details of a member's assessment
 */
public class Assessment {
    private double weight, chest, thigh, upperArm, waist, hips;
    private String comment;
    private Trainer trainer;

    /**
     * Constructor for objects of type assessment
     * @param weight weight
     * @param chest chest measurement
     * @param thigh thigh measurement
     * @param upperArm upper arm measurement
     * @param waist waist measurement
     * @param hips hips measurement
     * @param trainer trainer taking measurement
     * @param comment trainer comment
     */
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

    /**
     * Get the weight taken at assessment
     * @return the weight as a double
     */
    double getWeight() {
        return weight;
    }

    /**
     * Set the weight taken
     * @param weight the weight taken
     */
    private void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Get the chest measurement
     * @return chest measurement taken at assessment
     */
    double getChest() {
        return chest;
    }

    /**
     * Set the chest measurement taken
     * @param chest the chest measurement taken
     */
    private void setChest(double chest) {
        this.chest = chest;
    }

    /**
     * Get the thigh measurement taken
     * @return the thigh measurement taken
     */
    double getThigh() {
        return thigh;
    }

    /**
     * Set the thigh measurement taken
     * @param thigh the thigh measurement taken
     */
    private void setThigh(double thigh) {
        this.thigh = thigh;
    }

    /**
     * Get the upper arm measurement taken
     * @return the upper arm measurement taken
     */
    double getUpperArm() {
        return upperArm;
    }

    /**
     * Set the upper arm measurement taken
     * @param upperArm the upper arm measurement taken
     */
    private void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    /**
     * Get the waist measurement taken
     * @return the waist measurement taken
     */
    double getWaist() {
        return waist;
    }

    /**
     * Set the waist measurement taken
     * @param waist the waist measurement taken
     */
    private void setWaist(double waist) {
        this.waist = waist;
    }

    /**
     * Get the hips measurement taken
     * @return the hips measurement taken
     */
    double getHips() {
        return hips;
    }

    /**
     * Set the hips measurement taken
     * @param hips the hips measurement taken
     */
    private void setHips(double hips) {
        this.hips = hips;
    }

    /**
     * Get the trainer that performed the assessment
     * @return the trainer that performed the assessment
     */
    Trainer getTrainer() {
        return trainer;
    }

    /**
     * Set the trainer that performed the assessment
     * @param trainer the trainer that performed the assessment
     */
    private void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    /**
     * Get the trainer's comment on the assessment
     * @return the trainer's comment on the assessment
     */
    String getComment() {
        return comment;
    }

    /**
     * Set the trainer's comment on the assessment
     * @param comment the trainer's comment on the assessment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * The assessment details as a string
     * @return the assessment details
     */
    public String toString() {
        return  "Weight: " + getWeight() +
                "  Chest: " + getChest() +
                "  Thigh: " + getThigh() +
                "  Upper Arm: " + getUpperArm() +
                "  Waist: " + getWaist() +
                "  Hips: " + getHips() +
                "  Comment: " + getComment() +
                "  Trainer: " + getTrainer().getName();
    }
}
