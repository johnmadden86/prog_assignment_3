package controllers;

import models.Member;
import models.Person;
import models.Trainer;

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
        return null;
    }

    public String searchMembersByName(String nameEntered){
        return null;
    }

    public Person searchTrainersByEmail(String emailEntered){
        return null;
    }

    public String listMembers() {
        return null;
    }

    public String listMembersWithIdealWeight() {
        return null;
    }

    public String listMembersBySpecificBMICategory(String category) {
        return null;
    }

    public String listMemberDetailsImperialAndMetric() {
        return null;
    }

    public void load() throws Exception {

    }

    public void store() throws Exception {

    }
}
