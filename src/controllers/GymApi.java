package controllers;

import models.Member;
import models.Person;
import models.Trainer;
import static utils.Analytics.*;
import java.util.ArrayList;

class GymApi {
    private final ArrayList<Member> members;
    private final ArrayList<Trainer> trainers;

    GymApi() {
        members = new ArrayList<>();
        trainers = new ArrayList<>();
    }

    void addMember(Member member) {
        getMembers().add(member);
    }

    ArrayList<Member> getMembers() {
        return members;
    }

    void addTrainer(Trainer trainer) {
        getTrainers().add(trainer);
    }

    ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    ArrayList<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<>(getMembers());
        persons.addAll(getTrainers());
        return persons;
    }

    Person searchPersonsByEmail(String emailEntered){
        Person searchResult = null;
        for (Person person : getPersons()) {
            if(person.getEmail().equals(emailEntered)) {
                searchResult = person;
            }
        }
        return searchResult;
    }

    Member searchMembersByEmail(String emailEntered){
        return (Member) searchPersonsByEmail(emailEntered);
    }

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

    String listMembersWithIdealWeight() {
        if(members.isEmpty()) {
            return "No members";
        } else {
            StringBuilder list = new StringBuilder();
            for (Member member : members) {
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

    public String listMemberDetailsImperialAndMetric() {
        return null;
    }
}
