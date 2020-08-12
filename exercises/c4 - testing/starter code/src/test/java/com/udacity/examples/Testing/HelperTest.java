package com.udacity.examples.Testing;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class HelperTest extends TestCase {


    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }


    @Test
    public void testGetCount() {
        List<String> empNames = Arrays.asList("sareeta", "udacity");
        assertEquals(2, Helper.getCount(empNames));
    }

    public void testGetStats() {
        List<Integer> yrsOfExperience = Arrays.asList(13, 4, 15, 6, 17, 8, 19, 1, 2, 3);
        List<Integer> expectedList = Arrays.asList(13, 4, 15, 6, 17, 8, 19, 1, 2, 3);
        assertEquals(Helper.getStats(yrsOfExperience).getMax(), 19);
        assertEquals(expectedList, yrsOfExperience);
    }

    public void compare_arrays() {
        int[] yrs = {10, 14, 2};
        int[] expectedYrs = {10, 14, 2};

        assertArrayEquals(expectedYrs, yrs);
    }

    public void testGetStringsOfLength3() {
    }

    public void testGetSquareList() {
        List<Integer> yrsOfExperience = Arrays.asList(13, 4, 15, 4);
        List<Integer> expected = Arrays.asList(13 * 13, 4 * 4, 15 * 15);
        assertEquals(expected, Helper.getSquareList(yrsOfExperience));
    }

    public void testGetMergedList() {
        List<String> empNames = Arrays.asList("sareeta", "", "john", "");
        List<String> expected = Arrays.asList("sareeta", "", "john", "");
        String expectedStr = "sareeta, john";

        assertEquals(expectedStr, Helper.getMergedList(empNames));


    }

    public void testGetFilteredList() {
    }
}