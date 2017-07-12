package com.ragas.java.sample;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PalindromesTest {

	@Test
	public void testIsPalindromeForNull(){
		assertFalse(Palindromes.isPalindrome(null));
	}
	
	@Test
	public void testIsPalindromeForEmptyString(){
		assertFalse(Palindromes.isPalindrome(""));
	}
	
	@Test
	public void testIsPalindromeForCharacter(){
		assertFalse(Palindromes.isPalindrome("q"));
	}
	
	@Test
	public void testIsPalindromeForCharacters(){
		assertFalse(Palindromes.isPalindrome("qw"));
	}
	
	@Test
	public void testIsPalindromeForCharacters1(){
		assertTrue(Palindromes.isPalindrome("dD"));
	}
	
	@Test
	public void testIsPalindromeForNumber(){
		assertTrue(Palindromes.isPalindrome("121121121"));
	}
	
	@Test
	public void testIsPalindromeForNumbers(){
		assertFalse(Palindromes.isPalindrome("1121121"));
	}
	
	@Test
	public void testIsPalindromeCaseSensitivity(){
		assertTrue(Palindromes.isPalindrome("MADam"));
	}
	
	@Test
	public void testIsPalindromeForphrase(){
		assertTrue(Palindromes.isPalindrome("Do Geese See God"));
	}
}