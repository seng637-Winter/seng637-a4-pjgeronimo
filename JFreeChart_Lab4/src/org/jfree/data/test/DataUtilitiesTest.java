package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;
import java.util.List;

public class DataUtilitiesTest {

    //calculateColumnTotal***********************************************************

    @Test
    public void calculateColumnTotalForTwoValues() {
        // setup

    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(7.5, 0, 0);
    	values.addValue(2.5, 1, 0);
    	
        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals(10.0, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
    @Test
    public void calculateColumnTotalForTwoNegValues() {
        // setup
    
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(-7.5, 0, 0);
    	values.addValue(-2.5, 1, 0);

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals(-10.0, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
    @Test
    public void calculateColumnTotalForTwoOppositeSignedValues() {
        // setup
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(-7.5, 0, 0);
    	values.addValue(2.5, 1, 0);
    	
        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals(-5, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
    @Test
    public void calculateColumnTotalForTwoZeros() {
        // setup
    	
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(0, 0, 0);
    	values.addValue(0, 1, 0);

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals(0, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
    
    @Test // this test was added during the completion assignment 3
    public void calculateColumnTotalForChosenRows() {
    	
    	// in this test, we call calculateColumnTotal with a different signature.
    	// we pass in an array that consists of the rows we want to add.
    	// in this case, let's only add the first two rows.
    	
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(5, 0, 0);
    	values.addValue(5, 1, 0);
    	values.addValue(5, 2, 0);
    	
    	int rowsToAdd [] = {0, 1};
    	
    	double result = DataUtilities.calculateColumnTotal(values, 0, rowsToAdd);
    	
    	assertEquals(10, result, .000000001d);
    }
    
    @Test // this test was added during the completion assignment 3
    public void calculateColumnTotalForNulls1() {
    	
    	// this test tries to add nulls using the first signature of calculateColumnTotal
    	// ie, there is no array of rows to add
    	
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(null, 0, 0);
    	values.addValue(null, 1, 0);
    	
    	double result = DataUtilities.calculateColumnTotal(values, 0);
    	
    	assertEquals(0, result, .000000001d);
    }
    
    @Test // this test was added during the completion assignment 3
    public void calculateColumnTotalForNulls2() {
    	
    	// this test tries to add nulls using the second signature of calculateColumnTotal
    	// we pass in an array of rows to add
    	
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(null, 0, 0);
    	values.addValue(null, 1, 0);
    	values.addValue(5, 2, 0);
    	
    	int rowsToAdd [] = {0, 1};
    	
    	double result = DataUtilities.calculateColumnTotal(values, 0 , rowsToAdd);
    	
    	assertEquals(0, result, .000000001d);
    }
    
    @Test // this test was added during the completion assignment 3
    public void calculateColumnTotalWithInvalidRows() {
    	
    	// this test tries to add nulls using the second signature of calculateColumnTotal
    	// we pass in an array of rows to add
    	// this time, the array is invalid, meaning it has an entry that does not exist in values
    	
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(5, 0, 0);
    	values.addValue(5, 1, 0);
    	values.addValue(5, 2, 0);
    	
    	// lets try to add rows 0, 1, 3
    	// but there is no row 3 in values
    	// code should skip over the nonexistent 3rd row, and only add rows 0 and 1
    	int rowsToAdd [] = {0, 1, 3};
    	
    	double result = DataUtilities.calculateColumnTotal(values, 0 , rowsToAdd);
    	
    	assertEquals(10, result, .000000001d);
    }

    //calculateRowTotal***********************************************************

    @Test
    public void calculateRowForTwoValues() {
        // setup
    	
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(7.5, 0, 0);
    	values.addValue(2.5, 0, 1);

        // exercise
        double result = DataUtilities.calculateRowTotal(values, 0);
        
        // verify
        assertEquals(10, result, .000000001d);

        // tear-down: NONE in this test method
    }

    // Test for a row with two negative values
    @Test
    public void calculateRowForTwoNegValues() {
        // setup
        
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(-7.5, 0, 0);
    	values.addValue(-2.5, 0, 1);


        // exercise
        double result = DataUtilities.calculateRowTotal(values, 0);

        // verify
        assertEquals(-10.0, result, .000000001d);

        // tear-down: NONE in this test method
    }

    // Test for a row with two opposite signed values
    @Test
    public void calculateRowForTwoOppositeSignedValues() {
        // setup
    	
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(-7.5, 0, 0);
    	values.addValue(2.5, 0, 1);

        // exercise
        double result = DataUtilities.calculateRowTotal(values, 0);

        // verify
        assertEquals(-5, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
    @Test // this test was added during assignment 3
    public void calculateRowForNulls1() {
    	
    	// this test uses the first signature of calculateRowTotal
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(null, 0, 0);
    	values.addValue(null, 0, 1);
    	values.addValue(3, 0, 2);
    	
    	double result = DataUtilities.calculateRowTotal(values, 0);
    	
    	assertEquals(3, result, .000000001d);
    }
    
    @Test // this test was added during assignment 3
    public void calculateRowForNulls2() {
    	
    	// this test uses the second signature of calculateRowTotal
    	// pass in an array of columns to be used
    	
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(null, 0, 0);
    	values.addValue(null, 0, 1);
    	values.addValue(3, 0, 2);
    	
    	int [] colsToUse = {1, 2};
    	
    	double result = DataUtilities.calculateRowTotal(values, 0, colsToUse);
    	
    	assertEquals(3, result, .000000001d);
    }
    
    @Test // this test was added during assignment 3
    public void calculateRowForChosenCols() {
    	
    	// this test is similar to calculateColumnTotalForChosenRows
    	// we call calculateRowTotal and pass in an additional parameter,
    	// which is an array representing only the columns we wish to add
    	
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(5, 0, 0);
    	values.addValue(5, 0, 1);
    	values.addValue(5, 0, 2);
    	
    	// let's only add the first 2 columns
    	int colsToAdd [] = {0, 1};
    	
    	double result = DataUtilities.calculateRowTotal(values, 0, colsToAdd);
    	
    	assertEquals(10, result, .000000001d);
    }
    
    @Test // this test was added during assignment 3
    public void calculateRowWithInvalidCols() {
    	// in this test, we use the second signature of calculateRowTotal
    	// and we pass in an array of columns to use
    	// but it contains an index of a column that does not exist
    	
    	DefaultKeyedValues2D values = new DefaultKeyedValues2D();
    	values.addValue(5, 0, 0);
    	values.addValue(-1, 0, 1);
    	values.addValue(7, 0, 2);
    	
    	// let's attempt to add columns 0, 1, 2, 3
    	int colsToAdd [] = {0, 1, 2, 3};
    	
    	double result = DataUtilities.calculateRowTotal(values, 0, colsToAdd);
    	
    	assertEquals(11, result, .000000001d);
    }
    //CreateNumberArray***********************************************************
    
    // Test for a normal case with multiple values
    @Test
    public void testCreateNumberArrayWithNonEmptyArray() {
        // setup
        double[] data = {1.0, 2.0, 3.0};
        Number[] expected = {1.0, 2.0, 3.0};

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    // Test for a boundary case with a single element 
    @Test
    public void testCreateNumberArrayWithSingleElement() {
        // setup
        double[] data = {1.0};
        Number[] expected = {1.0};

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    
    // Test for handling decimal values
    @Test
    public void testCreateNumberArrayWithDecimalValues() {
        // setup
        double[] data = {1.5, 2.75, 3.125};
        Number[] expected = {1.5, 2.75, 3.125};

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    // Test for an empty array case 
    @Test
    public void testCreateNumberArrayWithEmptyArray() {
        // setup
        double[] data = new double[0];
        Number[] expected = new Number[0];

        // exercise
        Number[] result = DataUtilities.createNumberArray(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }

    // Test for handling null input 
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArrayWithNullArray() {
        // setup
        // No setup needed for this test

        // exercise and verify
        DataUtilities.createNumberArray(null);

        // tear-down: NONE in this test method
    }
    
    //CreateNumberArray2D***********************************************************

    // Test for a normal 2D array with multiple values in each inner array
    @Test
    public void testCreateNumberArray2DWithNonEmptyArray() {
        // setup
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] expected = {{1.0, 2.0}, {3.0, 4.0}};

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    // Test for a boundary case with a single element in a 2D array
    @Test
    public void testCreateNumberArray2DWithSingleElement() {
        // setup
        double[][] data = {{1.0}};
        Number[][] expected = {{1.0}};

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    // Test for handling uneven arrays 
    @Test
    public void testCreateNumberArray2DWithUnevenArray() {
        // setup
        double[][] data = {{1.0, 2.0}, {3.0}};
        Number[][] expected = {{1.0, 2.0}, {3.0}};

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }

    // Test for 2D arrays with empty inner arrays 
    @Test
    public void testCreateNumberArray2DWithEmptyInnerArrays() {
        // setup
        double[][] data = {new double[0], new double[0]};
        Number[][] expected = {new Number[0], new Number[0]};

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }
    
    // Test for entirely empty 2D array 
    @Test
    public void testCreateNumberArray2DWithEmptyArray() {
        // setup
        double[][] data = new double[0][0];
        Number[][] expected = new Number[0][0];

        // exercise
        Number[][] result = DataUtilities.createNumberArray2D(data);

        // verify
        assertArrayEquals(expected, result);

        // tear-down: NONE in this test method
    }

    // Test for handling null input for 2D arrays
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DWithNullArray() {
        DataUtilities.createNumberArray2D(null);
    }

    //getCumulativePercentages***********************************************************

    // Test for a normal case with single value
    @Test
    public void testGetCumulativePercentagesWithSingleValue() {
        // setup
        // setup
        DefaultKeyedValues keyedValues = new DefaultKeyedValues();
        keyedValues.addValue("Item 1", 5.0);

        double[] expected = {1.0};


        // exercise
        KeyedValues resultKeyedValues = DataUtilities.getCumulativePercentages(keyedValues);
        double[] result = new double[resultKeyedValues.getItemCount()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultKeyedValues.getValue(i).doubleValue();
        }

        // verify
        assertArrayEquals(expected, result, .000000001d);

        // tear-down: NONE in this test method
    }

    // Test for a normal case with multiple values
    @Test
    public void testGetCumulativePercentagesWithMultipleValues() {
        // setup
        DefaultKeyedValues keyedValues = new DefaultKeyedValues();
        keyedValues.addValue("Item 1", 1.0);
        keyedValues.addValue("Item 2", 2.0);
        keyedValues.addValue("Item 3", 3.0);

        double[] expected = {(double) 1.0/6.0, (double) 3.0/6.0, 1.0};

        // exercise
        KeyedValues resultKeyedValues = DataUtilities.getCumulativePercentages(keyedValues);
        double[] result = new double[resultKeyedValues.getItemCount()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultKeyedValues.getValue(i).doubleValue();
        }

        // verify
        assertArrayEquals(expected, result, .000000001d);

        // tear-down: NONE in this test method
    }

    // Test for handling decimal values
    @Test
    public void testGetCumulativePercentagesWithDecimalValues() {
        // setup
        DefaultKeyedValues keyedValues = new DefaultKeyedValues();
        keyedValues.addValue("Item 1", 1.5);
        keyedValues.addValue("Item 2", 2.75);
        keyedValues.addValue("Item 3", 3.125);

        double[] expected = {(double) 1.5/(1.5+2.75+3.125), (double) (1.5+2.75)/(1.5+2.75+3.125), 1.0};

        // exercise
        KeyedValues resultKeyedValues = DataUtilities.getCumulativePercentages(keyedValues);
        double[] result = new double[resultKeyedValues.getItemCount()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultKeyedValues.getValue(i).doubleValue();
        }

        // verify
        assertArrayEquals(expected, result, .000000001d);

        // tear-down: NONE in this test method
    }

    // Test for handling zero values
    @Test
    public void testGetCumulativePercentagesWithZeroValues() {
        // setup
        DefaultKeyedValues keyedValues = new DefaultKeyedValues();
        keyedValues.addValue("Item 1", 0.0);
        keyedValues.addValue("Item 2", 0.0);
        keyedValues.addValue("Item 3", 0.0);

        double[] expected = {0.0/0.0, 0.0/0.0, 0.0/0.0};

        // exercise
        KeyedValues resultKeyedValues = DataUtilities.getCumulativePercentages(keyedValues);
        double[] result = new double[resultKeyedValues.getItemCount()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultKeyedValues.getValue(i).doubleValue();
        }

        // verify
        assertArrayEquals(expected, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
    @Test // this test was added during assignment 3
    public void testGetCumulativePercentagesWithNulls() {
    	
    	DefaultKeyedValues values = new DefaultKeyedValues();
    	values.addValue("Item 1", 1);
    	values.addValue("Item 2", 5);
    	values.addValue("item 3", null);
    	
    	double [] expected = {
    							1.0/6.0, 
    							6.0/6.0, 
    							1.0
    						 };
    	
        KeyedValues resultKeyedValues = DataUtilities.getCumulativePercentages(values);
        double[] result = new double[resultKeyedValues.getItemCount()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultKeyedValues.getValue(i).doubleValue();
        }
        
        assertArrayEquals(expected, result, .000000001d);
    
    }
    
    // The following tests were added during Assignment 4 in order to increase mutation coverage
    // -----------------------------------------------------------------------------------------
    
    // One of the mutations that initially survived for the calculateColumnTotal method 
    // was removing the call to "nullNotPermitted" when checking if the Values2D object passed in is null. 
    // Here, we pass in a null value 
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateColumnTotalWithNullData() {
    	double result = DataUtilities.calculateColumnTotal(null, 0);
    }
    
    // Similar to the above test case, except we use the 
    // other signature of the calculateColumnTotal method.
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateColumnTotalWithNullData2() {
    	int [] values = {0, 1, 2};
    	double result = DataUtilities.calculateColumnTotal(null, 0, values);
    }
    
    // Similarly, the following 2 test cases kill a similar mutant that removes the call to "nullNotPermitted"
    // for the calculateRowTotal methods
    @Test(expected = IllegalArgumentException.class)
    public void testCalculcateRowTotalWithNullData() {
    	double result = DataUtilities.calculateRowTotal(null, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculcateRowTotalWithNullData2() {
    	int [] values = {0, 1, 2};
    	double result = DataUtilities.calculateRowTotal(null, 0, values);
    }
    
    // The following test case kills a similar mutant that removes the "nullNotPermitted" call 
    // for getCumulativePercentages
    @Test(expected = IllegalArgumentException.class)
    public void testNullGetCumulativePercentages() {
    	KeyedValues result = DataUtilities.getCumulativePercentages(null);
    }
    
    
    // This test kills the "Negated integer local variable number 1" mutant
    @Test
    public void calculateColumnTotalForTwoValuesColumn1() {
        // setup

    	DefaultKeyedValues2D values = new DefaultKeyedValues2D(true);
    	values.addValue(1.5, 0, 0);
    	values.addValue(1.5, 1, 0);
    	values.addValue(7.5, 0, 1);
    	values.addValue(2.5, 1, 1);
    	
    	double result = DataUtilities.calculateColumnTotal(values, 1);

        assertEquals(10.0, result, .000000001d);
        
        // test the other signature of the method
        int [] rows = {0, 1};
        double resultAgain = DataUtilities.calculateColumnTotal(values, 1, rows);
        
        assertEquals(10.0, resultAgain, .000000001d);
    }
}

