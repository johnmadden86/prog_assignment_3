package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static utils.Utilities.parseDate;

class MemberTest {
    private PremiumMember premiumMember;
    private Assessment assessment1, assessment2, assessment3;
    private HashMap<Date,Assessment> assessments;
    private Date date1, date2, date3;

    @BeforeEach
    void setUp() {
        premiumMember = new PremiumMember("zlatan@manutd.com", "Zlatan Ibrahimovic", "Sweden",
                "x", 1.95, 96, "Package 1");
        Trainer trainer = new Trainer("email", "name", "address", "m", "speciality");
        assessment1 = new Assessment(95.5,46,34.1,18.8,34.1,32.1, trainer, "comment");
        assessment2 = new Assessment(94.5,46.1,34.2,18.7,34.3,32.2, trainer, "comment");
        assessment3 = new Assessment(95.0,2,3,4,5,6, trainer, "comment");
        date1 = parseDate("18/05/2017");
        date2 = parseDate("19/05/2017");
        date3 = parseDate("20/05/2017");
        assessments = new HashMap<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAssessments() {
        assertEquals(assessments, premiumMember.getAssessments());
    }

    @Test
    void addAssessment() {
        assessments.put(date1, assessment1);
        assertTrue(assessments.containsKey(date1) && assessments.containsValue(assessment1));
    }

    @Test
    void getAssessment() {
        assertSame(assessments.get(new Date()), premiumMember.getAssessment(date1));
    }

    @Test
    void testHeight() {
        assertEquals(1.95, premiumMember.getHeight(), 0.001);
        premiumMember.setHeight(2.99);
        assertEquals(2.99, premiumMember.getHeight(), 0.001);
        premiumMember.setHeight(3);
        assertEquals(3, premiumMember.getHeight(), 0.001);
        premiumMember.setHeight(3.01);
        assertEquals(3, premiumMember.getHeight(), 0.001);
        premiumMember.setHeight(0.99);
        assertEquals(1, premiumMember.getHeight(), 0.001);
        premiumMember.setHeight(1);
        assertEquals(1, premiumMember.getHeight(), 0.001);
        premiumMember.setHeight(1.01);
        assertEquals(1.01, premiumMember.getHeight(), 0.001);
    }

    @Test
    void testStartingWeight() {
        assertEquals(96,premiumMember.getStartingWeight(),0.001);
        premiumMember.setStartingWeight(34.99);
        assertEquals(35,premiumMember.getStartingWeight(),0.001);
        premiumMember.setStartingWeight(35);
        assertEquals(35,premiumMember.getStartingWeight(),0.001);
        premiumMember.setStartingWeight(35.01);
        assertEquals(35.01,premiumMember.getStartingWeight(),0.001);
        premiumMember.setStartingWeight(249.99);
        assertEquals(249.99,premiumMember.getStartingWeight(),0.001);
        premiumMember.setStartingWeight(250);
        assertEquals(250,premiumMember.getStartingWeight(),0.001);
        premiumMember.setStartingWeight(250.01);
        assertEquals(250,premiumMember.getStartingWeight(),0.001);
    }

    @Test
    void getCurrentWeight() {
        assertEquals(96,premiumMember.getCurrentWeight());
        premiumMember.getAssessments().put(date1, assessment1);
        assertEquals(95.5,premiumMember.getCurrentWeight());
    }

    @Test
    void latestAssessment() {
        premiumMember.getAssessments().put(date1, assessment1);
        premiumMember.getAssessments().put(date2, assessment2);
        assertSame(assessment2, premiumMember.latestAssessment());

        premiumMember.getAssessments().clear();

        premiumMember.getAssessments().put(date2, assessment2);
        premiumMember.getAssessments().put(date1, assessment1);
        assertSame(assessment2, premiumMember.latestAssessment());
    }

    @Test
    void sortedAssessmentDates() {
        Set<Date> dates = new TreeSet<>();
        dates.add(date1);
        dates.add(date2);

        premiumMember.getAssessments().put(date1, assessment1);
        premiumMember.getAssessments().put(date2, assessment2);
        assertEquals(dates, premiumMember.sortedAssessmentDates());

        premiumMember.getAssessments().clear();

        premiumMember.getAssessments().put(date2, assessment2);
        premiumMember.getAssessments().put(date1, assessment1);
        assertEquals(dates, premiumMember.sortedAssessmentDates());
    }

    @Test
    void listAssessmentDates() {
        premiumMember.getAssessments().put(date1, assessment1);
        premiumMember.getAssessments().put(date2, assessment2);
        premiumMember.getAssessments().put(date3, assessment3);
        assertEquals("20/05/2017\n19/05/2017\n18/05/2017\n", premiumMember.listAssessmentDates());
    }

    @Test
    void testProgress() {
        premiumMember.getAssessments().put(date1, assessment1);
        premiumMember.getAssessments().put(date2, assessment2);
        assertEquals("19/05/2017 - 94.5\n18/05/2017 - 95.5\n", premiumMember.getWeightProgress());
        assertEquals("19/05/2017 - 46.1\n18/05/2017 - 46.0\n", premiumMember.getChestProgress());
        assertEquals("19/05/2017 - 34.2\n18/05/2017 - 34.1\n", premiumMember.getThighProgress());
        assertEquals("19/05/2017 - 18.7\n18/05/2017 - 18.8\n", premiumMember.getUpperArmProgress());
        assertEquals("19/05/2017 - 34.3\n18/05/2017 - 34.1\n", premiumMember.getWaistProgress());
        assertEquals("19/05/2017 - 32.2\n18/05/2017 - 32.1\n", premiumMember.getHipsProgress());
    }

    @Test
    void specificMemberProgress() {
        premiumMember.getAssessments().put(date1, assessment1);
        assertEquals("18/05/2017 - Weight: 95.5  Chest: 46.0  Thigh: 34.1  Upper Arm: 18.8  " +
                        "Waist: 34.1  Hips: 32.1  Comment: comment  Trainer: name\n",
                premiumMember.specificMemberProgress());
    }
}