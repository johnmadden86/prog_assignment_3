package models;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class Member extends Person {
    private double height, startingWeight;
    String chosenPackage;
    private HashMap<Date,Assessment> assessments;

    Member(String email, String name, String address, String gender,
           double height, double startingWeight, String chosenPackage) {

        super(email, name, address, gender);
        this.height = height;
        this.startingWeight = startingWeight;
        this.chosenPackage = chosenPackage;
        assessments = new HashMap<>();
    }

    public void addAssessment(Date date, Assessment assessment){
        assessments.put(date, assessment);
    }

    public HashMap<Date, Assessment> getAssessments() {
        return assessments;
    }

    private Assessment getAssessment (Date date) {
        return getAssessments().get(date);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Contract(pure = true)
    private double getStartingWeight() {
        return startingWeight;
    }

    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }

    public double getCurrentWeight() {
        double weight = getStartingWeight();
        if (latestAssessment() != null) {
            weight = latestAssessment().getWeight();
        }
        return weight;
    }

    @Contract(pure = true)
    private String getChosenPackage() {
        return chosenPackage;
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nHeight: " + getHeight() +
                "\nStarting Weight: " + getStartingWeight() +
                "\nChosen Package: " + getChosenPackage();
    }

    @Nullable
    private Assessment latestAssessment() {
        if (assessments.isEmpty()) {
            return null;
        } else {
            return assessments.get(sortedAssessmentDates().last());
        }
    }

    public SortedSet<Date> sortedAssessmentDates() {
        TreeSet sortedDates = new TreeSet<>(Collections.reverseOrder());
        sortedDates.addAll(assessments.keySet());
        return sortedDates;
    }

    public String getWeightProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(date).append(" - ")
                    .append(getAssessment(date).getWeight()).append("\n");
        }
        return progress.toString();
    }

    public String getChestProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(date).append(" - ")
                    .append(getAssessment(date).getChest()).append("\n");
        }
        return progress.toString();
    }

    public String getThighProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(date).append(" - ")
                    .append(getAssessment(date).getThigh()).append("\n");
        }
        return progress.toString();
    }

    public String getUpperArmProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(date).append(" - ")
                    .append(getAssessment(date).getUpperArm()).append("\n");
        }
        return progress.toString();
    }

    public String getWaistProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(date).append(" - ")
                    .append(getAssessment(date).getWaist()).append("\n");
        }
        return progress.toString();
    }

    public String getHipsProgress () {
        StringBuilder progress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            progress.append(date).append(" - ")
                    .append(getAssessment(date).getHips()).append("\n");
        }
        return progress.toString();
    }

    @NotNull
    public String specificMemberProgress() {
        StringBuilder details = new StringBuilder();
        for (Date date : sortedAssessmentDates()) {
            details.append(date).append(" - ")
                    .append(getAssessment(date).toString()).append("\n");
        }
        return details.toString();
    }

    //public abstract void chosenPackage(String chosenPackage);


}
