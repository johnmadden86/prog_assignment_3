package controllers;

import java.util.ArrayList;

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
        runMenu();
    }

    /**
     * This method displays the login menu for the application
     * @return The user's option that will be fed through the runMenu() method
     */
    private int mainMenu() {

        System.out.println("  Gym Menu");
        System.out.println("---------");
        System.out.println("  1) Login");
        System.out.println("  2) Register");
        System.out.println("---------");
        System.out.println("  0) Exit");
        return validNextInt("==>> ");
    }

    /**
     * This method controls the loop for the 'out of game' functions
     */
    private void runMenu() {
        int mainMenuOption = mainMenu();//puts user's choice from the main menu through the switch loop
        boolean skipMainMenu = false;//keep displaying main menu until user starts the game
        while (mainMenuOption != 0) {// 0 to exit and terminate the program
            switch (mainMenuOption) {
                case 1: //login

                    break;
                case 2: //register

                    break;
                default:
                    System.out.println("Invalid option entered: " + mainMenuOption);
                    break;
            }
            pause();
            if (!skipMainMenu) { //display the main menu again if not starting game
                mainMenuOption = mainMenu();
            }
        }
        System.out.println("Exiting... bye");
        System.exit(0);//the user chose option 0, so exit the program
    }
    private void pause(){
        validNextString("Press any key to continue...\n");
    }

}

