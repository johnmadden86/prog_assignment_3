package controllers;
import models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
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
                    memberOrTrainer(false);
                    break;
                case "R": //register
                    memberOrTrainer(true);
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

    private void memberOrTrainer(boolean reg) {
        System.out.println("\n  Select an option");
        System.out.println("-----------");
        System.out.println("  M) Member");
        System.out.println("  T) Trainer");
        System.out.println("-----------");
        System.out.println("  B) Back to previous menu");

        String mOrT = toUpperCase(validNextString("==>> "));

        while (!mOrT.equals("B")){
            if(mOrT.equals("M") || mOrT.equals("T")){
                if(reg) {
                    registration(mOrT);
                }
                else {
                    login(mOrT);
                }
            } else {
                System.out.println("Invalid option entered: " + mOrT);
                pause();
                memberOrTrainer(reg);
            }
        }
        homePage();
    }

    private void registration(String mOrT) {
        System.out.println("\nEnter registration details");
        pause();
        String name = validNextString("Enter name: ");
        String address = validNextString("Enter address: ");
        String email = validNextString("Enter email address: ");
        String gender = validNextString("Enter gender (M/F): ");
        if(mOrT.equals("T")){
            String speciality = validNextString("Pick a speciality: " );
            Trainer trainer = new Trainer(email, name, address, gender, speciality);
            gymApi.addTrainer(trainer);
            trainerWelcomePage(trainer);
        } else {
            Member member = null;
            double height = validNextDouble("Enter height: ");
            double startingWeight = validNextDouble("Enter starting weight: ");
            String packageChoice = validNextString("Enter package choice: ");
            if (!packageChoice.equals("Student")) {
                member = new PremiumMember(email, name, address, gender, height, startingWeight, packageChoice);
                gymApi.addMember(member);
            } else {
                String studentId = validNextString("Enter student ID: ");
                String collegeName = validNextString("Enter college name: ");
                member = new StudentMember(email, name, address, gender, height, startingWeight, packageChoice, studentId, collegeName);
                gymApi.addMember(member);
            }
            memberWelcomePage(member);
        }
    }

    private void login(String mOrT) {
        String email = validNextString("Enter email address: ");

        if(mOrT.equals("T")) {
            trainerWelcomePage(gymApi.searchTrainersByEmail(email));
        } else {
            memberWelcomePage(gymApi.searchMembersByEmail(email));
        }
    }

    private void trainerWelcomePage(Person trainer) {
        System.out.println("  Select an option");
        System.out.println("-----------");
        System.out.println("  1) Add a new member");
        System.out.println("  2) List all members");
        System.out.println("  3) Search for a member by email");
        System.out.println("  4) Search for a member by name");
        System.out.println("  5) List members with ideal body weight");
        System.out.println("  6) List members with a specific BMI category");
        System.out.println("  7) Assessment sub-menu");
        System.out.println("  8) Reports sub-menu");
        System.out.println("-----------");
        System.out.println("  0) Logout");

        int option = validNextInt("=>>");

        switch (option) {
            case 1:
                registration("M");
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
                String category = validNextString("Enter a BMI category: ");
                System.out.println(gymApi.listMembersBySpecificBMICategory(category));
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

    private void assessmentSubMenu(Person trainer) {
        System.out.println("  Select an option");
        System.out.println("-----------");
        System.out.println("  1) Add an assessment for a member");
        System.out.println("  2) Update comment on an assessment for a member");
        System.out.println("-----------");
        System.out.println("  0) Back to previous menu");

        int option = validNextInt("=>>");

        Trainer trainerDownCast = (Trainer) trainer;

        switch (option) {
            case 1:
                String mailSearch = validNextString("Enter a search term: ");
                Member member = gymApi.searchMembersByEmail(mailSearch);
                Date date = readValidDate("Date of assessment: ");
                double weight = validNextDouble("Weight: ");
                double chest = validNextDouble("Chest: ");
                double thigh = validNextDouble("Thigh: ");
                double upperArm = validNextDouble("Upper Arm: ");
                double waist = validNextDouble("Waist: ");
                member.addAssessment(date, new Assessment(weight, chest, thigh, upperArm, waist, trainerDownCast));
                break;
            case 2:
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

    private void reportsSubMenu(Person trainer) {
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
                String mailSearch = validNextString("Enter a search term: ");
                break;
            case 2:
                String nameSearch = validNextString("Enter a search term: ");
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
                break;
            case 2:
                break;
            case 3:
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
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
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

}

