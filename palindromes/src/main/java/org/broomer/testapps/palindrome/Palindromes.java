package org.broomer.testapps.palindrome;

/**
 */
public class Palindromes {


    /**
     * The O(n) version of checking if the string is a palindrome.
     *
     * @param s - the string under examination
     * @return - true, if given string complies to the definition of the palindrome.
     *
     * Empty and one-character strings are always palindromes.
     * If the string is null, it is always not a palindrome.
     *
     * @throws NullPointerException - if the given string is null
     */
    public static boolean isPalindrome(String s) {
        if (s == null) {
            throw new NullPointerException();
        }

        if (s.isEmpty() || s.length() == 1) {
            return true;
        }

        int charNumbers = s.length();
        for (int i = 0; i < (charNumbers / 2); ++i) {
            if (s.charAt(i) != s.charAt(charNumbers - i - 1)) {
                return false;
            }
        }

        return true;
    }

}
