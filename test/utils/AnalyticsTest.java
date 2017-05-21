package utils;

import models.PremiumMember;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static utils.Analytics.*;

class AnalyticsTest {
    private PremiumMember member;

    @BeforeEach
    void setUp() {
        member = new PremiumMember("zlatan@manutd.com", "Zlatan Ibrahimovic", "Sweden",
                "M", 1.95, 96, "Package 1");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSetupBmiCategories() {
        setupBmiCategories();
        assertEquals(8, bmiCategories.size());
    }

    @Test
    void testGetBmiCategory() {
        setupBmiCategories();
        assertEquals("Very severely underweight", getBmiCategory(0));
        assertEquals("Severely underweight", getBmiCategory(1));
        assertEquals("Underweight", getBmiCategory(2));
        assertEquals("Normal", getBmiCategory(3));
        assertEquals("Overweight", getBmiCategory(4));
        assertEquals("Moderately Obese", getBmiCategory(5));
        assertEquals("Severely Obese", getBmiCategory(6));
        assertEquals("Very severely obese", getBmiCategory(7));
    }

    @Test
    void testListBmiCategories() {
        setupBmiCategories();
        assertEquals("Very severely underweight\nSeverely underweight\n" +
                "Underweight\nNormal\nOverweight\nModerately Obese\nSeverely Obese\nVery severely obese\n", listBmiCategories());
    }

    @Test
    void testCalculateBMI() {
        assertEquals(25.24, calculateBMI(member), 0.01);
    }

    @Test
    void testDetermineBMICategory() {
        assertEquals("Very severely underweight", determineBMICategory(14.99));
        assertEquals("Severely underweight", determineBMICategory(15));
        assertEquals("Severely underweight", determineBMICategory(15.01));
        assertEquals("Severely underweight", determineBMICategory(15.99));
        assertEquals("Underweight", determineBMICategory(16));
        assertEquals("Underweight", determineBMICategory(16.01));
        assertEquals("Underweight", determineBMICategory(18.49));
        assertEquals("Normal", determineBMICategory(18.5));
        assertEquals("Normal", determineBMICategory(18.51));
        assertEquals("Normal", determineBMICategory(24.99));
        assertEquals("Overweight", determineBMICategory(25));
        assertEquals("Overweight", determineBMICategory(25.01));
        assertEquals("Overweight", determineBMICategory(29.99));
        assertEquals("Moderately Obese", determineBMICategory(30));
        assertEquals("Moderately Obese", determineBMICategory(30.01));
        assertEquals("Moderately Obese", determineBMICategory(34.99));
        assertEquals("Severely Obese", determineBMICategory(35));
        assertEquals("Severely Obese", determineBMICategory(35.01));
        assertEquals("Severely Obese", determineBMICategory(39.99));
        assertEquals("Very severely obese", determineBMICategory(40));
        assertEquals("Very severely obese", determineBMICategory(40.01));
    }

    @Test
    void testIsIdealBodyWeight() {
            assertEquals(false, isIdealBodyWeight(member));
            member.setStartingWeight(88.5);
            assertEquals(true, isIdealBodyWeight(member));
            member.setGender("F");
            assertEquals(false, isIdealBodyWeight(member));
            member.setStartingWeight(84);
            assertEquals(true, isIdealBodyWeight(member));
        }

    @Test
    void testToTwoDecimalPlaces() {
            assertEquals(12.3,toTwoDecimalPlaces(12.3),0.001);
            assertEquals(12.34,toTwoDecimalPlaces(12.34),0.001);
            assertEquals(12.34,toTwoDecimalPlaces(12.345),0.001);
        }

    @Test
    void testConvertHeightMetresToInches() {
            assertEquals(39.37, convertHeightMetresToInches(1), 0.01);
        }

    @Test
    void testConvertWeightKilogramsToPounds() {
            assertEquals(2.2, convertWeightKilogramsToPounds(1), 0.01);
        }

}