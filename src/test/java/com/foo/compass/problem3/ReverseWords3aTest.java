package com.foo.compass.problem3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseWords3aTest {
    private ReverseWords3a reverseWords3a;

    @Before
    public void setUp() throws Exception {

        reverseWords3a = new ReverseWords3a();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldReturnEmptyWhenInputwordsIsEmpty() {
        final String inputString = "";
        final String expectedReturnedWords = "";
        final String actualReturnedWords = reverseWords3a.reverseWords(inputString);
        assertEquals(expectedReturnedWords, actualReturnedWords);

    }

    @Test
    public void shouldReturnEmptyWhenInputwordsIsNull() {

        final String inputString = null;
        final String expectedReturnedWords = "";
        final String actualReturnedWords = reverseWords3a.reverseWords(inputString);
        assertEquals(expectedReturnedWords, actualReturnedWords);

    }

    @Test
    public void shouldReturnReversedWordsWhenInputwordsIsValid() {

        final String inputString = "This is a    test";
        final String expectedReturnedWords = "test    a is This";
        final String actualReturnedWords = reverseWords3a.reverseWords(inputString);
        assertEquals(expectedReturnedWords, actualReturnedWords);

    }

}