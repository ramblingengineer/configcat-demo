package edu.rein.configcatdemo.tax;

import com.configcat.ConfigCatClient;
import com.configcat.User;
import edu.rein.configcatdemo.ConfigcatDemoConfig;
import edu.rein.configcatdemo.restaurant.Restaurant;
import edu.rein.configcatdemo.restaurant.RestaurantRepo;

import java.util.Map;

public class TaxDelegateService implements TaxService {

    private ConfigCatClient client = ConfigcatDemoConfig.client;

    @Override
    public String getTax(Integer restaurantId) {
        Restaurant restaurant = RestaurantRepo.restaurants.get(restaurantId);
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant id not found");
        }

        User user = User.newBuilder().custom(
                Map.of("province", restaurant.province(),
                        "city", restaurant.city()
                )).build(String.valueOf(restaurantId));

        if (client.getValue(Boolean.class, "pajak_2024", user, false)) {
            return TaxServiceFactory.tax2024Service.getTax(restaurantId);
        } else {
            return TaxServiceFactory.taxPre2024Service.getTax(restaurantId);
        }
    }
}

