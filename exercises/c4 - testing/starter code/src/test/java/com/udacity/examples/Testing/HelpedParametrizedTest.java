package com.udacity.examples.Testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class HelpedParametrizedTest {

    private String input;

    private String output;


    public HelpedParametrizedTest(String input, String output) {
        super();
        this.input = input;
        this.output = output;
    }

    @Parameterized.Parameters
    public static Collection initData() {
        String[][] employeeName = {{"sareeta", "sareeta"}, {"sareeta", "jeff"}};
        return Arrays.asList(employeeName);
    }


    @Test
    public void verifyInputNameIsNotTheSameAsOutputName() {
        assertNotEquals(input, output);
    }
//
//    @RunWith()
//    @SelectClasses({HelpedParametrizedTest.class,})

}
