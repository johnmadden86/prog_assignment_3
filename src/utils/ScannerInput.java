package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ScannerInput {

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

    public static String validNextString(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }

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
