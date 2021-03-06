package com.foo.nasdaq;

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.calConsecutiveNumbers(5, 2);
        s.calConsecutiveNumbers(100, 10);
        s.calConsecutiveNumbers(1000000, 200);
        s.calConsecutiveNumbers(10000000, 200);
    }


    public String calConsecutiveNumbers(int n, int C) {
        String output = doJob(n, C).toString();
        System.out.printf("for n=%d & C=%d\nThe result is =%s\n\n", n, C,
                output.substring(0, Math.min(10, output.length())));
        return output;
    }

    private BigInteger doJob(int n, int C) {
        BigInteger product = new BigInteger("1");
        BigInteger sum = new BigInteger("0");
        for (int i = 1; i < n; i++) {
            if (i <= C) {
                product = product.multiply(new BigInteger("" + i));
            } else {
                product = product.multiply(new BigInteger("" + i)).divide(new BigInteger("" + (i - C)));
            }
            sum = sum.add(product);
        }
        return sum;
    }
}
