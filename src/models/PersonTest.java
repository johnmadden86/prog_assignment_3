package models;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by John on 05/05/2017.
 */
public class PersonTest {

    private Person person;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        person = new Person("email", "name", "address", "gender") {
        };
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void testgetEmail() {
        assertEquals("email", person.getEmail());
    }

    @org.junit.jupiter.api.Test
    void setEmail() {
    }

    @org.junit.jupiter.api.Test
    void testgetName() {
    }

    @org.junit.jupiter.api.Test
    void setName() {
    }

    @org.junit.jupiter.api.Test
    void testgetAddress() {
    }

    @org.junit.jupiter.api.Test
    void setAddress() {
    }

    @org.junit.jupiter.api.Test
    void testgetGender() {
    }

    @org.junit.jupiter.api.Test
    void setGender() {
    }

    @org.junit.jupiter.api.Test
    void testtoString() {
    }

}