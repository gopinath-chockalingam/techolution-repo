package com.gopi.restaurant;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TecholutionApplication {
	
	private static Logger logger = Logger.getLogger(TecholutionApplication.class);

	public static void main(String[] args) {
		logger.info("Starting Techolution Restaurant Max Satisfactory Finder for Food Item Application ......");
		SpringApplication.run(TecholutionApplication.class, args);
		
	}
}


