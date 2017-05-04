package utils;

import models.Assessment;
import models.Member;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Created by John on 24/04/2017.
 */
public class Analytics {
    /**
     * Calculate a member's body mass index, weight divided by height squared
     * @return  Member's BMI (kg m ^-2) truncated to two decimal places
     */
    public static double calculateBMI(Member member, Assessment assessment){
        return toTwoDecimalPlaces(member.getStartingWeight() / (member.getHeight() * member.getHeight()));
    }

    /**
     * Determines the BMI catergory to which the member belongs.
     * The categories are determined as follows:
     * less than 15 "VERY SEVERELY UNDERWEIGHT"
     * between 15 (inclusive) and 16 (exclusive) "SEVERELY UNDERWEIGHT"
     * between 16 (inclusive) and 18.5 (exclusive) "UNDERWEIGHT"
     * between 18.5 (inclusive) and 25 (exclusive) "NORMAL"
     * between 25 (inclusive) and 30 (exclusive) "OVERWEIGHT"
     * between 30 (inclusive) and 35 (exclusive) "MODERATELY OBESE"
     * between 35 (inclusive) and 40 (exclusive) "SEVERELY OBESE"
     * greater than 40 "VERY SEVERELY OBESE"
     * @return  Member's BMI category
     */
    @NotNull
    @Contract(pure = true)
    public static String determineBMICategory(double bmiValue) {
        if (bmiValue < 15) {
            return "VERY SEVERELY UNDERWEIGHT";
        } else if (bmiValue >= 15 && bmiValue < 16) {
            return "SEVERELY UNDERWEIGHT";
        } else if (bmiValue >= 16 && bmiValue < 18.5) {
            return "UNDERWEIGHT";
        } else if (bmiValue >= 18.5 && bmiValue < 25) {
            return "NORMAL";
        } else if (bmiValue >= 25 && bmiValue < 30) {
            return "OVERWEIGHT";
        } else if (bmiValue >= 30 && bmiValue < 35) {
            return "MODERATELY OBESE";
        } else if (bmiValue >= 35 && bmiValue < 40) {
            return "SEVERELY OBESE";
        } else {
            return "VERY SEVERELY OBESE";
        }
    }

    /**
     * Check's if a member's starting weight is ideal as defined by the Devine formula.
     * Males IBW = 50kg + 2.3kg per inch over 5ft
     * Females IBW = 45.5kg + 2.3kg per inch over 5ft
     * Members under 5ft are assigned the IBW of a 5ft member
     * @return  True if starting weight = ideal body weight. False otherwise.
     */
    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        double inchHeight = convertHeightMetresToInches(member.getHeight());
        if (inchHeight > 60) {
            inchHeight = inchHeight - 60;
        } else {
            inchHeight = 0;
        }
        double idealBodyWeight = 2.3 * inchHeight;
        if (member.getGender().equals("M")) {
            idealBodyWeight = idealBodyWeight + 50;
        } else {
            idealBodyWeight = idealBodyWeight + 45.5;
        }
        return idealBodyWeight <= member.getStartingWeight() + 2 && idealBodyWeight >= member.getStartingWeight() - 2;
    }

    /**
     * helper method to truncate numbers to two decimal places
     * @return number to two decimal places
     */
    @Contract(pure = true)
    private static double toTwoDecimalPlaces(double num){
        return (int) (num * 100 ) / 100.0;
    }

    /**
     * Converts a member's height from metres to inches, 1m = 39.37 inches.
     * @return  Member's height in inches truncated to two decimal places
     */
    @Contract(pure = true)
    public static double convertHeightMetresToInches(double height) {
        return toTwoDecimalPlaces(height * 39.37);
    }
}
