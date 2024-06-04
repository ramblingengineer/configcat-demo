package edu.rein.configcatdemo.tax;

import edu.rein.configcatdemo.restaurant.RestaurantRepo;

public class Tax2024Service implements TaxService {
    @Override
    public String getTax(Integer restaurantId) {
        return RestaurantRepo.restaurants.get(restaurantId).tax() + " + 2%";
    }
}
