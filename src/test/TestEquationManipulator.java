package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import fraction.Fraction;
import fraction.EquationManipulator;

import org.junit.Test;

public class TestEquationManipulator {
    
    @Test
    public void testGetEquationReturnsValidEquation() {
        EquationManipulator em = new EquationManipulator();
        String validEquation = "1/2 + 3/4";
        String[] equation = em.getEquation(validEquation);        
        assertTrue(equation.length == 3);
        
        String[] expected = new String[] {"1/2","+","3/4"};
        assertEquals(expected, equation);
    }
    
    @Test
    public void testGetEquationIsEmptyOnInvalidEquation() {
        EquationManipulator em = new EquationManipulator();
        String invalidEquation = "1/2 + f3/4";
        String[] equation = em.getEquation(invalidEquation);        
        assertTrue(equation.length == 0);
        
    }
    
    @Test
    public void testGetEquationWorksWithMultipleSpaces() {
        EquationManipulator em = new EquationManipulator();
        String validEquation = "1/2  +                                 3/4";
        String[] equation = em.getEquation(validEquation);        
        assertTrue(equation.length == 3);
        
        String[] expected = new String[] {"1/2","+","3/4"};
        assertEquals(expected, equation);
    }
    
    @Test
    public void testGetEquationWorksWithNegatives() {
        EquationManipulator em = new EquationManipulator();
        String validEquation = "-1/2 + 3/4";
        String[] equation = em.getEquation(validEquation);        
        assertTrue(equation.length == 3);
        
        String[] expected = new String[] {"-1/2","+","3/4"};
        assertEquals(expected, equation);
    }
    
    @Test
    public void testGetEquationIsEmptyOnIncorrectArgLength() {
        EquationManipulator em = new EquationManipulator();
        String invalidEquation = "1/2 + 3/4 -";
        String[] equation = em.getEquation(invalidEquation);        
        assertTrue(equation.length == 0);
        
        invalidEquation = "1/2 + ";
        equation = em.getEquation(invalidEquation);
        assertTrue(equation.length == 0);
        
    }
    
    @Test
    public void testGetEquationIsEmptyOnEmptyStringOrNull() {
        EquationManipulator em = new EquationManipulator();
        String invalidEquation = "";
        String[] equation = em.getEquation(invalidEquation);        
        assertTrue(equation.length == 0);
        
        invalidEquation = null;
        equation = em.getEquation(invalidEquation);
        assertTrue(equation.length == 0);
    }
    
    @Test
    public void testPerformOperationBasicMath() {
        EquationManipulator em = new EquationManipulator();
        Fraction first = new Fraction(1,2);
        Fraction second = new Fraction(1,4);
        
        Fraction result = em.performOperation(first, second, "+");
        System.out.println("Result: " + result.toString());
        Fraction expected = new Fraction(3,4); 
        assertEquals(result, expected);
        
        expected = new Fraction(1,4);
        result = em.performOperation(first, second, "-");
        System.out.println("Result: " + result.toString());
        assertEquals(result, expected);
        
        expected = new Fraction(1,8);
        result = em.performOperation(first, second, "*");
        System.out.println("Result: " + result.toString());
        assertEquals(result, expected);
        
        expected = new Fraction(2, 1);
        result = em.performOperation(first, second, "/");
        System.out.println("Result: " + result.toString());
        assertEquals(expected, result);
    }
    

}
