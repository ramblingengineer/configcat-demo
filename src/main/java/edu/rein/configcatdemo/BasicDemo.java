package edu.rein.configcatdemo;

import edu.rein.configcatdemo.restaurant.Restaurant;
import edu.rein.configcatdemo.restaurant.RestaurantRepo;
import edu.rein.configcatdemo.tax.TaxServiceFactory;

public class BasicDemo {
    public static void main(String[] args) {
        for (int i = 1; i <= RestaurantRepo.restaurants.size(); i++) {
            Restaurant r = RestaurantRepo.restaurants.get(i);
            System.out.println("Restaurant di " + r.city() + " " + r.province()
                    + " pajaknya adalah: " + TaxServiceFactory.taxService.getTax(i)
            );
        }

        System.exit(0);
    }
}