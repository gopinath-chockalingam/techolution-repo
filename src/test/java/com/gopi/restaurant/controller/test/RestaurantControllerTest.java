package com.gopi.restaurant.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import com.gopi.restaurant.TecholutionApplication;
import com.gopi.restaurant.controllers.RestaurantController;
import com.gopi.restaurant.domain.Customer;
import com.gopi.restaurant.domain.Menu;
import com.gopi.restaurant.services.RestaurantService;

/**
 * @author gopic
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TecholutionApplication.class})
@SpringBootTest
@WebAppConfiguration
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
	
	@MockBean
    private RestaurantService restaurantService;
	
	@MockBean
    Customer customer;
    @MockBean
	Menu menu;
    
	BufferedReader br = null;
	FileReader reader = null;
	@Autowired 
	private WebApplicationContext ctx;
	 
    private MockMvc mockMvc;
 
    @Before 
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
	
	    
    @Autowired
	public void setRestaurantService(RestaurantService restaurantService) {
	      this.restaurantService = restaurantService;
	}
	    
	    
	@Before
	public void setup() throws FileNotFoundException{
			reader = new FileReader(
					new File("src/test/resources/data.txt"));
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testWrongInputFile() throws IOException {
		reader = new FileReader(
				new File("src/test/resources/input.txt"));
			Assert.notNull(reader);
	}
	
	@Test
	public void testInputFileContentNotEmpty() throws IOException {
		BufferedReader br = null;
		FileReader reader = null;
			reader = new FileReader(
					new File("src/test/resources/data.txt"));
			Assert.notNull(reader);
			br = new BufferedReader(reader);
			Assert.notNull(br.readLine());
	}

	
	@Test
	public void testInputFileReadAndTokenize() throws IOException {
			Assert.notNull(reader);
			br = new BufferedReader(reader);
			String line=br.readLine();
			Assert.notNull(line);
			Assert.hasText(line);
			StringTokenizer tokens = new StringTokenizer(line, " ");
			Assert.notNull(tokens);
			Assert.isTrue(tokens.hasMoreElements());
	}
	
	@Test
	public void testMaxSatisfactoryLevelStatus() throws Exception{
		this.mockMvc.perform(get("/v1/restaurant/maxlevel"))
        .andExpect(status().isOk());
	}
	
	@Test
	public void testMaxSatisfactoryLevel() throws Exception{
		this.mockMvc.perform(get("/v1/restaurant/maxlevel"))
        .andExpect(content().string("maximum  satisfaction level is : 1899596 "));
	}
	
}

