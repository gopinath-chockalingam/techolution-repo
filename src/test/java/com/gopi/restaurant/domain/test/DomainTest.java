package com.gopi.restaurant.domain.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.gopi.restaurant.TecholutionApplication;
import com.gopi.restaurant.domain.Customer;
import com.gopi.restaurant.domain.Item;
import com.gopi.restaurant.domain.Menu;

/**
 * @author gopic
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TecholutionApplication.class})
public class DomainTest {
	
	private Customer customer;
	 private Menu menu;
	 
    public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Test
    public void testEmptyCustomer(){
    	Assert.isNull(customer);
    }
	
	@Test
    public void testCustomerWithNameOnly(){
    	customer=new Customer("Gopinath", 0, menu);
    	Assert.notNull(customer);
    	Assert.isNull(customer.getMenu());
    	Assert.isTrue(customer.getTotalTimeGiven()==0, "0");
    }
	
	@Test
    public void testMenuWithItem() throws IOException{
    	menu=new Menu();
    	Assert.notNull(menu);
    	Assert.isNull(menu.getItems());
    	BufferedReader br = null;
		FileReader reader = null;
			reader = new FileReader(
					new File("src/test/resources/data_with_oneline.txt"));
			Assert.notNull(reader);
			br = new BufferedReader(reader);
			String firstLine = null;
			if(br!=null){
				firstLine= br.readLine();
		  }
			if(firstLine!=null){
				String[] firstLineTokens=firstLine.split(" ");
				//10000 100
				System.out.println("Total time in mins is.. " + firstLineTokens[0]);
				System.out.println("Total no of items .. " + firstLineTokens[1]);
				Assert.isTrue(Integer.parseInt(firstLineTokens[0])==10000);
				Assert.isTrue(Integer.parseInt(firstLineTokens[1])==100);
				Item[] items=new Item[Integer.parseInt(firstLineTokens[1])];
				Assert.isTrue(items.length==100);
			}
			
    }
}

