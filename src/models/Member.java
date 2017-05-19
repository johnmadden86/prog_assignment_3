package models;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.Callable;


/**
 * Created by John on 24/04/2017.
 */
public abstract class Member extends Person {
    private double height, startingWeight;
    public String chosenPackage;
    private HashMap<Date,Assessment> assessments;

    public Member(String email, String name, String address, String gender,
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

    public Assessment getAssessment (Date date) {
        return getAssessments().get(date);
    }

    public void setAssessments(HashMap assessments) {
        this.assessments = assessments;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getStartingWeight() {
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

    public String getChosenPackage() {
        return chosenPackage;
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    @Override
    public String toString() {
        return super.toString() +
                "height=" + height +
                ", startingWeight=" + startingWeight +
                ", chosenPackage='" + chosenPackage + '\'';
    }

    public Assessment latestAssessment() {
        return assessments.get(sortedAssessmentDates().last());
    }

    public SortedSet<Date> sortedAssessmentDates() {
        return new TreeSet<>(assessments.keySet());
    }

    public String getProgress () {
        StringBuilder weightProgress = new StringBuilder();
        for (Date date: sortedAssessmentDates()) {
            weightProgress.append(date).append(" - ")
                    .append(getAssessment(date).getWeight()).append("\n");
        }
        return weightProgress.toString();
    }

    public abstract void chosenPackage(String chosenPackage);


}
