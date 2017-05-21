package controllers;

import models.Member;
import models.Person;
import models.Trainer;
import static utils.Analytics.*;
import java.util.ArrayList;

/**
 * Class to run methods belonging to the gym
 */
class GymApi {
    private final ArrayList<Member> members;
    private final ArrayList<Trainer> trainers;

    /**
     * Constructor for objects of type gymapi
     */
    GymApi() {
        members = new ArrayList<>();
        trainers = new ArrayList<>();
    }

    /**
     * Method to add a new member
     * @param member the member to be added
     */
    void addMember(Member member) {
        getMembers().add(member);
    }

    /**
     * Method to get all members
     * @return all members as an array list
     */
    ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * Method to add a trainer
     * @param trainer the trainer to be added
     */
    void addTrainer(Trainer trainer) {
        getTrainers().add(trainer);
    }

    /**
     * Method to get all trainers
     * @return all trainers as an array list
     */
    ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    /**
     * Method to get all members and trainers
     * @return all persons as an array list
     */
    ArrayList<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<>(getMembers());
        persons.addAll(getTrainers());
        return persons;
    }

    /**
     * Method to search by email
     * @param emailEntered the email entered
     * @return the person whose email is matched
     */
    Person searchPersonsByEmail(String emailEntered){
        Person searchResult = null;
        for (Person person : getPersons()) {
            if(person.getEmail().equals(emailEntered)) {
                searchResult = person;
            }
        }
        return searchResult;
    }

    /**
     * Casts the search persons by email method to an object of type member
     * @param emailEntered the email to search for
     * @return the member searched for
     */
    Member searchMembersByEmail(String emailEntered){
        return (Member) searchPersonsByEmail(emailEntered);
    }

    /**
     * Methid to search by name
     * @param nameEntered the name to search for
     * @return all users returning that search term as a string
     */
    String searchMembersByName(String nameEntered){
        StringBuilder list = new StringBuilder();
        for (int index = 0; index < getMembers().size(); index++) {
            if(getMembers().get(index).getName().toUpperCase().contains(nameEntered.toUpperCase())) {
                list.append(index).append(" - ").append(getMembers().get(index).getName()).append("\n");
            }
        }
        if (list.toString().equals("")) {
            return "No members";
        } else {
            return list.toString();
        }
    }

    /**
     * Lists the members with an ideal body weight
     * @return the members with ideal bidy weight as a string
     */
    String listMembersWithIdealWeight() {
        if(getMembers().isEmpty()) {
            return "No members";
        } else {
            StringBuilder list = new StringBuilder();
            for (Member member : getMembers()) {
                if(isIdealBodyWeight(member)) {
                    list.append(member.getName()).append("\n");
                }
            }
            if (list.toString().equals("")) {
                return "No members with an ideal body weight";
            } else {
                return list.toString();
            }
        }
    }

    /**
     * Method to list all members
     * @return All member names as a string
     */
    String listMembers() {
        StringBuilder list = new StringBuilder();
        for (int index = 0; index < getMembers().size(); index++) {
            list.append(index).append(" - ").append(getMembers().get(index).getName()).append("\n");
        }
        if (list.toString().equals("")) {
            return "No members";
        } else {
            return list.toString();
        }
    }

    /**
     * List all members belonging to a specified category
     * @param category The category to list
     * @return A string with names of members with a bmi matching the search term
     */
    String listMembersBySpecificBMICategory(String category) {
        if(getMembers().isEmpty()) {
            return "No members";
        } else {
            StringBuilder list = new StringBuilder();
            for (Member member : getMembers()) {
                if(determineBMICategory(calculateBMI(member)).toUpperCase().contains(category.toUpperCase())) {
                    list.append(member.getName()).append("\n");
                }
            }
            if (list.toString().equals("")) {
                return "No members with BMI in the category \"" + category.toLowerCase() +"\"";
            } else {
                return list.toString();
            }
        }
    }

    /**
     * List all member with with in kg and lbs
     * @return Members and weights as a string
     */
    String listMemberDetailsImperialAndMetric() {
        if(getMembers().isEmpty()) {
            return "No members";
        } else {
            StringBuilder list = new StringBuilder();
            for (Member member : getMembers()) {
                list.append(member.getName()).append(" ")
                        .append(member.getCurrentWeight()).append("kg ")
                        .append(convertWeightKilogramsToPounds(member.getCurrentWeight())).append("lbs\n");
            }
            return list.toString();
        }
    }
}
