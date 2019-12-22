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
			Double salesTax = 0.0;
			Double total = 0.0;

			String st;
			while ((st = br.readLine()) != null) {
				System.out.println(st);

				Map<String, Double> map = generateItemlist(st);

				for (String key : map.keySet()) {

					Double value = map.get(key);

					Boolean exceptionStatus = checkExempted(key);
					Boolean importApplicableStatus = checkImported(key);

					Map<Double, Double> salesTaxValueMap  = getFinalPrice(value, exceptionStatus, importApplicableStatus);
					
					for (Double itemSalesTax : salesTaxValueMap.keySet()) {
						
						salesTax = salesTax + itemSalesTax;
						value = salesTaxValueMap.get(itemSalesTax);
						total = total + salesTaxValueMap.get(itemSalesTax);
					}
					
					itemListMap.put(key, value);
					itemListMap.put("Sales Taxes: ", salesTax);
					itemListMap.put("Total: ", total);
					

				}

			}
			
			
			for(String key : itemListMap.keySet()) {
				System.out.println(key + itemListMap.get(key).toString());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Map<Double, Double> getFinalPrice(Double value, Boolean exceptionStatus, Boolean importApplicableStatus) {

		final int SALES_TAX_RATE = 10;
		final int IMPORT_DUTY = 5;

		Double salesTax = 0.0;
		Double importTax = 0.0;

		Map<Double, Double> salesTaxValueMap = new HashMap<>();

		if (exceptionStatus == false && importApplicableStatus == false) {
			salesTax = (value * SALES_TAX_RATE) / 100;

			salesTaxValueMap.put(salesTax, value + salesTax);
		}

		else if (exceptionStatus == false && importApplicableStatus == true) {
			salesTax = (value * SALES_TAX_RATE) / 100;
			importTax = (value * IMPORT_DUTY) / 100;
			salesTaxValueMap.put(salesTax, value + salesTax + importTax);
		}

		else {
			salesTaxValueMap.put(salesTax, value);
		}

		return salesTaxValueMap;

	}

	private static Boolean checkImported(String key) {
		Boolean importApplicableStatus = false;

		if (key.contains("imported"))
			importApplicableStatus = true;

		return importApplicableStatus;
	}

	private static Boolean checkExempted(String key) {

		Boolean exceptionStatus = false;

		if (key.contains("book") || key.contains("chocolate") || key.contains("pills"))
			exceptionStatus = true;
		return exceptionStatus;
	}

	private static Map<String, Double> generateItemlist(String st) {

		Map<String, Double> map = new HashMap<>();

		String inputString = st.replace(" at ", " : ");

		String[] arr = inputString.split(" ");
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < arr.length - 1; i++) {
			builder = builder.append(arr[i]).append(" ");
		}

		map.put(builder.toString(), Double.valueOf(arr[arr.length - 1]));
		return map;

	}

}
