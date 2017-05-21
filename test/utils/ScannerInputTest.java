package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static utils.ScannerInput.*;
import static utils.Utilities.parseDate;

/**
 * Class to test the methods associated with the scanner input class
 */
class ScannerInputTest {
    @Test

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testValidNextInt() {
        String userInput = "1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        int input = validNextInt("placeholder");
        assertEquals(1, input);
        assertNotEquals("1", input);
        assertNotEquals(1.0, input);
    }

    @Test
    void testValidNextDouble() {
        String userInput = "1.1";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        double input = validNextDouble("placeholder");
        assertEquals(1.1, input, 0.001);
        assertNotEquals("1.1", input);
        assertNotEquals(1, input);
    }

    @Test
    void testReadValidDate() {
        String userInput = "01/01/2017";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Date input = readValidDate("placeholder");
        assertEquals(parseDate(userInput), input);
    }

    @Test
    void testValidNextString() {
        String userInput = "test";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        String input = validNextString("placeholder");
        assertEquals(userInput, input);
    }

    @Test
    void readValidEmail() {
        String userInput = "address@email.domain";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        String input = validNextString("placeholder");
        assertEquals(userInput, input);
    }
}