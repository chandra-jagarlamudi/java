package com.ragas.java.sample;

import java.util.Scanner;

public class Palindromes {

	/**
	 * Method to verify a given string or phrase is Palindrome. This method
	 * converts a given string to lower case for handling case sensitivity and
	 * replaces all spaces to handle phrases. Further empty and single character
	 * strings are not palindromes
	 * 
	 * @param str
	 * @return boolean
	 * 
	 */
	public static boolean isPalindrome(String str) {
		// Empty string and single character strings are not palindromes
		if (null != str && str.length() > 1) {
			// Converting toLowerCase for handling case sensitivity
			// Replacing empty spaces to accommodate palindrome phrases
			String formattedString = str.toLowerCase();
			int n = formattedString.length();
			for (int i = 0; i < n / 2; ++i) {
				if (formattedString.charAt(i) != formattedString.charAt(n - i - 1))
					return false;
			}
			return true;
		}
		return false;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// Reading console input
		Scanner consoleInput = new Scanner(System.in);
		String stringEntered ;//= consoleInput.nextLine().replaceAll("[^\\dA-Za-z ]", "").replaceAll("\\s+", "");
		while((stringEntered = consoleInput.nextLine().replaceAll("[^\\dA-Za-z ]", "").replaceAll("\\s+", "")).length() > 0 ) {
			if (isPalindrome(stringEntered))
				System.out.println("YES");
			else
				System.out.println("NO");
        }
		
		System.exit(1);
	}
}