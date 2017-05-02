package utils;

import models.Assessment;
import models.Member;
import org.jetbrains.annotations.Contract;

import java.util.Scanner;

/**
 * Created by John on 24/04/2017.
 */
public class ScannerInput {

    public static int validNextInt(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                return Integer.parseInt( input.next() );
            }
            catch (NumberFormatException e) {//catches any non-integer input, loop will run again
                System.err.println("\tEnter a valid number please.");
            }
        }
        while (true);
    }

    public static String validNextString(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }
}
