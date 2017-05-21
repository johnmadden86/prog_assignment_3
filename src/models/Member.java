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
     * Constructor of object type member
     * @param email email address
     * @param name name
     * @param address address
     * @param gender gender
     * @param height height
     * @param startingWeight member's starting weight
     * @param chosenPackage the member's chosen gym package
     */
    Member(String email, String name, String address, String gender,
           double height, double startingWeight, String chosenPackage) {

        super(email, name, address, gender);
        setHeight(height);
        setStartingWeight(startingWeight);
        setChosenPackage(chosenPackage);
        assessments = new HashMap<>();
    }

    /**
     * Method to add an assessment for a member
     * @param date date of assessment
     * @param assessment object of type assessment
     */
    public void addAssessment(Date date, Assessment assessment){
        getAssessments().put(date, assessment);
    }

    /**
     * getter for a member's assessments
     * @return member's assessments
     */
    public HashMap<Date, Assessment> getAssessments() {
        return assessments;
    }

    /**
     * Getter for a specific assessment
     * @param date date of assessment
     * @return assessment on that date
     */
    Assessment getAssessment(Date date) {
        return getAssessments().get(date);
    }

    /**
     * getter for height
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * setter for height
     * between 1m & 3m
     * defaults to 1 or 3 if values under/over this are entered
     * @param height height entered
     */
    public void setHeight(double height) {
        if (height < 1) {
            height = 1;
        } else if (height > 3) {
            height = 3;
        }
        this.height = toTwoDecimalPlaces(height);
    }

    /**
     * getter for starting weight
     * @return member's starting weight
     */
    double getStartingWeight() {
        return startingWeight;
    }

    /**
     * setter for starting weight
     * min 35kg, max 250kg
     * @param startingWeight starting weight
     */
    public void setStartingWeight(double startingWeight) {
        if (startingWeight < 35) {
            startingWeight = 35;
        } else if (startingWeight > 250) {
            startingWeight = 250;
        }
        this.startingWeight = toTwoDecimalPlaces(startingWeight);
    }

    /**
     * Method to get a member's current weight as per their latest assessment
     * @return member's current weight, starting weight if there are no assessments
     */
    public double getCurrentWeight() {
        double weight = getStartingWeight();
        if (latestAssessment() != null) {
            //noinspection ConstantConditions
            weight = latestAssessment().getWeight();
        }
        return weight;
    }

    /**
     * getter for a member's chosen package
     * @return chosen package
     */
    String getChosenPackage() {
        return chosenPackage;
    }

    /**
     * setter for chosen package
     * @param chosenPackage chosen package
     */
    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    /**
     * Lists a member's attributes as a string
     * @return member's attributes as a string
     */
    public String toString() {
        return super.toString() +
                "\nHeight: " + getHeight() +
                "\nStarting Weight: " + getStartingWeight() +
                "\nCurrent Weight: " + getCurrentWeight() +
                "\nChosen Package: " + getChosenPackage();
    }

    /**
     * Gets a member's most recent assessment
     * @return most recent assessment
     */
    Assessment latestAssessment() {
        if (getAssessments().isEmpty()) {
            return null;
        } else {
            return getAssessments().get(sortedAssessmentDates().first());
        }
    }

    /**
     * Method to sort the assessment dates
     * @return assessment dates in reverse chronological order
     */
    SortedSet<Date> sortedAssessmentDates() {
        SortedSet<Date> sortedDates = new TreeSet<>(Collections.reverseOrder());
        sortedDates.addAll(getAssessments().keySet());
        return sortedDates;
    }

    /**
     * lists a member's assessment dates
     * @return a member's assessment dates as a string
     */
    public String listAssessmentDates() {
        StringBuilder dates = new StringBuilder();
        for (Date date : sortedAssessmentDates()) {
            dates.append(printShortDate(date)).append("\n");
        }
        return dates.toString();
    }

    /**
     * Lists a member's weight by date
     * @return string of assessments dates and weights
     */
    public String getWeightProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getWeight()).append("\n");
        }
        return progress.toString();
    }

    /**
     * Lists a member's chest measurement by date
     * @return string of assessments dates and chest measurements
     */
    public String getChestProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getChest()).append("\n");
        }
        return progress.toString();
    }

    /**
     * Lists a member's thigh measurement by date
     * @return string of assessments dates and thigh measurements
     */
    public String getThighProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getThigh()).append("\n");
        }
        return progress.toString();
    }

    /**
     * Lists a member's upper arm measurement by date
     * @return string of assessments dates and upper arm measurements
     */
    public String getUpperArmProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getUpperArm()).append("\n");
        }
        return progress.toString();
    }

    /**
     * Lists a member's waist measurement by date
     * @return string of assessments dates and waist measurements
     */
    public String getWaistProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getWaist()).append("\n");
        }
        return progress.toString();
    }

    /**
     * Lists a member's hips measurement by date
     * @return string of assessments dates and hips measurements
     */
    public String getHipsProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).getHips()).append("\n");
        }
        return progress.toString();
    }

    /**
     * Lists a member's assessment details by date
     * @return string of assessments dates and measurements
     */
    public String specificMemberProgress() {
        StringBuilder details = new StringBuilder();
        for (Date date : sortedAssessmentDates()) {
            details.append(printShortDate(date)).append(" - ")
                    .append(getAssessment(date).toString()).append("\n");
        }
        return details.toString();
    }

}
