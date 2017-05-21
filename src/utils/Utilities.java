package utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    /**
     * method to print a date in a ahorter format
     * @param date the date to print
     * @return date in the formant dd/MM/yyyy
     */
    @NotNull
    public static String printShortDate (Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    /**
     * method to parse a date from a user input
     * @param date the date to parse
     * @return parsed date
     */
    @Nullable
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
