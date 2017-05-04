package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;


/**
 * Created by John on 24/04/2017.
 */
public abstract class Member extends Person{
    private double height;
    private double startingWeight;
    public String chosenPackage;
    private HashMap<Date,Assessment> assessments;

    public Member(String email, String name, String address, String gender,
                  double height, double startingWeight, String chosenPackage) {

        super(email, name, address, gender);

        this.height = height;

        this.startingWeight = startingWeight;

        this.chosenPackage = chosenPackage;
    }

    public void addAssessment(Date date,Assessment assessment){
        assessments.put(date, assessment);
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

    public Assessment latestAssessment(){
        return null;
    }

    public SortedSet<Date> sortedAssessmentDates(){
        return null;
    }

    public abstract void chosenPackage(String chosenPackage);


}
