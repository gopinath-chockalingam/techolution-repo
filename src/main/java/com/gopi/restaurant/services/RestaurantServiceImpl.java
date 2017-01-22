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

/**
 * @author gopic
 *
 */
@Service
public class RestaurantServiceImpl implements RestaurantService{

	    // Time in minutes
		private int timeSofarAfterEatingDish = 0;
		
		private int totalMaximumSatisfactoryLevel = 0;

		private Boolean hasTimeRemaining = Boolean.FALSE;
		
	@Override
	public String getMaximumSatisfactoryLevel(Customer customer) {
		this.hasTimeRemaining=Boolean.FALSE;
		this.totalMaximumSatisfactoryLevel=0;
		return calculateNoOfItemsToEat(customer);
	}
	
	
	private String calculateNoOfItemsToEat(Customer customer) {
		   addItemsFromMenu(customer);
		return displayAllSatisfactoryLevel(customer);
	}

	private String displayAllSatisfactoryLevel(Customer customer) {
		Item[] items = customer.getMenu().getItems();

		return eatAndCalculateMaxSatisfactoryLevel(items, customer);

	}

	/**
	 * This method will read the input file and will read the lines and will add/populate the 
	 *  properties of Item class to corresponding data members.
	 *  Also,Sorts the Item array with the help of customized CustomerSatisfactoryLevelComparator class.
	 *  
	 */
	private void addItemsFromMenu(Customer customer) {
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
			//Sort the added items based on satisfactoryLevel in descending order
			Arrays.sort(customer.getMenu().getItems(), new CustomerSatisfactoryLevelComparator());

		} catch (IOException e) {
			System.err.println("IOException while reading input data.. " + e.getStackTrace());
			hasTimeRemaining =Boolean.FALSE;
			timeSofarAfterEatingDish=0;
			totalMaximumSatisfactoryLevel=0;
		} finally {
			hasTimeRemaining =Boolean.FALSE;
			timeSofarAfterEatingDish=0;
			totalMaximumSatisfactoryLevel=0;
			try {
				if (br != null)
					br.close();
				if (reader != null)
					reader.close();
			} catch (IOException ex) {
				System.err.println("IOException while shuting down resources.. " + ex.getStackTrace());
			}
		}
	}

	
	private String eatAndCalculateMaxSatisfactoryLevel(Item[] items,Customer customer){
		//Iterating the Items which has max satisfactory leve in descending order. 
		//So that the customer can pick the maximum satisfactory items .
		for (Item currentDish:items){
			timeSofarAfterEatingDish = timeSofarAfterEatingDish + currentDish.getTimeTaken();
			totalMaximumSatisfactoryLevel =totalMaximumSatisfactoryLevel+ currentDish.getSatisfactoryLevel();
			 // check the total time given is less after eating the current dish
			if (timeSofarAfterEatingDish > customer.getTotalTimeGiven()) {
				hasTimeRemaining = Boolean.TRUE;
				break;
			}
			System.out.println(customer.getCustomerName() + " ate/finished  " + currentDish.getName() + " for about "
					+ currentDish.getTimeTaken() + " mins " + " and got satisfactory level of "
					+ currentDish.getSatisfactoryLevel());
		}
		return "maximum  satisfaction level is : " + totalMaximumSatisfactoryLevel;
	}
	
	
	/**
	 * This will tokenize each line and will set the timetaken per dish
	 * and satisfactorylevel of the corresponding dish in Item object 
	 * 	 * 
	 * @param line
	 * @param itemCount
	 * @param Customer
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
	}

}
