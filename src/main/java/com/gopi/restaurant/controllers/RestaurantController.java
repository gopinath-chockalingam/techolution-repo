package com.gopi.restaurant.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gopi.restaurant.domain.Customer;
import com.gopi.restaurant.domain.Menu;
import com.gopi.restaurant.services.RestaurantService;

/**
 * @author gopic
 *
 */
@Controller
@RequestMapping(value = "/v1/restaurant")
public class RestaurantController {

    private Logger logger = Logger.getLogger(RestaurantController.class);
    
    private static final String INPUT_FILE_PATH="src/main/resources/data.txt";
	
    private static Menu menu;
	
	private static Customer customer;
	
	BufferedReader bufferedReader = null;
	
   	FileReader fileReader = null;
	
    private RestaurantService restaurantService;
    
    @Autowired
	public void setRestaurantService(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}


	private int totalTimeGiven = 0;
    
	private int totalItems=0;
    
   

    /**
     * calculate and get maximum satisfactory level for the given items 
     * against the time taken for each dish/item
     * 
     * @return Long
     */
    @RequestMapping(value = "/maxlevel", method = RequestMethod.GET)
    public ResponseEntity<String> getMaxSatisfactoryLevel(){
    	logger.info("Executing getMaxSatisfactoryLevel()");
    	 String finalResponse="";
    	try{
	    	readFileAndSetTotalItemsAndTime(INPUT_FILE_PATH);
	        //Prepare Menu with number of Items given
	    	if(this.totalItems >0 && this.totalTimeGiven>0){
				menu = new Menu(this.totalItems);
				customer = new Customer("Gordon Ramsey", this.totalTimeGiven, menu);
				finalResponse= restaurantService.getMaximumSatisfactoryLevel(customer);
	    	}else{
	    		finalResponse="Please enter valid time and totalNoOfItems and it should be > 0";
	    	}
		       if(StringUtils.isNotEmpty(finalResponse)){
		    	   return new ResponseEntity<String>(finalResponse+" ",HttpStatus.OK);
		       }
		} catch (NumberFormatException nfe) {
			logger.error("Invalid input passed. please provide valid time and number of items as numbers.." + nfe.getMessage());
			finalResponse="Invalid data. The data file should have only Numbers. No alphanumeric Or special Characters allowed";
		}catch(ArrayIndexOutOfBoundsException ex){
			logger.error("Invalid index position :" + ex.getMessage());
			finalResponse="Error while getting the result";
		} catch (FileNotFoundException ex) {
			logger.error(" Input file not found in the path "+INPUT_FILE_PATH + "--"+ex.getMessage());
			finalResponse="Input file not found in the file sytem to process";
		}catch (IOException e) {
			logger.error("Exception occured while reading a file"+e.getMessage());
			finalResponse="Error occured while reading and parsing the given input data";
		}try {
			if (bufferedReader != null)
				bufferedReader.close();
			if (fileReader != null)
				fileReader.close();
		} catch (IOException ex) {
			System.err.println("IOException while shuting down resources.. " + ex.getStackTrace());
		}
    	


       return new ResponseEntity<String>(finalResponse+" ",HttpStatus.OK);
    }

    private BufferedReader getBufferedReader(String filePath) throws FileNotFoundException{
    				fileReader = new FileReader(
    						new File(filePath));
    				bufferedReader = new BufferedReader(fileReader);
    				return bufferedReader;
    }
    
    
   private void readFileAndSetTotalItemsAndTime(String filePath) throws IOException{
	   String firstLine=null;
	    bufferedReader =getBufferedReader(filePath);
		  if(bufferedReader!=null){
				firstLine= bufferedReader.readLine();
		  }
			if(firstLine!=null){
				String[] firstLineTokens=firstLine.split(" ");
				//10000 100
				System.out.println("Total time in mins is.. " + firstLineTokens[0]);
				System.out.println("Total no of items .. " + firstLineTokens[1]);
				this.totalTimeGiven = Integer.parseInt(firstLineTokens[0]);
				this.totalItems=Integer.parseInt(firstLineTokens[1]);
			}
			
   }
}
