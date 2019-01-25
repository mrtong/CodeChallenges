package com.foo.compass.problem3;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created with IntelliJ IDEA.
 * User: Luke Tong
 * Date: 29/03/16
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReverseWords3a {

    public String reverseWords(String input) {

        if (input == null || input.equals("")) {
            return "";
        }

        List<String> wordsList = asList(input.split("\\s"));
        //another approach is to just use String arrays instead of the Collections method.
        Collections.reverse(wordsList);

        int wordsSize = input.length();
        StringBuilder sb = new StringBuilder(wordsSize);

        for (int i = 0; i < wordsList.size(); i++) {
            sb.append(wordsList.get(i));
            sb.append(' ');
        }

        return sb.toString().trim();

    }

    public static void main(String[] args) {
        String sentence = "This is a    test";
        ReverseWords3a reverseWords3a = new ReverseWords3a();

        String reversed = reverseWords3a.reverseWords(sentence);
        System.out.println(reversed);
    }
}
