package models;

import java.util.*;

import static utils.Analytics.toTwoDecimalPlaces;
import static utils.Utilities.printShortDate;

/**
 * abstract class for the object of type member
 */
public abstract class Member extends Person {
    private double height, startingWeight;
    private String chosenPackage;
    private final HashMap<Date,Assessment> assessments;

    /**
     * Constructor for objects of type member
     * @param email
     * @param name
     * @param address
     * @param gender
     * @param height
     * @param startingWeight
     * @param chosenPackage
     */
    Member(String email, String name, String address, String gender,
           double height, double startingWeight, String chosenPackage) {

        super(email, name, address, gender);
        setHeight(height);
        setStartingWeight(startingWeight);
        setChosenPackage(chosenPackage);
        assessments = new HashMap<>();
    }

    public void addAssessment(Date date, Assessment assessment){
        getAssessments().put(date, assessment);
    }

    public HashMap<Date, Assessment> getAssessments() {
        return assessments;
    }

    Assessment getAssessment(Date date) {
        return getAssessments().get(date);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height < 1) {
            height = 1;
        } else if (height > 3) {
            height = 3;
        }
        this.height = toTwoDecimalPlaces(height);
    }

    double getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(double startingWeight) {
        if (startingWeight < 35) {
            startingWeight = 35;
        } else if (startingWeight > 250) {
            startingWeight = 250;
        }
        this.startingWeight = toTwoDecimalPlaces(startingWeight);
    }

    public double getCurrentWeight() {
        double weight = getStartingWeight();
        if (latestAssessment() != null) {
            //noinspection ConstantConditions
            weight = latestAssessment().getWeight();
        }
        return weight;
    }

    String getChosenPackage() {
        return chosenPackage;
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    public String toString() {
        return super.toString() +
                "\nHeight: " + getHeight() +
                "\nStarting Weight: " + getStartingWeight() +
                "\nCurrent Weight: " + getCurrentWeight() +
                "\nChosen Package: " + getChosenPackage();
    }

    Assessment latestAssessment() {
        if (getAssessments().isEmpty()) {
            return null;
        } else {
            return getAssessments().get(sortedAssessmentDates().first());
        }
    }

    SortedSet<Date> sortedAssessmentDates() {
        SortedSet<Date> sortedDates = new TreeSet<>(Collections.reverseOrder());
        sortedDates.addAll(getAssessments().keySet());
        return sortedDates;
    }

    public String listAssessmentDates() {
        StringBuilder dates = new StringBuilder();
        for (Date date : sortedAssessmentDates()) {
            dates.append(printShortDate(date)).append("\n");
        }
        return dates.toString();
    }

    public String getWeightProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getWeight()).append("\n");
        }
        return progress.toString();
    }

    public String getChestProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getChest()).append("\n");
        }
        return progress.toString();
    }

    public String getThighProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getThigh()).append("\n");
        }
        return progress.toString();
    }

    public String getUpperArmProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getUpperArm()).append("\n");
        }
        return progress.toString();
    }

    public String getWaistProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getWaist()).append("\n");
        }
        return progress.toString();
    }

    public String getHipsProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getHips()).append("\n");
        }
        return progress.toString();
    }

    public String specificMemberProgress() {
        StringBuilder details = new StringBuilder();
        for (Date date : sortedAssessmentDates()) {
            details.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).toString()).append("\n");
        }
        return details.toString();
    }

}
