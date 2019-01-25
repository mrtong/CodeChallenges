package com.foo.compass.problem3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ReverseWords3bTest {
    private ReverseWords3b reverseWords3b;

    @Before
    public void setUp() throws Exception {

        reverseWords3b = new ReverseWords3b();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldReturnReversedWordsWhenInputValid() {
        final char[] input = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 't', 'e', 's', 't'};
        final char[] expectedReversedWords = {'t', 'e', 's', 't', ' ', 'a', ' ', 'i', 's', ' ', 'T', 'h', 'i', 's'};
        final char[] actualReversedWords = reverseWords3b.reverseWords(input);
        assertArrayEquals(expectedReversedWords, actualReversedWords);

    }

    @Test
    public void shouldReturnEmptyWordsWhenInputIsEmpty() {
        final char[] input = {};
        final char[] expectedReversedWords = {};
        final char[] actualReversedWords = reverseWords3b.reverseWords(input);
        assertArrayEquals(expectedReversedWords, actualReversedWords);

    }

    @Test
    public void shouldReturnEmptyWordsWhenInputIsNull() {
        final char[] input = null;
        final char[] expectedReversedWords = {};
        final char[] actualReversedWords = reverseWords3b.reverseWords(input);
        assertArrayEquals(expectedReversedWords, actualReversedWords);

    }

}