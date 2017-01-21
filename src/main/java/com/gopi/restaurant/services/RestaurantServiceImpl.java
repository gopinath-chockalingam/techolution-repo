package com.gopi.restaurant.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

import com.gopi.restaurant.domain.Customer;
import com.gopi.restaurant.domain.Item;
import com.gopi.restaurant.utils.CustomerSatisfactoryLevelComparator;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	    // Time in minutes
		private int timeSofarAfterEatingDish = 0;

		private Boolean hasTimeRemaining = Boolean.FALSE;
		
	@Override
	public String getMaximumSatisfactoryLevel(Customer customer) {

		return calculateNoOfItemsToEat(customer);
	}
	
	
	private String calculateNoOfItemsToEat(Customer customer) {
		       eatItemsFromMenu(customer);
		return displayAllSatisfactoryLevel(customer);
	}

	private String displayAllSatisfactoryLevel(Customer customer) {
		Item[] items = customer.getMenu().getItems();

		return getMaximumSatisfactoryLevel(items);

	}

	/**
	 * Sort the Item array with the help of customized CustomerSatisfactoryLevelComparator class.
	 * 
	 * finds the maximum satisfactoryLevel that consumer has for a 
	 * particular dish/food item.
	 * 
	 * @param itemList
	 */
	private String getMaximumSatisfactoryLevel(Item[] itemList) {
		//This comparator is to sort the Items based on satisfactoryLevel in descending order. 
		//If two items are having equal time to complete the dish, then the item which had max satisfactoryLevel will be return as a response.
		Arrays.sort(itemList, new CustomerSatisfactoryLevelComparator());
		System.out.print("[ ");
		for (int j = 0; j < itemList.length; j++) {
			System.out.print(itemList[j].getSatisfactoryLevel() + " ");
		}
		System.out.print(" ]");
		System.out.println();
		return "maximum  satisfaction level is : " + itemList[0].getSatisfactoryLevel() +"\n"
				+ "item : "+ itemList[0].getName();

	}

	/**
	 * This method will read the input file and will read the lines and will add/populate the 
	 *  properties of Item class to corresponding data members.
	 */
	private void eatItemsFromMenu(Customer customer) {
		BufferedReader br = null;
		FileReader reader = null;
		int itemCount = 0;
		try {
			reader = new FileReader(
					new File("src/main/resources/data.txt"));
			br = new BufferedReader(reader);

			br.readLine(); // this will read the first line
			String line;
			while ((line = br.readLine()) != null) {
				if (!hasTimeRemaining) {
					readAndParseLine(line, itemCount,customer);
					itemCount++;

				} else {
					break;
				}
			}
			// set consumed items
			customer.getMenu().setItems(Arrays.copyOf(customer.getMenu().getItems(), itemCount - 1));

		} catch (IOException e) {
			System.err.println("IOException while reading input data.. " + e.getStackTrace());
			hasTimeRemaining =Boolean.FALSE;
			timeSofarAfterEatingDish=0;
		} finally {
			hasTimeRemaining =Boolean.FALSE;
			timeSofarAfterEatingDish=0;
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				System.err.println("IOException while shuting down resources.. " + ex.getStackTrace());
			}
		}
	}

	/**
	 * This will tokenize each line and will sum the time taken for each dish
	 * and compares with the given input time. It will break the loop 
	 * when the value reaches the given input time.
	 * 
	 * @param line
	 * @param itemCount
	 */
	private void readAndParseLine(String line, int itemCount,Customer customer) {

		itemCount = itemCount + 1;
		int timeTakenPerDish = 0;
		//It will tokenize/create tokens of the given line by having space as separator
		StringTokenizer tokens = new StringTokenizer(line, " ");

		//when we call tokens.nextToken() , it will give the amount of satisfaction from eating dish from data.txt file
		customer.getMenu().getItems()[itemCount].setSatisfactoryLevel(Integer.parseInt(tokens.nextToken()));
		if (tokens.hasMoreElements()) {
			//when we call tokens.nextToken() here , it will give the time taken for dish from data.txt file
			timeTakenPerDish = Integer.parseInt(tokens.nextToken());
			customer.getMenu().getItems()[itemCount].setTimeTaken(timeTakenPerDish);
		}
		timeSofarAfterEatingDish = timeSofarAfterEatingDish + timeTakenPerDish;
		if (timeSofarAfterEatingDish > customer.getTotalTimeGiven()) {
			hasTimeRemaining = Boolean.TRUE;
			return;
		}
		System.out.println(customer.getCustomerName() + " ate/finished  " + customer.getMenu().getItems()[itemCount].getName() + " for about "
				+ customer.getMenu().getItems()[itemCount].getTimeTaken() + " mins " + " and got satisfactory level of "
				+ customer.getMenu().getItems()[itemCount].getSatisfactoryLevel());

	}

}
