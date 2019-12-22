package com.org.xebia.salesTaxCalculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this program is to calculate the sales tax for the provided
 * input.
 * 
 * @author sahchopr
 *
 */
public class SalesTaxCalculator {

	public static void main(String[] args) {
		try {

			URL path = SalesTaxCalculator.class.getResource("input.txt");
			File f = new File(path.getFile());

			BufferedReader br = new BufferedReader(new FileReader(f));

			Map<String, Double> itemListMap = new HashMap<>();
			int salesTax = 0;
			int total = 0;

			String st;
			while ((st = br.readLine()) != null) {
				System.out.println(st);

				Map<String, String> map = generateItemlist(st);

				for (String key : map.keySet()) {

					String value = map.get(key);

					Boolean exceptionStatus = checkExempted(key);
					Boolean importApplicableStatus = checkImported(key);

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Boolean checkImported(String key) {
		Boolean importApplicableStatus = false;

		return importApplicableStatus;
	}

	private static Boolean checkExempted(String key) {

		Boolean exceptionStatus = false;

		if (key.contains("book") || key.contains("chocolate") || key.contains("pills"))
			exceptionStatus = true;
		return exceptionStatus;
	}

	private static Map<String, String> generateItemlist(String st) {

		Map<String, String> map = new HashMap<>();

		String inputString = st.replace(" at ", " : ");

		String[] arr = inputString.split(" ");
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < arr.length - 1; i++) {
			builder = builder.append(arr[i]).append(" ");
		}

		map.put(builder.toString(), arr[arr.length - 1]);
		return map;

	}

}
