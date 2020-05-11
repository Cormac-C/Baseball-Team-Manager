/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cureton_cormac_baseballculminating;

import java.util.*;

/**
 * A class containing some helpful methods to be used across other classes in the project
 * @author Owner
 */
public class UsefulMethods {
    /**
     * Rounds a given number to a given number of decimal places
     * @param num An unrounded double value
     * @param numDecimalPlaces The number of decimal places to be rounded to
     * @return The rounded value
     */
    public static double round (double num, int numDecimalPlaces){
        num = num * Math.pow(10, numDecimalPlaces);
        return Math.round(num) / Math.pow(10, numDecimalPlaces);
    }
    
    /**
     * Prompts the user for an int with a given method, continues until it receives a valid int within the given range
     * @param input The scanner used to get information from the user
     * @param message The prompt to be displayed
     * @param min The minimum accepted value
     * @param max The maximum accepted value
     * @return The appropriate user input
     */
    public static int getInt(Scanner input, String message, int min, int max) {
        int n = 0;
        while (true) {
            try {
                System.out.println(message);
                n = Integer.parseInt(input.nextLine());
                if (n >= min && n <= max) {
                    return n;
                } else {
                    System.out.println("Invalid range, must be between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Not an integer");
            }
        }
    }   
}
