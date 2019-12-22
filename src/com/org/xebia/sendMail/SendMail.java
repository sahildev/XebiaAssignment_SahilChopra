package com.org.xebia.sendMail;

import java.util.HashSet;
import java.util.Set;

public class SendMail {

	public void sendMail(String emailAddress, String message) {

		System.out.println(
				"sendMail triggered with parameters --> EmailAddress :: " + emailAddress + " , Message :: " + message);

	}

	public void sendMail(String emailAddress, String message, String[][] friendsArray) {

		// Created in order to remove duplicate values implicitly
		Set<String> sendEmailSet = new HashSet<>();

		
		String[] mailArr = getFriendMailArray(emailAddress, friendsArray);
		
		// Iterate over the 2D array to find list of friends for a given email Address
		for (String email : mailArr) {
			sendEmailSet.add(email);

			String[] dependentMailArr = getFriendMailArray(email, friendsArray);
			if (dependentMailArr != null) {
				for (String dependentEmail : dependentMailArr) {
					sendEmailSet.add(dependentEmail);

				}
			}

		}

		// user email removed to cater to use case condition that mail should not go to
		// user for which this is triggered
		sendEmailSet.remove(emailAddress);

		for (String email : sendEmailSet) {
			sendMail(email, message);
		}

	}

	/**
	 * Helper method to get mailArray corresponding to a given mail
	 * 
	 * @param emailAddress
	 * @param friendsArray
	 * @return null if not found else String[] of frindsMail
	 */
	private String[] getFriendMailArray(String emailAddress, String[][] friendsArray) {

		String[] mailArr;

		for (int i = 0; i < 3; i++)
			if (friendsArray[i][0].equalsIgnoreCase(emailAddress)) {
				mailArr = friendsArray[i][1].split(",");
				return mailArr;
			}

		return null;
	}

	/**
	 * Assumption : The input will be in a 2D array
	 * 
	 * The Sample DataSet is prepared via the 2D array and the program triggered via
	 * main itself for sample data string 1.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String[][] arr = new String[3][2];
		arr[0][0] = "rahul@test.com";
		arr[0][1] = "rajat@test.com,rashmi@test.com,vinod@test.com";

		arr[1][0] = "vineet@test.com";
		arr[1][1] = "ajay@test.com,rajat@test.com,rahul@test.com";

		arr[2][0] = "Vinod@test.com";
		arr[2][1] = "rahul@test.com,nitin@test.com,vineet@test.com";

		SendMail mailObj = new SendMail();
		mailObj.sendMail("vineet@test.com", "test message for rahul", arr);
	}

}
