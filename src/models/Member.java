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
    private String chosenPackage;
    private HashMap<Date,String> assessmentDetails;

    public Member(String email, String name, String address, String gender, double height, double startingWeight, String chosenPackage) {
        super(email, name, address, gender);
        if(height >= 1 && height <= 3) {//values between 1 and 3 only
            this.height = height;
        } else {//default to 0 if invalid entry made
            this.height = 0;
        }
        if(startingWeight >= 35 && startingWeight <= 250) {//values between 35 and 250 only
            this.startingWeight = startingWeight;
        } else {//default to 0 if invalid entry made
            this.startingWeight = 0;
        }
        this.chosenPackage = chosenPackage;
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
                "Member{" +
                "height=" + height +
                ", startingWeight=" + startingWeight +
                ", chosenPackage='" + chosenPackage + '\'' +
                '}';
    }

    public Assessment latestAssessment(){

    }

    public SortedSet<Date> sortedAssessmentDates(){

    }

    public abstract void chosenPackage();


}
