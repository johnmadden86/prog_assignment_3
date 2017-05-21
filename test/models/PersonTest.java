package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private PremiumMember premiumMember;

    @BeforeEach
    void setUp() {
        premiumMember = new PremiumMember("zlatan@manutd.com", "Zlatan Ibrahimovic", "Sweden",
                "x", 1.95, 96, "Package 1");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEmail() {
        assertEquals("zlatan@manutd.com", premiumMember.getEmail());
    }

    @Test
    void testName() {
        assertEquals("Zlatan Ibrahimovic", premiumMember.getName());
        premiumMember.setName("1245678901234567890123456789");
        assertEquals("1245678901234567890123456789", premiumMember.getName());
        premiumMember.setName("12456789012345678901234567890");
        assertEquals("12456789012345678901234567890", premiumMember.getName());
        premiumMember.setName("124567890123456789012345678901");
        assertEquals("124567890123456789012345678901", premiumMember.getName());
    }

    @Test
    void testAddress() {
        assertEquals("Sweden", premiumMember.getAddress());
    }

    @Test
    void testGender() {
        assertEquals("Unspecified",premiumMember.getGender());
        premiumMember.setGender("M");
        assertEquals("M",premiumMember.getGender());
        premiumMember.setGender("m");
        assertEquals("M",premiumMember.getGender());
        premiumMember.setGender("F");
        assertEquals("F",premiumMember.getGender());
        premiumMember.setGender("f");
        assertEquals("F",premiumMember.getGender());
        premiumMember.setGender("female");
        assertEquals("F",premiumMember.getGender());
        premiumMember.setGender("not known");
        assertEquals("F",premiumMember.getGender());
    }

    @Test
    void testPersonString() {
        assertEquals("Name: Zlatan Ibrahimovic\nAddress: Sweden\nEmail: zlatan@manutd.com" +
                "\nGender: Unspecified\nHeight: 1.95\nStarting Weight: 96.0\nCurrent Weight: 96.0" +
                "\nChosen Package: Package 1", premiumMember.toString());
    }

}