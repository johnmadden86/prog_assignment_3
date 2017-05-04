package controllers;

import models.Member;
import models.Person;
import models.Trainer;
import static utils.Analytics.*;
import java.util.ArrayList;

/**
 * Created by John on 02/05/2017.
 */
public class GymApi {
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;

    public GymApi() {
        members = new ArrayList<>();
        trainers = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public int numberOfMembers() {
        return members.size();
    }

    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    public int numberOfTrainers() {
        return trainers.size();
    }

    public boolean isValidMemberIndex(int index){
        return false;
    }

    public boolean isValidTrainerIndex(int index){
        return false;
    }

    public Member searchMembersByEmail(String emailEntered){
        Member searchResult = null;
        for (Member member : members) {
            if(member.getEmail().contains(emailEntered)) {
                searchResult = member;
            }
        }
        return searchResult;
    }

    public String searchMembersByName(String nameEntered){
        StringBuilder list = new StringBuilder();
        for (Member member : members) {
            if(member.getEmail().contains(nameEntered)) {
                list.append(member.getName()).append("\n");
            }
        }
        if (list.toString().equals("")) {
            return "No members";
        } else {
            return list.toString();
        }
    }

    public String listMembersWithIdealWeight() {
        if(members.size() == 0) {
            return "No members";
        } else {
            StringBuilder list = new StringBuilder();
            for (Member member : members) {
                if(isIdealBodyWeight(member, member.latestAssessment())) {
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


    public Person searchTrainersByEmail(String emailEntered){
        Person searchResult = null;
        ArrayList<Person> persons = new ArrayList<>(members);
        persons.addAll(trainers);
        for (Person person : persons) {
            if(person.getEmail().contains(emailEntered)) {
                searchResult = person;
            }
        }
        return searchResult;
    }

    public String listMembers() {
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

    public String listMembersBySpecificBMICategory(String category) {
        if(members.size() == 0) {
            return "No members";
        } else {
            StringBuilder list = new StringBuilder();
            for (Member member : members) {
                if(determineBMICategory(calculateBMI(member, member.latestAssessment())).equals(category)) {
                    list.append(member.getName()).append("\n");
                }
            }
            if (list.toString().equals("")) {
                return "No members with an ideal body weight";
            } else {
                return list.toString();
            }
        }    }

    public String listMemberDetailsImperialAndMetric() {
        return null;
    }

    public void load() throws Exception {

    }

    public void store() throws Exception {

    }
}
