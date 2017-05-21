package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static utils.Utilities.parseDate;
import static utils.Utilities.printShortDate;

@SuppressWarnings("deprecation")
class UtilitiesTest{
    private Date date;
    @BeforeEach
    void setUp() {
        date = new Date("2017/05/21");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testShortDate() {
        assertEquals("21/05/2017", printShortDate(date));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testParseDate() {
        assertEquals(date.getDate(), parseDate("21/05/2017").getDate());
        assertEquals(date.getMonth(), parseDate("21/05/2017").getMonth());
        assertEquals(date.getYear(), parseDate("21/05/2017").getYear());
    }

}