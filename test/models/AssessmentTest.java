package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test the methods associated with the assessment class
 */
class AssessmentTest {
    private Assessment assessment;
    private Trainer trainer;

    @BeforeEach
    void setUp() {
        trainer = new Trainer("email", "name", "address", "M", "speciality");
        assessment = new Assessment(1,2,3,4,5,6, trainer, "comment");

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAssessment() {
        assertEquals(1,assessment.getWeight(),0.001);
        assertEquals(2,assessment.getChest(),0.001);
        assertEquals(3,assessment.getThigh(),0.001);
        assertEquals(4,assessment.getUpperArm(),0.001);
        assertEquals(5,assessment.getWaist(),0.001);
        assertEquals(6,assessment.getHips(),0.001);
        assertEquals(trainer,assessment.getTrainer());
        assertEquals("comment",assessment.getComment());
    }

    @Test
    void testAssessmentString() {
        assertEquals("Weight: 1.0  Chest: 2.0  Thigh: 3.0  Upper Arm: 4.0  Waist: 5.0  Hips: 6.0  " +
                "Comment: comment  Trainer: name" , assessment.toString());
    }

}