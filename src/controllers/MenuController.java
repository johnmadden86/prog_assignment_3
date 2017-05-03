package controllers;

import models.Member;
import models.PremiumMember;
import models.StudentMember;
import models.Trainer;

import java.util.ArrayList;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import static utils.ScannerInput.validNextDouble;
import static utils.ScannerInput.validNextInt;
import static utils.ScannerInput.validNextString;

/**
 * Created by John on 24/04/2017.
 */
class MenuController {
    private GymApi gymApi;

    /**
     * Main method to run the program
     *
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
                    System.out.println("Exiting... bye");
                    System.exit(0);
                default:
                    System.out.println("Invalid option entered: " + option);
                    homePage();
                    break;
            }
    }

    private void memberOrTrainer(boolean reg) {
        System.out.println("  Select an option");
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
                memberOrTrainer(reg);
            }
        }
        homePage();
    }

    private void registration(String mOrT) {
        System.out.println("Enter registration details");
        pause();
        String name = validNextString("Enter name: ");
        String address = validNextString("Enter address: ");
        String email = validNextString("Enter email address: ");
        String gender = validNextString("Enter gender (M/F): ");
        if(mOrT.equals("T")){
            String speciality = validNextString("Pick a speciality: " );
            gymApi.addTrainer(new Trainer(email, name, address, gender, speciality));
            trainerWelcomePage(email);
        } else {
            double height = validNextDouble("Enter height: ");
            double startingWeight = validNextDouble("Enter starting weight: ");
            String packageChoice = validNextString("Enter package choice: ");
            if (!packageChoice.equals("Student")) {
                gymApi.addMember(new PremiumMember(email, name, address, gender, height, startingWeight, packageChoice));
            } else {
                String studentId = validNextString("Enter student ID: ");
                String collegeName = validNextString("Enter college name: ");
                gymApi.addMember(new StudentMember(email, name, address, gender, height, startingWeight, packageChoice, studentId, collegeName));
            }
            memberWelcomePage(email);
        }
    }

    private void login(String mOrT) {
        String email = validNextString("Enter email address: ");
        if(mOrT.equals("T")) {
            trainerWelcomePage(email);
        } else {
            memberWelcomePage(email);
        }
    }

    private void trainerWelcomePage(String email) {
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
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            default:
                break;
        }
    }

    private void assessmentSubMenu(String email) {
        System.out.println("  Select an option");
        System.out.println("-----------");
        System.out.println("  1) Add an assessment for a member");
        System.out.println("  2) Update commment on an assessment for a member");
        System.out.println("-----------");
        System.out.println("  0) Back to previous menu");

        int option = validNextInt("=>>");

        switch (option) {
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    private void reportsSubMenu(String email) {
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
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    private void memberWelcomePage(String email) {
        System.out.println("  Select an option");
        System.out.println("-----------");
        System.out.println("  1) View profile");
        System.out.println("  2) Update profile");
        System.out.println("  3) Progress sub-menu");
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
            default:
                break;
        }
    }

    private void progresssubMenu(String email) {
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
            default:
                break;
        }
    }

    private void pause() {
        validNextString("Press any key to continue...\n");
    }

}

