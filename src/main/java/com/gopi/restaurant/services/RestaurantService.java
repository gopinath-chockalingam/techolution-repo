package com.gopi.restaurant.services;

import com.gopi.restaurant.domain.Customer;

/**
 * @author gopic
 *
 */
public interface RestaurantService {
	
    /**
     * This method returns the maximum satisfactoryLevel by calling the implementation
     * @param customer
     * @return String
     */
    String getMaximumSatisfactoryLevel(Customer customer );
}
