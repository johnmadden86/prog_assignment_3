package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test the methods associated with the trainer class
 */
class TrainerTest {
    private Trainer trainer;
    @BeforeEach
    void setUp() {
        trainer = new Trainer("rui@manutd.pt", "Rui Faria", "Portugal", "M", "Fighting");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSpeciality() {
        assertEquals("Fighting", trainer.getSpeciality());
    }

    @Test
    void testTrainerString() {
        assertEquals("Name: Rui Faria\nAddress: Portugal\nEmail: rui@manutd.pt\nGender: M\nSpeciality: Fighting", trainer.toString());
    }

}