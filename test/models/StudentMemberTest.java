package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMemberTest {
    private StudentMember studentMember1, studentMember2;
    @BeforeEach
    void setUp() {
        studentMember1 = new StudentMember("email", "name", "address",
                "M", 2, 100, "Package 2", "1234567", "UCC");
        studentMember2 = new StudentMember("email", "name", "address",
                "M", 2, 100, "Package 2", "1234567", "WIT");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testStudentId() {
        assertEquals("1234567", studentMember1.getStudentId());
        studentMember1.setStudentId("7654321");
        assertEquals("7654321", studentMember1.getStudentId());
    }

    @Test
    void testCollegeName() {
        assertEquals("UCC", studentMember1.getCollegeName());
        studentMember1.setStudentId("WIT");
        assertEquals("UCC", studentMember1.getCollegeName());
    }

    @Test
    void chosenPackage() {
        assertEquals("Package 3", studentMember1.getChosenPackage());
        assertEquals("WIT", studentMember2.getChosenPackage());
    }

    @Test
    void testStudentString() {
    }

}