package test;

import static org.junit.Assert.assertTrue;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.IllegalArgumentException;
import org.junit.Test;

import fraction.Fraction;


public class TestFraction {
    
    @Test
    public void basicAdditionTest() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(3, 4);
        Fraction expected = new Fraction(5, 4);
        Fraction result = f1.add(f2);
        assertEquals(expected, result);    
    }
    
    @Test
    public void basicSubtractionTest() {
        Fraction f1 = new Fraction(4, 5);
        Fraction f2 = new Fraction(2, 3);
        Fraction expected = new Fraction(2, 15);
        Fraction result = f1.subtract(f2);
        assertEquals(expected, result);
    }
    
    @Test
    public void basicMultiplicationTest() {
        Fraction f1 = new Fraction(2, 7);
        Fraction f2 = new Fraction(3, 5);
        Fraction expected = new Fraction(6, 35);
        Fraction result = f1.multiply(f2);
        assertEquals(expected, result);
    }
    
    @Test
    public void basicDivisionTest() {
        Fraction f1 = new Fraction(2, 7);
        Fraction f2 = new Fraction(3, 5);
        Fraction expected = new Fraction(10, 21);
        Fraction result = f1.divide(f2);
        assertEquals(expected, result);
    }
    
    @Test
    public void testAdditionSimplifiesResult() {
        Fraction f1 = new Fraction(4, 6);
        Fraction f2 = new Fraction(5, 10);
        Fraction expected = new Fraction(7, 6);
        Fraction result = f2.add(f1);
        assertEquals(expected, result);
    }
    
    @Test
    public void testSubtractionSimplifiesResult() {
        Fraction f1 = new Fraction(4, 6);
        Fraction f2 = new Fraction(5, 10);
        Fraction expected = new Fraction(1, 6);
        Fraction result = f1.subtract(f2);
        assertEquals(expected, result);
    }
    
    @Test
    public void testAddNegativeFraction() {
        Fraction f1 = new Fraction(-1, 2);
        Fraction f2 = new Fraction(3, 4);
        Fraction expected = new Fraction (1, 4);
        Fraction result = f1.add(f2);
        assertEquals(expected, result);
        
        f1.setDenominator(-2);
        f1.setNumerator(1);
        Fraction result2 = f1.add(f2);
        assertEquals(expected, result2);
        
        f1.setNumerator(-1);
        Fraction expected2 = new Fraction(5, 4);
        Fraction result3 = f1.add(f2);
        assertEquals(expected2, result3);
        
    }
    
    @Test
    public void testAddWholeNumber() {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(5, 1);
        Fraction expected = new Fraction(11, 2);
        Fraction result = f1.add(f2);
        assertEquals(expected, result);
    }
    
    @Test
    public void testLargeNumbers() {
        Fraction f1 = new Fraction(10000, 1);
        Fraction f2 = new Fraction(100000, 1);
        Fraction expected = new Fraction(110000, 1);
        Fraction result = f1.add(f2);
        assertEquals(expected, result);
    }
    
    @Test
    public void zeroDenominatorThrowsError() {
        try {
            Fraction f1 = new Fraction(5, 0);
            fail("Expected IllegalArgumentException");
        } catch ( IllegalArgumentException ex) {
           // Do nothing we want this to happen
        }
    }
    
    @Test
    public void testSimplify() {
        Fraction f1 = new Fraction(1, 2);
        f1.simplify();
        Fraction expected = new Fraction(1, 2);
        assertEquals(f1, expected);
        
        Fraction f2 = new Fraction(2, 4);
        f2.simplify();
        assertEquals(f2, expected);
        
        Fraction f3 = new Fraction(10, 30);
        expected = new Fraction (1, 3);
        f3.simplify();
        assertEquals(f3, expected);
    }
    
    @Test
    public void testSimplifyImproperFraction() {
        Fraction f1 = new Fraction(6, 4);
        f1.simplify();
        Fraction expected = new Fraction(3, 2);
        assertEquals(f1, expected);
    }
    
    @Test
    public void testToString() {
        Fraction f1 = new Fraction(15, 8);
        String expected = "1_7/8";
        assertEquals(f1.toString(), expected);
        
        Fraction f2 = new Fraction(3,4);
        String expected2 = "3/4";
        assertEquals(f2.toString(), expected2);
    }
}
