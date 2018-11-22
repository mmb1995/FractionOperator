package fraction;

import java.util.Scanner;
import fraction.Fraction;

/**
 * This is a command line program that performs basic math operations on two Fractions.
 * The user can either pass in arguments from the command line or manually enter arguments when prompted
 * 
 * @author mmb95
 *
 */

public class Main {
    
    /**
     * An enum that represents the potential Operators
     * @author mmb36
     *
     */
    public enum Operator {
        ADD ("+"),
        SUBTRACT ("-"),
        MULTIPLY ("*"),
        DIVIDE ("/");
        
        private final String operation;
        
        private Operator(String operation) {
            this.operation = operation;
        }
    }
    

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        if (args.length == 3) {
            System.out.println("It looks like you passed in some arguments. Let me fetch those for you.");
            handlePassedInArguments(args, input);
        } else if (args.length == 0) {
            // The user did not pass in any arguments
            printGreeting();
            handleUserInput(input);
        } else {
            // User passed in an incorrect number of arguments
            handleInvalidArguments(input);
        }
        System.exit(0);

    }
    
    /**
     * Prints a greeting to the user. 
     */
    private static void printGreeting() {
        System.out.println("Welcome to the Fraction Operator!\nThis program accepts an operator (+, -, *, /) and two operands which can be in the form of fractions, "
                + "whole numbers, or mixed fractions.\nThe operator is applied to the given operands and the result is printed for you. Let's begin.");
    }
    
    /**
     * Prints the result of the operation
     * @param result a Fraction created by performing an operation on two fractions
     */
    private static void printResult(Fraction result) {
        System.out.println("The fraction returned by the operation is: " + result.toString());
    }
    
    
    /**
     * This method is called when the user attempted to pass in arguments from the command line but failed to pass them in correctly
     * @param input a scanner to read user input
     */
    private static void handleInvalidArguments(Scanner input){
        System.out.print("Oh no! It looks like I wasn't able to understand the arguments you passed in :("
                + "\nI can only handle arguments passed in a format like the following: 1/2 * 3/4"
                + "\nWould you like to manually enter in your arguments? (y to coninue, any other letter to exit): ");
        String response = input.nextLine();
        if (response.trim().equalsIgnoreCase("y")) {
            handleUserInput(input);
        } else {
            System.out.println("Thank you, ending session");
            System.exit(0);
        }
    }
    
    /**
     * This is used when arguments are passed in from the command line
     * @param args a string array containing the passed in arguments
     * @param input a scanner to read user input if necessary
     */
    public static void handlePassedInArguments(String[] args, Scanner input) {

        // try and get first fraction
        String firstFractionString = args[0];
        Fraction first = getFraction(firstFractionString);
        if (first == null) {
            handleInvalidArguments(input);
            return;
        }
        
        // Try and get operator
        String operatorString = args[1];
        
        if (!isValidOperator(operatorString)) {
            handleInvalidArguments(input);
            return;
        } 
        Operator operator = getOperator(operatorString);
        
        // Try and get second fraction
        String secondFractionString = args[2];
        Fraction second = getFraction(secondFractionString);
        
        if (second == null) {
            handleInvalidArguments(input);
            return;
        }
        
        // Arguments were successfully passed in
        System.out.println("Woho! It looks like everything was passed in correctly."
                + "\nYour equation is: " + first.toString() + " " + operator.operation + " " + second.toString());
        
        // Perform the operation on the fractions
        Fraction result = performOperation(first, second, operator);
        
        // Print out the results to the user
        printResult(result);   
    }
    
    /**
     * Prompts the user for input and uses the values given by the user to perform an operation on two fractions
     * @param input a Scanner to read in user input
     */
    public static void handleUserInput(Scanner input) {
        Fraction firstFraction;
        boolean valid = false;
        do {
            // prompt the user for the first fraction
            System.out.print("Please enter the value for the first fraction in one of the following"
                    + " formats two integers (x/y), an integer x, or a mixed number (a_x/y): ");
            String firstFractionString = input.nextLine().trim();
            
            firstFraction = getFraction(firstFractionString);
            if (firstFraction != null) {
                valid = true;
            }                
        } while(!valid);
        
        Operator operator = null;
        valid = false;
        do {
            // Prompt the user for an operator
            System.out.print("Please select an operator (+, -, *, /): ");
            String operatorString = input.nextLine().trim();
            if (isValidOperator(operatorString)) {
                // The operator is valid
                valid = true;
                operator = getOperator(operatorString);
            }
            
        } while(!valid);
        
        Fraction secondFraction;
        valid = false;
        do {
            // Prompt the user for the second fraction
            System.out.print("Please enter the value for the second fraction in one of the following"
                    + " formats two integers (x/y), an integer x, or a mixed number (a_x/y): ");
            String secondFractionString = input.nextLine().trim();
            
            secondFraction = getFraction(secondFractionString);
            
            if(secondFraction != null) {
                valid = true;
            }
        
        } while (!valid);
         
        // Perform the operation and print out the result
        Fraction result = performOperation(firstFraction, secondFraction, operator);
        printResult(result);
    }
    
    
    
    /**
     * Performs the selected operation on the given fractions
     * @param first the first passed in fraction
     * @param second the second passed in fraction
     * @param operator the selected operator
     * @return the Fraction that represents the result of the operation
     */
    public static Fraction performOperation(Fraction first, Fraction second, Operator operator) {
        
        // Perform the operation specified by the user
        switch(operator) {
            case ADD:
                return first.add(second);
            case SUBTRACT:
                return first.subtract(second);
            case MULTIPLY:
                return first.multiply(second);
            case DIVIDE:
                return first.divide(second);
            default:
                break;
        
        }
        return null;
    }
    
    /**
     * Helper method to determine if a user has provided a valid operator
     * @param operator
     * @return
     */
    private static boolean isValidOperator(String operatorString) {
        for (Operator operator: Operator.values()) {
            if (operator.operation.equals(operatorString)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Helper method that returns the selected operator
     * @param operatorString input from the user
     * @return the selected Operator
     */
    private static Operator getOperator(String operatorString) {
        for (Operator operator: Operator.values()) {
            if (operator.operation.equals(operatorString)) {
                return operator;
            }
        }
        return null;
    }
    
    
    /**
     * Returns a Fraction that represents the input from the user
     * @param fractionString a string that may contain a fraction
     * @return
     */
    public static Fraction getFraction(String fractionString) {
        
        if (isMixedNumber(fractionString)) {
            
            // Builds a mixed number into an improper fraction
            int underScoreIndex = fractionString.indexOf("_");
            int wholeNumber = Integer.parseInt(fractionString.substring(0,underScoreIndex));
            
            // Convert whole number to a fraction
            Fraction convertedFraction = new Fraction(wholeNumber);
            
            // Build the rest of the string into a fraction
            Fraction rest = buildFraction(fractionString.substring(underScoreIndex + 1));
            
            // Add the Fractions together and return the result
            return convertedFraction.add(rest);
            
        } else if (isFraction(fractionString)) {
            // builds the fraction
            return buildFraction(fractionString);
            
        } else if (isNumber(fractionString)) {
            int wholeNumber = Integer.parseInt(fractionString);
            return new Fraction(wholeNumber);  
            
        } else {
            // The input could not be made into a fraction
            return null;
        }      
    }
    
    /**
     * Helper method that transforms the user input into a Fraction object
     * @param fractionString the input from the user
     * @return A Fraction object representing the input
     */
    private static Fraction buildFraction(String fractionString) {
        int slashIndex = fractionString.indexOf("/");
        int numerator = Integer.parseInt(fractionString.substring(0, slashIndex));
        int denominator = Integer.parseInt(fractionString.substring(slashIndex + 1, 
                fractionString.length()));
        return new Fraction(numerator, denominator);
    }
    
    
    /**
     * Helper method to check that the input string contains valid numbers
     * @param input
     * @return true/false
     */
    private static boolean isNumber(String input) {
        
        // Ternary expression that basically is just checking if the number could potentially be negative
        int index = input.charAt(0) == '-' ? 1 : 0;

        for (int i = index; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Helper method that checks if the given input contains a valid fraction
     * @param input
     * @return true if the input contains a fraction, false otherwise
     */
    private static boolean isFraction(String input) {
        int slashIndex = input.indexOf("/");
        
        if (slashIndex != -1) {
            
            if (input.charAt(0) == '-') {
                
                // handles negative numbers
                return (isNumber(input.substring(1, slashIndex)) 
                        && isNumber(input.substring(slashIndex + 1, input.length())));
            } else {
                
                // Check that the fraction contains two valid numbers
                return (isNumber(input.substring(0, slashIndex)) && 
                        isNumber(input.substring(slashIndex + 1, input.length())));
            }
        } else {
            
            // not a fraction
            return false;
        }
    }
    
    /**
     * Helper method to determine if a string contains a valid mixed fraction
     * @param input
     * @return
     */
    private static boolean isMixedNumber(String input) {
        int underScoreIndex = input.indexOf("_");
        
        if (underScoreIndex != -1) {
            
            // check for valid mixed number
            if (input.charAt(0) == '-') {
                
                // handles negative numbers
                return (isNumber(input.substring(1, underScoreIndex)) &&
                        isFraction(input.substring(underScoreIndex + 1, input.length())));
            } else {
                return (isNumber(input.substring(0,  underScoreIndex)) 
                        && isFraction(input.substring(underScoreIndex + 1, input.length())));
            }
        }
        
        return false;
    }

}
