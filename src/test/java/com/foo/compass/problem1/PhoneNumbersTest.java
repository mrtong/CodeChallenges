package com.foo.compass.problem1;

import com.foo.compass.problem1.exceptions.EmptyInputPhoneNumberException;
import com.foo.compass.problem1.exceptions.InvalidInputPhoneNumberException;
import com.foo.compass.problem1.exceptions.InvalidLengthOfInputPhoneNumberException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhoneNumbersTest {
    private final PhoneNumbers pn = new PhoneNumbers();
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldReturnAListWhenInputNumberIsValid() throws Exception {
        final int expectedGeneratedWordListArrayLength = 259539;
        final int actualWordListArrayLength = pn.generateWordListByPhoneNumber("1234567890").toArray().length;
        assertEquals(expectedGeneratedWordListArrayLength, actualWordListArrayLength);

    }

    @Test(expected = InvalidLengthOfInputPhoneNumberException.class)
    public void shouldRaiseExceptionWhenInputNumberIsLessThanTEN() throws Exception {
        final String inputNumber = "67890";
        final int actualWordListArrayLength = pn.generateWordListByPhoneNumber(inputNumber).toArray().length;

    }

    @Test(expected = EmptyInputPhoneNumberException.class)
    public void shouldRaiseExceptionWhenInputNumberIsEmpty() throws Exception {
        final String inputNumber = "";
        final int actualWordListArrayLength = pn.generateWordListByPhoneNumber(inputNumber).toArray().length;

    }

    @Test(expected = InvalidInputPhoneNumberException.class)
    public void shouldRaiseExceptionWhenInputStringContainsInvalidNumber() throws Exception {
        final String inputNumber = "67890!@#$%";
        final int actualWordListArrayLength = pn.generateWordListByPhoneNumber(inputNumber).toArray().length;

    }

}