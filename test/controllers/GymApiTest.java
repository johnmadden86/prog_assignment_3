package controllers;

import models.Member;
import models.Person;
import models.PremiumMember;
import models.Trainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Analytics;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static utils.Analytics.addCategories;
import static utils.Analytics.setupBmiCategories;

class GymApiTest {
    private GymApi gymApi;
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;
    private PremiumMember premiumMember;
    private Trainer trainer;

    @BeforeEach
    void setUp() {
        gymApi = new GymApi();
        members = new ArrayList<>();
        trainers = new ArrayList<>();
        trainer = new Trainer("email", "name", "address", "M", "speciality");
        premiumMember = new PremiumMember("zlatan@manutd.se", "Zlatan Ibrahimovic", "Sweden",
                "M", 1.95, 96, "Package 1");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testMembers() {
        assertEquals(members, gymApi.getMembers());
        members.add(premiumMember);
        gymApi.getMembers().add(premiumMember);
        assertEquals(members, gymApi.getMembers());
    }

    @Test
    void testTrainers() {
        assertEquals(trainers, gymApi.getTrainers());
        trainers.add(trainer);
        gymApi.getTrainers().add(trainer);
        assertEquals(trainers, gymApi.getTrainers());
    }

    @Test
    void getPersons() {
        ArrayList<Person> persons = new ArrayList<>(gymApi.getMembers());
        persons.addAll(trainers);
        assertEquals(persons, gymApi.getPersons());

        gymApi.getMembers().add(premiumMember);
        gymApi.getTrainers().add(trainer);
        ArrayList<Person> persons1 = new ArrayList<>(gymApi.getMembers());
        persons1.addAll(gymApi.getTrainers());
        assertEquals(persons1, gymApi.getPersons());
    }

    @Test
    void searchByEmail() {
        gymApi.getMembers().add(premiumMember);
        gymApi.getTrainers().add(trainer);
        ArrayList<Person> persons = new ArrayList<>(gymApi.getMembers());
        persons.addAll(gymApi.getTrainers());
        assertEquals(premiumMember, gymApi.searchPersonsByEmail("zlatan@manutd.se"));
        assertEquals(trainer, gymApi.searchPersonsByEmail("email"));
        assertEquals(premiumMember, gymApi.searchMembersByEmail("zlatan@manutd.se"));
    }

    @Test
    void searchMembersByName() {
        gymApi.getMembers().add(premiumMember);
        assertEquals("0 - Zlatan Ibrahimovic\n", gymApi.searchMembersByName("Z"));
        assertEquals("0 - Zlatan Ibrahimovic\n", gymApi.searchMembersByName("z"));
        assertEquals("0 - Zlatan Ibrahimovic\n", gymApi.searchMembersByName(" "));
    }

    @Test
    void listMembersWithIdealWeight() {
        assertEquals("No members", gymApi.listMembersWithIdealWeight());
        gymApi.getMembers().add(premiumMember);
        assertEquals("No members with an ideal body weight", gymApi.listMembersWithIdealWeight());
        premiumMember.setStartingWeight(88.5);
        assertEquals("Zlatan Ibrahimovic\n", gymApi.listMembersWithIdealWeight());
        premiumMember.setGender("F");
        assertEquals("No members with an ideal body weight", gymApi.listMembersWithIdealWeight());
        premiumMember.setStartingWeight(84);
        assertEquals("Zlatan Ibrahimovic\n", gymApi.listMembersWithIdealWeight());
    }

    @Test
    void listMembers() {
        assertEquals("No members", gymApi.listMembers());
        gymApi.getMembers().add(premiumMember);
        assertEquals("0 - Zlatan Ibrahimovic\n", gymApi.listMembers());
    }

    @Test
    void listMembersBySpecificBMICategory() {
        setupBmiCategories();
        assertEquals("No members", gymApi.listMembersBySpecificBMICategory("normal"));
        gymApi.getMembers().add(premiumMember);
        assertEquals("No members with BMI in the category \"normal\"", gymApi.listMembersBySpecificBMICategory("NORMAL"));
        assertEquals("Zlatan Ibrahimovic\n", gymApi.listMembersBySpecificBMICategory("overw"));
    }

    @Test
    void listMemberDetailsImperialAndMetric() {
    }
}