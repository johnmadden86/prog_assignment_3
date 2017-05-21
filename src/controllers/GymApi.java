package controllers;

import models.Member;
import models.Person;
import models.Trainer;
import static utils.Analytics.*;
import java.util.ArrayList;
import java.util.HashMap;


public class GymApi {
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;

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

    /*private HashMap<Integer, Member> membersByName(String searchTerm) {
        int index = 1;
        HashMap<Integer, Member> indexedList = new HashMap<>();
        for (Member member : getMembers()) {
            if (member.getName().contains(searchTerm)) {
                indexedList.put(index, member);
                index++;
            }
        }
        return indexedList;
    }

    public String membersByNameToString (String searchTerm) {
        HashMap<Integer, Member> indexedList = membersByName(searchTerm);
        StringBuilder list = new StringBuilder();
        for (int i = 1; i < indexedList.keySet().size(); i++) {
            Member member = indexedList.get(i);
            list.append(i).append(" - ").append(member.getName()).append("\n");
        }
        return list.toString();
    }*/

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
