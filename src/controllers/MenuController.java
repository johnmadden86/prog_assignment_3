package controllers;
import models.*;

import com.google.gson.*;

import java.io.*;
import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import static utils.Analytics.*;
import static utils.ScannerInput.*;

/**
 * Runs the program from the console
 * https://github.com/johnmadden86/prog_assignment_3
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
            load();//load upon startup
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
        homePage();//launch home-page
    }

    /**
     * This method controls the loop for the homepage functions
     */
    private void homePage() {
        System.out.println("  Gym Menu");
        System.out.println("-----------");
        System.out.println("  L) Login");
        System.out.println("  R) Register");
        System.out.println("-----------");
        System.out.println("  X) Exit");

        //puts user's choice from the main menu through the switch loop
        String option = toUpperCase(validNextString("==>> "));

            switch (option) {
                case "L": //login
                    if (gymApi.getPersons().isEmpty()) {
                        System.out.println("Error, no people registered");
                        homePage();
                    } else {
                        login();
                    }
                    break;
                case "R": //register
                    registration(false, null);
                    break;
                case "X":
                    try {
                        save();
                    } catch (Exception e) {
                        System.err.println("Error writing to file: " + e);
                    }
                    System.out.println("Exiting... bye");
                    System.exit(0);
                default:
                    System.out.println("Invalid option entered: " + option);
                    pause();
                    homePage();
                    break;
            }
    }

    /**
     * Method to allow a user to register as a member or trainer
     * @param addingByTrainer true to indicate a member is being added by a trainer
     * @param loggedInTrainer keeps the same trainer logged in after exiting this menu
     */
    private void registration(boolean addingByTrainer, Trainer loggedInTrainer) {
        String mOrT;
        if (!addingByTrainer) {//doesn't run if adding by trainer
        System.out.println("\n  Select an option");
        System.out.println("-----------");
        System.out.println("  M) Member");
        System.out.println("  T) Trainer");
        System.out.println("-----------");
        System.out.println("  B) Back to previous menu");
        mOrT = toUpperCase(validNextString("==>> "));
        } else {
            mOrT = "M";//skips to adding a member when adding by trainer
        }

        while (!mOrT.equals("B")){
            String name = validNextString("Enter name: ");
            String address = validNextString("Enter address: ");
            String email = readValidEmail("Enter email address: ");
            String gender = validNextString("Enter gender (M/F): ");
            switch (mOrT) {
                case "T":
                    String speciality = validNextString("Pick a speciality: " );
                    Trainer trainer = new Trainer(email, name, address, gender, speciality);
                    gymApi.addTrainer(trainer);
                    trainerWelcomePage(trainer);
                    break;
                case "M":
                    Member member;
                    double height = validNextDouble("Enter height (1m min, 3m max): ");
                    double startingWeight = validNextDouble("Enter starting weight (35kg min, 250kg max): ");
                    String packageChoice = validNextString("Enter package choice (Premium/Student/Basic): ");
                    switch (packageChoice) {
                        case "Premium":
                            member = new PremiumMember(email, name, address,
                                    gender, height, startingWeight, "Package 1");
                            gymApi.addMember(member);
                            break;
                        case "Student":
                            String studentId = validNextString("Enter student ID: ");
                            String collegeName = validNextString("Enter college name: ");
                            member = new StudentMember(email, name, address,
                                    gender, height, startingWeight, packageChoice, studentId, collegeName);
                            gymApi.addMember(member);
                            break;
                        default:
                            member = new PremiumMember(email, name, address,
                                    gender, height, startingWeight, "Package 2");
                            gymApi.addMember(member);
                        break;
                    }
                    if (addingByTrainer) {//back to trainer page if adding by trainer
                        trainerWelcomePage(loggedInTrainer);
                    } else {//to member page if addign by member
                        memberWelcomePage(member);
                    }
                default:
                    System.out.println("Invalid option entered: " + mOrT);
                    pause();
                    registration(addingByTrainer, loggedInTrainer);
            }
        }
        homePage();
    }

    /**
     * Method to allow a user to login to the program
     */
    private void login() {
        String email = readValidEmail("Enter email address: ");
        Person person = gymApi.searchPersonsByEmail(email);
        //instance check on person
        if (person instanceof Trainer) {
            trainerWelcomePage((Trainer) person);
        } else if (person instanceof Member) {
            memberWelcomePage((Member) person);
        } else {
            System.out.println("Invalid email entered: " + email);
            pause();
            login();
        }
    }

    /**
     * Controls the loop for the menu after logging in as a trainer
     * @param trainer the trainer logged in
     */
    private void trainerWelcomePage(Trainer trainer) {
        System.out.println("  Select an option");
        System.out.println("-----------");
        System.out.println("  1) Add a new member");
        System.out.println("  2) List all members");
        System.out.println("  3) Search for a member by email");
        System.out.println("  4) Search for a member by name");
        System.out.println("  5) List members with ideal body weight");
        System.out.println("  6) List members with a specific BMI category");
        System.out.println("  7) Assessment test sub-menu");
        System.out.println("  8) Reports sub-menu");
        System.out.println("-----------");
        System.out.println("  0) Logout");
        int option = validNextInt("=>>");

        switch (option) {
            case 1:
                registration(true, trainer);
                break;
            case 2:
                System.out.println(gymApi.listMembers());
                break;
            case 3:
                String mailSearch = readValidEmail("Enter an email address: ");
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
                System.out.println(listBmiCategories());
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

    /**
     * Controls the loop for the assessment sub-menu
     * @param trainer the trainer logged in
     */
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
                String mailSearch = readValidEmail("Enter email address: ");
                Member member = gymApi.searchMembersByEmail(mailSearch);
                if (member != null) {
                    Date date = readValidDate("Date of assessment (dd/mm/yyyy): ");
                    double weight = validNextDouble("Weight: ");
                    double chest = validNextDouble("Chest: ");
                    double thigh = validNextDouble("Thigh: ");
                    double upperArm = validNextDouble("Upper Arm: ");
                    double waist = validNextDouble("Waist: ");
                    double hips = validNextDouble("Hips: ");
                    String comment = validNextString("Enter comment: ");
                    member.addAssessment(date,
                            new Assessment(weight, chest, thigh, upperArm, waist, hips, trainer, comment));
                } else {
                    System.out.println("No member with this email");
                }
                break;
            case 2:
                String emailSearch = readValidEmail("Enter email address: ");
                Member memberToUpdate = gymApi.searchMembersByEmail(emailSearch);
                if (memberToUpdate != null) {
                    System.out.println(memberToUpdate.listAssessmentDates());
                    Date dateToUpdate = readValidDate("Enter date of assessment to update (dd/mm/yyyy): ");
                    String newComment = validNextString("Enter new comment: ");
                    memberToUpdate.getAssessments().get(dateToUpdate).setComment(newComment);
                } else {
                    System.out.println("No member with this email");
                }
                break;
            case 0:
                trainerWelcomePage(trainer);
                break;
            default:
                System.out.println("Invalid option entered: " + option);
                break;
        }
        pause();
        assessmentSubMenu(trainer);
    }

    /**
     * Controls the loop for the reports sub-menu
     * @param trainer the trainer logged in
     */
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
                String mailSearch = readValidEmail("Enter email address: ");
                Member member = gymApi.searchMembersByEmail(mailSearch);
                if (member != null) {
                    System.out.println(member.specificMemberProgress());
                } else {
                    System.out.println("No member with this email");
                }
                break;
            case 2:
                String nameSearch = validNextString("Enter name: ");
                System.out.println(gymApi.searchMembersByName(nameSearch));
                if (!gymApi.searchMembersByName(nameSearch).equals("No members")) {
                    int index = validIndex("Enter index: ", gymApi.getSelectedMembers());
                    Member member1 = gymApi.getSelectedMembers().get(index);
                    System.out.println(member1.specificMemberProgress());
                }
                break;
            case 3:
                System.out.println(gymApi.listMemberDetailsImperialAndMetric());
                break;
            case 0:
                trainerWelcomePage(trainer);
                break;
            default:
                System.out.println("Invalid option entered: " + option);
                break;
        }
        pause();
        reportsSubMenu(trainer);
    }

    /**
     * Controls the loop for the menu after logging in as a member
     * @param member the member logged in
     */
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
                progressSubMenu(member);
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

    /**
     * Method to display a member's details
     * @param member the member logged in
     */
    private void viewMemberProfile(Member member) {
        if (member instanceof StudentMember) {
            StudentMember studentMember = (StudentMember) member;
            System.out.println(studentMember.toString());
        }
        System.out.println(member.toString());
        pause();
    }

    /**
     * Method to allow a member to update their details
     * @param member the member logged in
     */
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
        System.out.println("  7) Update package choice");
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
                member.setEmail(readValidEmail("Enter email address: "));
                break;
            case 4:
                member.setGender(validNextString("Enter gender (M/F): "));
                break;
            case 5:
                member.setHeight(validNextDouble("Enter height (m): "));
                break;
            case 6:
                member.setStartingWeight(validNextDouble("Enter starting weight (kg): "));
                break;
            case 7:
                member.setChosenPackage(validNextString("Enter package choice (Premium/Student/Basic): "));
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

    /**
     * Controls the loop for the progress sub-menu
     * @param member the member logged in
     */
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
                System.out.println(member.getWeightProgress());
                break;
            case 2:
                System.out.println(member.getChestProgress());
                break;
            case 3:
                System.out.println(member.getThighProgress());
                break;
            case 4:
                System.out.println(member.getUpperArmProgress());
                break;
            case 5:
                System.out.println(member.getWaistProgress());
                break;
            case 6:
                System.out.println(member.getHipsProgress());
                break;
            case 0:
                memberWelcomePage(member);
            default:
                System.out.println("Invalid option entered: " + option);
                break;
        }
        pause();
        progressSubMenu(member);
    }

    /**
     * Saves new input information
     * @throws Exception to be thrown
     */
    private void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("gymapp.xml"));
        out.writeObject(gymApi);
        out.close();
        try (Writer writer = new FileWriter("gymapp.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(gymApi, writer);
        }
    }

    /**
     * Loads previously saved data
     * @throws Exception to be thrown
     */
    private void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("gymapp.xml"));
        gymApi = (GymApi) is.readObject();
        is.close();
    }

    /**
     * Helper method to insert a pause when running the program
     */
    private void pause() {
        validNextString("Press any key to continue...\n");
    }
}

