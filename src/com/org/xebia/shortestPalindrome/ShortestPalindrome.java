package com.org.xebia.shortestPalindrome;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class ShortestPalindrome {

	public static void main(String[] args) {
		try {

			URL path = ShortestPalindrome.class.getResource("input.txt");
			File f = new File(path.getFile());

			BufferedReader br = new BufferedReader(new FileReader(f));

			String st;
			while ((st = br.readLine()) != null) {
				
				System.out.println(getPalindromeString(st));
			}

		} catch (IOException e) {
			System.out.println("Exception Occured : " + e.getMessage());
		}
	}

	private static String getPalindromeString(String str) {
		Boolean isPlaindrome = isPalindrome(str);
		if (isPlaindrome == true)
			return str;
		else {
			String res = findShortestPalindrome(str);
			return res;
		}

	}

	/**
	 * This method checks if a string is in palindrome or not
	 * 
	 * @param String input
	 * @return
	 */
	public static String findShortestPalindrome(String str) {

		int index = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			if (str.charAt(i) == str.charAt(index))
				index = index + 1;

		}
		if (index == str.length())
			return str;

		String suffix = str.substring(index);
		String palindrome = new StringBuilder(suffix).reverse().toString()
				+ findShortestPalindrome(str.substring(0, index)) + suffix;

		return palindrome;
	}

	public static boolean isPalindrome(String str) {
		Boolean res = false;
		res = str.equals(new StringBuilder(str).reverse().toString());

		return res;
	}
}
