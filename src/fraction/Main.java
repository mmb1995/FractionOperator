package fraction;

import java.util.Scanner;
import fraction.Fraction;

/**
 * This is a command line program that performs basic math operations on two Fractions.
 * The user can either pass in arguments from the command line or manually enter arguments when prompted.
 * A valid equation consists of two fractions in one the following formats, an Integer (x), a fraction (x/y), or a mixed number (a_x/y),
 * and an operator (+, -, *, /).
 * 
 * @author mmb1995
 *
 */
public class Main {
    
    public static void main(String[] args) {
        // Gets an EquationManipulator that will handle the operation logic
        EquationManipulator manipulator = new EquationManipulator();
        
        if (args.length == 3) {
            // arguments were passed in from the command line
            System.out.println("It looks like you passed in some arguments. Let me fetch those for you.");
            checkPassedInArguments(args, manipulator);
        } else if (args.length != 0) {
            // User passed in an incorrect number of arguments
            printErrorMessage();
            handleUserInput(manipulator);
        } else { 
            // User did not pass in any arguments
            handleUserInput(manipulator);
        }

    }
     
    /**
     * Prints the result of the operation
     * @param result a Fraction created by performing an operation on two fractions
     */
    private static void printResult(Fraction result) {
        System.out.println("The fraction returned by the operation is: " + result.toString());
    }
    
    /**
     * Prints an error message when the program is unable to correctly parse the user's input
     */
    private static void printErrorMessage() {
        System.out.println("Oh no! It looks like I wasn't able to understand the arguments you passed in :("
                + "\nI can only handle arguments passed in a format like the following: 1/2 * 3/4\n");
    }
    
    /**
     * This gets called when arguments are passed in from the command line.
     * This method checks if the arguments were passed in correctly or if the user will have to manually enter them in
     * @param args
     * @param manipulator
     */
    private static void checkPassedInArguments(String[] args, EquationManipulator manipulator) {
        
        // Convert arguments to a String
        StringBuilder builder = new StringBuilder();
        for (String argument: args) {
            builder.append(argument + " ");
        }
        
        System.out.println("Command line equation: " + builder.toString());
        // check if equation is valid
        String[] equation = manipulator.getEquation(builder.toString());
        if (equation.length == 0) {
            // unable to parse passed in arguments
            printErrorMessage();
            handleUserInput(manipulator);
        } else {
            // arguments were passed in correctly
            System.out.println("Wohoo! It looks like everything was passed in correctly!"
                    + "\nYour equation is: " + builder.toString() + "\n");
            handleArguments(equation, manipulator);
        }
    }
    
    /**
     * This prompts the user for input and uses that input to build a valid equation to act on.
     * @param manipulator
     * @return A String[] containing the valid components of an equation
     */
    private static String[] buildEquation(EquationManipulator manipulator) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter in an equation in the following format: 1/2 * 3/4. "
                + "\nThe operands can take one of the following formats: an integer (x), a fraction (x/y), or a mixed number (a_x/y)"
                + "\nThe operator can be one of the following (+, -, *, /)"
                + "\nPlease seperate each input with a space."
                );
        
        String response;
        String[] equation;
        // This runs until the user enters in a valid equation
        do {
            System.out.print("Enter your equation or enter q to exit: ");
            // Gets the response from the user
            response = input.nextLine().trim();
            
            // Checks if the user wishes to end the program
            if (response.equalsIgnoreCase("q")) {
                quitProgram(input);
            }
            
            // Gets the arguments contained in the user's response
            equation = manipulator.getEquation(response);
            
            // Makes sure the response is valid
            if (equation.length == 0) {
                System.out.println("There was a problem reading your equation. Please try again.");
            }
        } while (equation.length == 0);
        
        // returns an array containing the valid equation
        return equation;
    }
    
    
    /**
     * This is used when arguments are passed in from the command line
     * @param args a string array containing the passed in arguments
     * @param input a scanner to read user input if necessary
     */
    public static void handleArguments(String[] args, EquationManipulator manipulator) {
        
        // Grab the arguments
        Fraction firstFraction = manipulator.getFraction(args[0]);
        String operator = args[1];
        Fraction secondFraction = manipulator.getFraction(args[2]);
        
        // Perform the operation and get the results
        Fraction result = manipulator.performOperation(firstFraction, secondFraction, operator);
        
        // Report the result to the user
        printResult(result);
    }
    
    /**
     * Handles getting the arguments from the user
     * @param an EquationManipulator to handle the logic of dealing with the users input
     */
    public static void handleUserInput(EquationManipulator manipulator) {
        Scanner input = new Scanner(System.in);
        
        while(true) {
            // Gets the arguments from the equation
            String[] equation = buildEquation(manipulator);
            
            // Perform the operation on the equation
            handleArguments(equation, manipulator);
             
            // See if the user wants to continue
            System.out.print("Would you like to continue? (y to continue, any other letter to exit): ");
            if (!input.nextLine().trim().equalsIgnoreCase("y")) {
                quitProgram(input);
            }
        }
    }  
    
    /**
     * Ends the program when prompted by the user
     * @param input a Scanner
     */
    private static void quitProgram(Scanner input) {
        System.out.println("Thank you, ending session.");
        input.close();
        System.exit(0);
    }

}
