package com.example.lic.digui;

public class Solution {

    public static void main(String args[]) {
        char[] str = new char[]{'h','e','l','l','o'};
        new Solution().reverseStringV1(str);
        System.out.println(str);
    }

    public void reverseString(char[] s) {
        if (s.length == 0) {
            return;
        }
        reverse(0, s, s[s.length - 0 - 1]);
    }

    public void reverse(int index, char[] s, char str) {
        if (index == s.length - 1) {
            s[index] = str;
            return;
        }

        reverse(index + 1, s, s[s.length - (index + 1) - 1]);
        s[index] = str;
    }

    public void reverseStringV1(char[] s) {
        int n = s.length;
        for (int i = 0; i < n / 2; ++i) {
            int j = n - 1 - i;
            s[i] ^= s[j];
            s[j] ^= s[i];
            s[i] ^= s[j];
        }
    }
}