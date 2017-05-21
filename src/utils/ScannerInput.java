package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * class for methods that require user input from the console
 */
public class ScannerInput {

    /**
     * method to check user inputs an integer
     * @param prompt prompt displayed on console
     * @return an integer
     */
    public static int validNextInt(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                return Integer.parseInt( input.next() );
            } catch (NumberFormatException e) {
                System.err.println("\tEnter a valid number please.");
            }
        } while (true);
    }

    /**
     * method to check user inputs a decimal number
     * @param prompt prompt displayed on console
     * @return decimal number
     */
    public static double validNextDouble(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                return Double.parseDouble(input.next());
            } catch (NumberFormatException e) {
                System.err.println("\tEnter a decimal number please.");
            }
        }  while (true);
    }

    /**
     * method to parse a date from user input
     * @param prompt prompt displayed on console
     * @return a date
     */
    public static Date readValidDate(String prompt) {
        Scanner input = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        do {
            try {
                System.out.print(prompt);
                return sdf.parse(input.nextLine());
            } catch (ParseException e) {
                System.err.println ("\tInvalid date format; expected dd/MM/yyyy. Try again.");
            }
        } while(true);
    }

    /**
     * method to check for a valid email format
     * @param prompt  prompt displayed on console
     * @return valid email
     */
    public static String readValidEmail(String prompt) {
        String email;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println(prompt);
            email = input.nextLine();
            if(!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.([a-zA-Z.]{2,4})$")) {
                System.out.println("Invalid email address");
            } else {
                break;
            }
        } while(true);

        return email;
    }

    /**
     * passes user input as a string
     * @param prompt prompt displayed on console
     * @return user input as a string
     */
    public static String validNextString(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }

    /**
     * method to check user inputs an integer within the bounds of an array list
     * @param prompt prompt displayed on console
     * @param arrayList the array list to check against
     * @return an integer corresponding to an index in the array list
     */
    public static int validIndex (String prompt, ArrayList arrayList) {
        do {
            int index = validNextInt(prompt);
            try {
                arrayList.get(index);
                return index;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error, invalid index");

            }
        } while (true);
    }

}
