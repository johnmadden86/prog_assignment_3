package utils;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class ScannerInput {

    public static int validNextInt(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                return Integer.parseInt( input.next() );
            } catch (NumberFormatException e) {//catches any non-integer input, loop will run again
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


    @NotNull
    public static String printShortDate (Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public static String validNextString(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }
}
