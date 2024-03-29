package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {
    private Range exampleRange;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        exampleRange = new Range(-1, 1);
    }

    //--------------------------------- Example
    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
                0, exampleRange.getCentralValue(), .000000001d);
    }

    
    //--------------------------------- Tests for combine()
    
    // Test case for combine method when both ranges are not null
    @Test
    public void combineWithBothRangesNotNull() {
        Range range1 = new Range(1, 3);
        Range range2 = new Range(2, 4);
        // combine throws IllegalArgumentException because it wrongly calculates the lower and upper bounds
        // and attempts to create a new range where the lower bound is greater than the upper bound

        Range result = Range.combine(range1, range2);
        assertEquals("Combining range1 with range2 should return range with min = 1 and max = 4", new Range(1, 4), result);
    }

    // Test case for combine method when first range is null
    @Test
    public void combineWithFirstRangeNull2() {
        Range range1 = null;
        Range range2 = new Range(1, 3);
        Range result = Range.combine(range1, range2);
        assertEquals("Combining null range1 with range2 should return range2", range2, result);
    }

    // Test case for combine method when second range is null
    @Test
    public void combineWithSecondRangeNull() {
        Range range1 = new Range(1, 3);
        Range range2 = null;
        Range result = Range.combine(range1, range2);
        assertEquals("Combining range1 with null range2 should return range1", range1, result);
    }

    // Test case for combine method when both ranges are null
    @Test
    public void combineWithBothRangesNull() {
        Range range1 = null;
        Range range2 = null;
        Range result = Range.combine(range1, range2);
        assertNull("Combining null range1 with null range2 should return null", result);
    }

    
    //--------------------------------- Tests for constrain()
    
    @Test
    // Test with a value within the range
    public void constrainValueWithinRange() {
    	double value = 5.0;
    	double lowerBound = 1.0;
    	double upperBound = 10.0;
    	Range range = new Range(lowerBound, upperBound);
    	double result = range.constrain(value);
        assertEquals("Value within the range should be returned as is", value, result, 0.0000001d);
    }

    @Test
    // Test with a value below the range
    public void constrainValueBelowRange() {
    	double value = 0.5;
    	double lowerBound = 1.0;
    	double upperBound = 10.0;
    	Range range = new Range(lowerBound, upperBound);
    	double result = range.constrain(value);
        assertEquals("Value below the range should return the lower bound", lowerBound, result, 0.0000001d);
    }

    @Test
    // Test with a value above the range
    public void constrainValueAboveRange() {
    	double value = 11.0;
    	double lowerBound = 1.0;
    	double upperBound = 10.0;
    	Range range = new Range(lowerBound, upperBound);
    	double result = range.constrain(value);
        assertEquals("Value above the range should return the upper bound", upperBound, result, 0.0000001d);
    }

    @Test
    // Test with a value exactly at the lower bound
    public void constrainLowerBoundValue() {
    	double value = 1.0;
    	double lowerBound = 1.0;
    	double upperBound = 10.0;
    	Range range = new Range(lowerBound, upperBound);
    	double result = range.constrain(value);
        assertEquals("Value at the lower bound should be returned as is", value, result, 0.0000001d);
    }

    @Test
    // Test with a value exactly at the upper bound
    public void constrainUpperBoundValue() {
    	double value = 10.0;
    	double lowerBound = 1.0;
    	double upperBound = 10.0;
    	Range range = new Range(lowerBound, upperBound);
    	double result = range.constrain(value);
        assertEquals("Value at the upper bound should be returned as is", value, result, 0.0000001d);
    }
    
    
    //--------------------------------- Tests for contains()
    
    @Test
    // Test with a value within the range
    public void containsValueWithinRange() {
    	double value = 5.0;
    	Range range = new Range(1.0, 10.0);
    	boolean result = range.contains(value);
        assertTrue("The function with range containing the value should return true", result);
    }
    
    @Test
    // Test with a value within the range
    public void containsValueOutsideRange() {
    	double value = 11.0;
    	Range range = new Range(1.0, 10.0);
    	boolean result = range.contains(value);
        assertFalse("The function with range not containing the value should return false", result);
    }
    
    @Test
    // Test with a value within the range
    public void containsValueAtUpperBound() {
    	double value = 10.0;
    	Range range = new Range(1.0, 10.0);
    	boolean result = range.contains(value);
        assertTrue("The value at the upper bound of the containing range should return true", result);
    }
    
    @Test
    // Test with a value within the range
    public void containsValueAtLowerBound() {
    	double value = 1.0;
    	Range range = new Range(1.0, 10.0);
    	boolean result = range.contains(value);
        assertTrue("The value at the lower bound of the containing range should return true", result);
    }
    
    //--------------------------------- Tests for intersects()
    
    // Test case for when input range is on right side of range1 and partially overlapping
    @Test
    public void testIntersectsWithRightInputPartialOverlap() {
    	Range range1 = new Range(-5, 1);
    	boolean result = range1.intersects(-1, 5);
    	assertTrue("The partially overlapping ranges should return true", result);
    }
    
    // Test case for when input range is on left side of range1 and partially overlapping
    @Test
    public void testIntersectsWithLeftInputPartialOverlap() {
    	Range range1 = new Range(-1, 5);
    	boolean result = range1.intersects(-5, 1);
    	assertTrue("The partially overlapping ranges should return true", result);
    }
    	
    // Test case for when input range is on right side of range1 and non-overlapping
    @Test
    public void testIntersectsWithRightInputNonOverlapping() {
    	Range range1 = new Range(-5, -1);
    	boolean result = range1.intersects(1, 5);
    	assertFalse("The non-overlapping ranges should return false", result);
    }
    
    // Test case for when input range is on left side of range1 and non-overlapping
    @Test
    public void testIntersectsWithLeftInputNonOverlapping() {
    	Range range1 = new Range(1, 5);
    	boolean result = range1.intersects(-5, -1);
    	assertFalse("The non-overlapping ranges should return false", result);
    }
    
    // Test with identical ranges
    @Test
    public void testIntersectsWithIdenticalRanges() {
    	Range range1 = new Range(-2, 2);
    	boolean result = range1.intersects(-2, 2);
    	assertTrue("The identical ranges should return true", result);
    }
    
    // Test case with input range on right side of range1 and adjacent
    @Test
    public void testIntersectsWithRightAdjacentInputRange() {
    	Range range1 = new Range(-5, 0);
    	boolean result = range1.intersects(0, 5);
    	assertFalse("The adjacent ranges should return false", result);
    }
    
    // Test case with input range on left side of range1 and adjacent
    @Test
    public void testIntersectsWithLeftAdjacentInputRange() {
    	Range range1 = new Range(0, 5);
    	boolean result = range1.intersects(-5, 0);
    	assertFalse("The adjacent ranges should return false", result);
    }
    
    // Test case with input range containing range1
    @Test
    public void testIntersectsWithInputContainingRange() {
    	Range range1 = new Range(-2, 2);
    	boolean result = range1.intersects(-5, 5);
    	assertTrue("Range containing range1 should return true", result);
    }
    
    // Test case with range1 containing input range
    @Test
    public void testIntersectsWithRangeContainingInput() {
    	Range range1 = new Range(-5, 5);
    	boolean result = range1.intersects(-2, 2);
    	assertTrue("Range containing input range should return true", result);
    }
    
    //--------------------------------- Tests for equals()
    
    // Test with equivalent ranges
    @Test
    public void testEqualsWithSameRanges() {
    	Range range1 = new Range(-2, 2);
    	Range range2 = new Range(-2, 2);
    	boolean result = range1.equals(range2);
    	assertTrue("Comparing equivalent ranges should return true", result);
    }
    
    // Test with non-equivalent ranges
    @Test
    public void testEqualsWithDifferentRanges() {
    	Range range1 = new Range(-1, 7);
    	Range range2 = new Range(0, 10);
    	boolean result = range1.equals(range2);
    	assertFalse("Comparing non-equivalent ranges should return false", result);
    }
    
    // Test case for range comparing to itself
    @Test
    public void testEqualsItself() {
    	Range range1 = new Range(-2, 2);
    	boolean result = range1.equals(range1);
    	assertTrue("Comparing a range with itself should return true", result);
    }
    
    // Test case for when one range is null
    @Test
    public void testEqualsWithNullRange() {
    	Range range1 = new Range(-2, 2);
    	Range range2 = null;
    	boolean result = range1.equals(range2);
    	assertFalse("Comparing range with null should return false", result);
    }
    
    @Test // this test was added during assigment 3
    public void testEqualsWithSameLowerBound() {
    	// we will test the method with 2 ranges where
    	// only the lower bound matches
    	
    	Range range1 = new Range(1, 10);
    	Range range2 = new Range(1, 5);
    	
    	boolean result = range1.equals(range2);
    	
    	assertFalse("Comparing ranges with only the same lower bound should resturn false", result);
    }
    
    
    //--------------------------------- Tests for shift()
    
    @Test
    public void shiftNoShift() {
        Range base = new Range(-5.0, 5.0);
        Range result = Range.shift(base, 0, true);
        assertEquals("No shift should result in the same range", base, result);
    }
    
    @Test
    public void shiftRightZeroWithinRangeNoZeroCrossing() {
        Range base = new Range(-5.0, 5.0);
        Range result = Range.shift(base, 10.0, false);
        Range expected = new Range(0.0, 15.0);
        assertEquals("Shift right on range containing zero, zero crossing disallowed, should adjust lower bound to zero", expected, result);
    }

    @Test
    public void shiftRightZeroWithinRangeWithZeroCrossing() {
        Range base = new Range(-5.0, 5.0);
        Range result = Range.shift(base, 10.0, true);
        Range expected = new Range(5.0, 15.0);
        assertEquals("Shift right on range containing zero, with zero crossing allowed, should move range as is", expected, result);
    }

    @Test
    public void shiftLeftZeroWithinRangeNoZeroCrossing() {
        Range base = new Range(-5.0, 5.0);
        Range result = Range.shift(base, -10.0, false);
        Range expected = new Range(-15.0, 0.0);
        assertEquals("Shift left on range containing zero, with zero crossing disallowed should adjust upper bound to zero", expected, result);
    }

    @Test
    public void shiftLeftZeroWithinRangeWithZeroCrossing() {
        Range base = new Range(-5.0, 5.0);
        Range result = Range.shift(base, -10.0, true);
        Range expected = new Range(-15.0, -5.0);
        assertEquals("Shift left on range containing zero, with zero crossing allowed, should move range as is", expected, result);
    }

    @Test
    public void shiftRightNoZeroInRangeNoZeroCrossing() {
        Range base = new Range(-10.0, -5.0);
        Range result = Range.shift(base, 10.0, false);
        Range expected = new Range(0.0, 0.0);
        assertEquals("Shift non-zero range right, with zero crossing disallowed, should adjust bounds to zero", expected, result);
    }

    @Test
    public void shiftRightNoZeroInRangeWithZeroCrossing() {
        Range base = new Range(-10.0, -5.0);
        Range result = Range.shift(base, 10.0, true);
        Range expected = new Range(0.0, 5.0);
        assertEquals("Shift non-zero range right, with with zero crossing allowed, should move range as is", expected, result);
    }

    @Test
    public void shiftLeftNoZeroInRangeNoZeroCrossing() {
        Range base = new Range(5.0, 10.0);
        Range result = Range.shift(base, -10.0, false);
        Range expected = new Range(0.0, 0.0);
        assertEquals("Shift non-zero range left, with zero crossing disallowed, should adjust bounds to zero", expected, result);
    }

    @Test
    public void shiftLeftNoZeroInRangeWithZeroCrossing() {
        Range base = new Range(5.0, 10.0);
        Range result = Range.shift(base, -10.0, true);
        Range expected = new Range(-5.0, 0.0);
        assertEquals("Shift non-zero range left, with zero crossing allowed should move range as is", expected, result);
    }
    
    // The following tests were added during assignment 4
    // --------------------------------------------------
    
    // Testing the creation of a range object where lower > upper,
    // triggering the if statement
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalConstructorArgs() {
    	Range range = new Range(10, 2);
    }
    
    // For the equals method, this kills the mutant that replaces "==" with "<="
    @Test
    public void testEqualsWithSameLowerBounds() {
    	Range range1 = new Range(1, 5);
    	Range range2 = new Range(1, 7);
    	boolean result = range1.equals(range2);
    	assertFalse("Comparing range with null should return false", result);
    }
    
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}

