package com.foo.compass.problem3;

/**
 * Created by user on 24-Mar-16.
 */
public class ReverseWords3b {
    public static void main(String[] args) throws Exception {

        ReverseWords3b rw = new ReverseWords3b();

        char[] input = {'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 't', 'e', 's', 't'};

        rw.reverseWords(input);

        System.out.println(new String(input));

    }

    //Your code here
    public char[] reverseWords(char[] input) {
        char[] reversedCharArray = {};
        if (input == null) {
            return reversedCharArray;
        }

        reversedCharArray = reverse(0, input.length - 1, input);
        // reverse the words (delimeted by space) back to normal
        int i = 0, j = 0;
        while (j < reversedCharArray.length) {

            if (reversedCharArray[j] == ' ' || j == reversedCharArray.length - 1) {

                int m = i;
                int n;

                //dont include space in the swap.
                //(special case is end of line)
                if (j == reversedCharArray.length - 1)
                    n = j;
                else
                    n = j - 1;


                //reuse reverse
                reversedCharArray = reverse(m, n, reversedCharArray);

                i = j + 1;

            }
            j++;
        }
        return reversedCharArray;
    }

    private char[] reverse(int i, int j, char[] str) {

        while (i < j) {

            char temp;
            temp = str[i];
            str[i] = str[j];
            str[j] = temp;

            i++;
            j--;
        }
        return str;
    }

}
