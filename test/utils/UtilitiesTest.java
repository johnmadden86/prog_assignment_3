package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static utils.Utilities.parseDate;
import static utils.Utilities.printShortDate;

class UtilitiesTest {
    private Date date;
    @BeforeEach
    void setUp() {
        date = new Date();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testShortDate() {
        assertEquals("21/05/2017", printShortDate(date));
    }

    @Test
    void testParseDate() {
    }

}