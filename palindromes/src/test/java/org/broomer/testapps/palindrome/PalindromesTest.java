package org.broomer.testapps.palindrome;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test suite for Palindromes
 */
public class PalindromesTest {
    @Test
    public void testPalindromes() throws Exception {
        Assert.assertTrue("The given string is a palindrome", Palindromes.isPalindrome("aacaa"));
        Assert.assertTrue("The given string is a palindrome", Palindromes.isPalindrome("aaccaa"));
        Assert.assertTrue("The given string is a palindrome", Palindromes.isPalindrome("1"));
        Assert.assertTrue("The given string is a palindrome", Palindromes.isPalindrome(""));
        Assert.assertTrue("The given string is a palindrome", Palindromes.isPalindrome("船上女子叫子女上船"));
    }

    @Test
    public void testNonPalindromes() throws Exception {
        Assert.assertFalse("The given string is not a palindrome", Palindromes.isPalindrome("aacc1aa"));
    }

    @Test(expected=NullPointerException.class)
    public void testForNulls() throws Exception {
        Palindromes.isPalindrome(null);
    }

}