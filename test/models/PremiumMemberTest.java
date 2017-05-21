package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PremiumMemberTest {
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
    void chosenPackage() {
        assertEquals("Package 1", premiumMember.getChosenPackage());
    }

}