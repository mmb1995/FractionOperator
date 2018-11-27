package fraction;

public class EquationManipulator {
    
    /**
     * An enum that represents the potential Operators
     * @author mmb1995
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
    
    /**
     * Returns a Fraction that represents the input from the user. 
     * Important note: mixed numbers are converted into improper fractions.
     * @param fractionString a string that may contain a fraction
     * @return
     */
    public Fraction getFraction(String fractionString) {
        
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
            
            // Fraction is a whole number
            int wholeNumber = Integer.parseInt(fractionString);
            return new Fraction(wholeNumber);  
            
        } else {
            
            // The input could not be made into a fraction
            return null;
        }      
    }
    
    /**
     * Returns an array representing the arguments contained in the given String
     * @param equationString a String that represents an equation
     * @return A Sting[] containing the parts of the equation, 
     * or an empty array if the given String is invalid
     */
    public String[] getEquation(String equationString) {
        if (equationString != null && !equationString.isEmpty()) {
            String delimitor = "[ ]+";
            String[] equation = equationString.split(delimitor);
            
            // checks if all parts of the string contain valid arguments
            if ((equation.length == 3) && (isValidFraction(equation[0]) 
                    && isValidOperator(equation[1]) && isValidFraction(equation[2]))) {
                return equation;
            }
        }
        // Returns an empty array to indicate that the equationString could not be properly parsed
        return new String[0];
    }
    
    
    /**
     * Helper method to check if a given string represents a valid fraction
     * @param fractionString
     * @return
     */
    private boolean isValidFraction(String fractionString) {
        return (isFraction(fractionString) || isMixedNumber(fractionString) || isNumber(fractionString));
    }
    
    
    /**
     * Performs the selected operation on the given fractions.
     * @param first the first passed in fraction
     * @param second the second passed in fraction
     * @param operator the selected operator
     * @return the Fraction that represents the result of the operation
     */
    public Fraction performOperation(Fraction first, Fraction second, String operator) {
        
        // Performs the given operation
        switch(getOperator(operator)) {
            case ADD:
                return first.add(second);
            case SUBTRACT:
                return first.subtract(second);
            case MULTIPLY:
                return first.multiply(second);
            case DIVIDE:
                return first.divide(second);
            default:
                return null;
            
        }
    }
    
    /**
     * Helper method to determine if a user has provided a valid operator
     * @param operator
     * @return
     */
    public boolean isValidOperator(String operatorString) {
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
    private Operator getOperator(String operatorString) {
        for (Operator operator: Operator.values()) {
            if (operator.operation.equals(operatorString)) {
                return operator;
            }
        }
        return null;
    }
    
    
    /**
     * Helper method that transforms the user input into a Fraction object
     * @param fractionString the input from the user
     * @return A Fraction object representing the input
     */
    private Fraction buildFraction(String fractionString) {
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
    private boolean isNumber(String input) {
        
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
    private boolean isFraction(String input) {
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
    private boolean isMixedNumber(String input) {
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
