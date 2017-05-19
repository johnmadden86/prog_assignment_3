package controllers;

import models.Member;
import models.Person;
import models.Trainer;
import static utils.Analytics.*;
import java.util.ArrayList;


public class GymApi {
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;

    GymApi() {
        members = new ArrayList<>();
        trainers = new ArrayList<>();
    }

    void addMember(Member member) {
        members.add(member);
    }

    ArrayList<Member> getMembers() {
        return members;
    }

    public int numberOfMembers() {
        return members.size();
    }

    void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public int numberOfTrainers() {
        return trainers.size();
    }

    private ArrayList<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<>(members);
        persons.addAll(trainers);
        return persons;
    }

    public boolean isValidMemberIndex(int index){
        return false;
    }

    public boolean isValidTrainerIndex(int index){
        return false;
    }

    Member searchMembersByEmail(String emailEntered){
        return (Member) searchPersonsByEmail(emailEntered);
    }

    public Trainer searchTrainersByEmail(String emailEntered){
        return (Trainer) searchPersonsByEmail(emailEntered);
    }

    String searchMembersByName(String nameEntered){
        StringBuilder list = new StringBuilder();
        for (int index = 0; index < members.size(); index++) {
            if(members.get(index).getName().contains(nameEntered)) {
                list.append(index).append(" - ").append(members.get(index).getName()).append("\n");
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

    Person searchPersonsByEmail(String emailEntered){
        Person searchResult = null;
        for (Person person : getPersons()) {
            if(person.getEmail().equals(emailEntered)) {
                searchResult = person;
            }
        }
        return searchResult;
    }

    String listMembers() {
        StringBuilder list = new StringBuilder();
        for (int index = 0; index < members.size(); index++) {
            list.append(index).append(" - ").append(members.get(index).getName()).append("\n");
        }
        if (list.toString().equals("")) {
            return "No members";
        } else {
            return list.toString();
        }
    }

    String listMembersBySpecificBMICategory(String category) {
        if(members.isEmpty()) {
            return "No members";
        } else {
            StringBuilder list = new StringBuilder();
            for (Member member : members) {
                if(determineBMICategory(calculateBMI(member)).toUpperCase().contains(category.toUpperCase())) {
                    list.append(member.getName()).append("\n");
                }
            }
            if (list.toString().equals("")) {
                return "No members with BMI in the category " + category.toLowerCase();
            } else {
                return list.toString();
            }
        }
    }

    public String listMemberDetailsImperialAndMetric() {
        return null;
    }

}
