package controllers;
import models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedSet;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.jetbrains.annotations.NotNull;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import static utils.Analytics.*;
import static utils.ScannerInput.*;

/**
 * Created by John on 24/04/2017.
 */
class MenuController {

    private GymApi gymApi;

    /**
     * Main method to run the program
     * @param args As required by main
     */
    public static void main(String[] args) {
        new MenuController();
    }

    /**
     * Constructor for objects of class MenuController
     */
    private MenuController() {
        gymApi = new GymApi();
        try {
            load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
        homePage();
    }

    /**
     * This method controls the loop for the 'out of game' functions
     */
    private void homePage() {
        System.out.println("  Gym Menu");
        System.out.println("-----------");
        System.out.println("  L) Login");
        System.out.println("  R) Register");
        System.out.println("-----------");
        System.out.println("  X) Exit");

        String option = toUpperCase(validNextString("==>> "));//puts user's choice from the main menu through the switch loop

            switch (option) {
                case "L": //login
                    login();
                    break;
                case "R": //register
                    registration(false);
                    break;
                case "X":
                    try {
                        save();
                        System.out.println("Exiting... bye");
                    }
                    catch (Exception e) {
                        System.err.println("Error writing to file: " + e);
                    }
                    System.exit(0);
                default:
                    System.out.println("Invalid option entered: " + option);
                    pause();
                    homePage();
                    break;
            }
    }

    private void registration(boolean addingByTrainer) {
        String mOrT = "";
        if (!addingByTrainer) {
        System.out.println("\n  Select an option");
        System.out.println("-----------");
        System.out.println("  M) Member");
        System.out.println("  T) Trainer");
        System.out.println("-----------");
        System.out.println("  B) Back to previous menu");
        mOrT = toUpperCase(validNextString("==>> "));
        } else {
            mOrT = "M";
        }

        while (!mOrT.equals("B")){
            String name = validNextString("Enter name: ");
            String address = validNextString("Enter address: ");
            String email = validNextString("Enter email address: ");
            String gender = validNextString("Enter gender (M/F): ");
            switch (mOrT) {
                case "T":
                    String speciality = validNextString("Pick a speciality: " );
                    Trainer trainer = new Trainer(email, name, address, gender, speciality);
                    gymApi.addTrainer(trainer);
                    trainerWelcomePage(trainer);
                    break;
                case "M":
                    Member member = null;
                    double height = validNextDouble("Enter height: ");
                    double startingWeight = validNextDouble("Enter starting weight: ");
                    String packageChoice = validNextString("Enter package choice: ");
                    switch (packageChoice) {
                        case "Premium":
                            member = new PremiumMember(email, name, address, gender, height, startingWeight, packageChoice);
                            gymApi.addMember(member);
                            break;
                        case "Student":
                            String studentId = validNextString("Enter student ID: ");
                            String collegeName = validNextString("Enter college name: ");
                            member = new StudentMember(email, name, address, gender, height, startingWeight, packageChoice, studentId, collegeName);
                            gymApi.addMember(member);
                            break;
                        default:
                        member = new BasicMember(email, name, address, gender, height, startingWeight, packageChoice);
                        gymApi.addMember(member);
                        break;
                    }
                    memberWelcomePage(member);
                default:
                    System.out.println("Invalid option entered: " + mOrT);
                    pause();
                    registration(addingByTrainer);
            }
        }
        homePage();
    }

    private void login() {
        String email = validNextString("Enter email address: ");
        Person person = gymApi.searchPersonsByEmail(email);
        if (person instanceof Trainer) {
            trainerWelcomePage((Trainer) person);
        } else if (person instanceof Member) {
            memberWelcomePage((Member) person);
        } else {
            System.out.println("Invalid option entered: " + email);
            pause();
            login();
        }
    }

    private void trainerWelcomePage(Trainer trainer) {
        System.out.println("  Select an option");
        System.out.println("-----------");
        System.out.println("  1) Add a new member");
        System.out.println("  2) List all members");
        System.out.println("  3) Search for a member by email");
        System.out.println("  4) Search for a member by name");
        System.out.println("  5) List members with ideal body weight");
        System.out.println("  6) List members with a specific BMI category");
        System.out.println("  7) AssessmentTest sub-menu");
        System.out.println("  8) Reports sub-menu");
        System.out.println("-----------");
        System.out.println("  0) Logout");
        int option = validNextInt("=>>");

        switch (option) {
            case 1:
                registration(true);
                break;
            case 2:
                System.out.println(gymApi.listMembers());
                break;
            case 3:
                String mailSearch = validNextString("Enter a search term: ");
                System.out.println(gymApi.searchMembersByEmail(mailSearch));
                break;
            case 4:
                String nameSearch = validNextString("Enter a search term: ");
                System.out.println(gymApi.searchMembersByName(nameSearch));
                break;
            case 5:
                System.out.println(gymApi.listMembersWithIdealWeight());
                break;
            case 6:
                listBmiCategories();
                int index = validNextInt("\nEnter a BMI category: ");
                System.out.println(gymApi.listMembersBySpecificBMICategory(getBmiCategory(index)));
                break;
            case 7:
                assessmentSubMenu(trainer);
                break;
            case 8:
                reportsSubMenu(trainer);
                break;
            case 0:
                System.out.println("Logging out...");
                pause();
                homePage();
                break;
            default:
                System.out.println("Invalid option entered: " + option);
                break;
        }
        pause();
        trainerWelcomePage(trainer);
    }

    private void assessmentSubMenu(Trainer trainer) {
        System.out.println("  Select an option");
        System.out.println("-----------");
        System.out.println("  1) Add an assessment for a member");
        System.out.println("  2) Update comment on an assessment for a member");
        System.out.println("-----------");
        System.out.println("  0) Back to previous menu");
        int option = validNextInt("=>>");

        switch (option) {
            case 1:
                String mailSearch = validNextString("Enter email address: ");
                Member member = gymApi.searchMembersByEmail(mailSearch);
                Date date = readValidDate("Date of assessment: ");
                double weight = validNextDouble("Weight: ");
                double chest = validNextDouble("Chest: ");
                double thigh = validNextDouble("Thigh: ");
                double upperArm = validNextDouble("Upper Arm: ");
                double waist = validNextDouble("Waist: ");
                double hips = validNextDouble("Hips: ");
                String comment = validNextString("Enter comment: ");
                member.addAssessment(date,
                        new Assessment(weight, chest, thigh, upperArm, waist, hips, trainer, comment));
                break;
            case 2:
                String emailSearch = validNextString("Enter email address: ");
                Member memberToUpdate = gymApi.searchMembersByEmail(emailSearch);
                System.out.println(memberToUpdate.sortedAssessmentDates());
                Date dateToUpdate = readValidDate("Enter date of assessment to update: ");
                String newComment = validNextString("Enter new comment: ");
                memberToUpdate.getAssessments().get(dateToUpdate).setComment(newComment);
                break;
            case 0:
                trainerWelcomePage(trainer);
                break;
            default:
                System.out.println("Invalid option entered: " + option);
                pause();
                break;
        }
        assessmentSubMenu(trainer);
    }

    private void reportsSubMenu(Trainer trainer) {
        System.out.println("  Select an option");
        System.out.println("-----------");
        System.out.println("  1) Specific member progress (via email search)");
        System.out.println("  2) Specific member progress (via name search)");
        System.out.println("  3) Overall members' report");
        System.out.println("-----------");
        System.out.println("  0) Back to previous menu");

        int option = validNextInt("=>>");

        switch (option) {
            case 1:
                String mailSearch = validNextString("Enter email address: ");
                System.out.println(specificMemberProgress(gymApi.searchMembersByEmail(mailSearch)));
                break;
            case 2:
                String nameSearch = validNextString("Enter name: ");
                System.out.println(gymApi.searchMembersByName(nameSearch));
                int index = validNextInt("Enter index: ");
                specificMemberProgress(gymApi.getMembers().get(index));
                break;
            case 3:
                break;
            case 0:
                trainerWelcomePage(trainer);
                break;
            default:
                System.out.println("Invalid option entered: " + option);
                pause();
                break;
        }
        reportsSubMenu(trainer);
    }

    private void memberWelcomePage(Member member) {
        System.out.println("  Welcome");
        System.out.println("-----------");
        System.out.println("  1) View profile");
        System.out.println("  2) Update profile");
        System.out.println("  3) Progress sub-menu");
        System.out.println("-----------");
        System.out.println("  0) Logout");

        int option = validNextInt("=>>");

        switch (option) {
            case 1:
                viewMemberProfile(member);
                break;
            case 2:
                updateMember(member);
                break;
            case 3:
                specificMemberProgress(member);
                break;
            case 0:
                System.out.println("Logging out...");
                pause();
                homePage();
            default:
                System.out.println("Invalid option entered: " + option);
                pause();
                break;
        }
        memberWelcomePage(member);
    }

    private void viewMemberProfile(Member member) {
        System.out.println(member.toString());
    }


    private void updateMember(Member member) {
        viewMemberProfile(member);
        System.out.println("  Select an option");
        System.out.println("-----------");
        System.out.println("  1) Update name");
        System.out.println("  2) Update address");
        System.out.println("  3) Update email address");
        System.out.println("  4) Update gender");
        System.out.println("  5) Update height");
        System.out.println("  6) Update startingWeight");
        System.out.println("-----------");
        System.out.println("  0) Back to previous menu");
        int option = validNextInt("=>>");

        switch (option) {
            case 1:
                member.setName(validNextString("Enter name: "));
                break;
            case 2:
                member.setAddress(validNextString("Enter address: "));
                break;
            case 3:
                member.setEmail(validNextString("Enter email address: "));
                break;
            case 4:
                member.setGender(validNextString("Enter gender (M/F): "));
                break;
            case 5:
                member.setStartingWeight(validNextDouble("Enter starting weight: "));
                break;
            case 6:
                member.setChosenPackage(validNextString("Enter package choice: "));
                break;
            case 0:
                memberWelcomePage(member);
            default:
                System.out.println("Invalid option entered: " + option);
                pause();
                break;
        }
        updateMember(member);
    }

    private void progressSubMenu(Member member) {
        System.out.println("  Select an option");
        System.out.println("-----------");
        System.out.println("  1) View progress by weight");
        System.out.println("  2) View progress by chest measurement");
        System.out.println("  3) View progress by thigh measurement");
        System.out.println("  4) View progress by upper arm measurement");
        System.out.println("  5) View progress by waist measurement");
        System.out.println("  6) View progress by hips measurement");
        System.out.println("-----------");
        System.out.println("  0) Back to previous menu");

        int option = validNextInt("=>>");

        switch (option) {
            case 1:
                System.out.println(member.getProgress());
                break;
            case 2:
                System.out.println(member.getProgress());
                break;
            case 3:
                System.out.println(member.getProgress());
                break;
            case 4:
                System.out.println(member.getProgress());
                break;
            case 5:
                System.out.println(member.getProgress());
                break;
            case 6:
                System.out.println(member.getProgress());
                break;
            case 0:
                memberWelcomePage(member);
            default:
                System.out.println("Invalid option entered: " + option);
                pause();
                break;
        }
        progressSubMenu(member);
    }

    private void pause() {
        validNextString("Press any key to continue...\n");
    }

    private void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("gymapp.xml"));
        out.writeObject(gymApi);
        out.close();
    }

    private void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("gymapp.xml"));
        gymApi = (GymApi) is.readObject();
        is.close();
    }

    @NotNull
    private String specificMemberProgress (Member member) {
        StringBuilder details = new StringBuilder();
        SortedSet<Date> dates = member.sortedAssessmentDates();
        for (Date date : dates) {
            details.append(member.getAssessments().get(date)).append("\n");
        }
        return details.toString();
    }

}

