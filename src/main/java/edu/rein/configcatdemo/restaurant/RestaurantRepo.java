package edu.rein.configcatdemo.restaurant;

import java.util.Map;

public class RestaurantRepo {

    public static final Map<Integer, Restaurant> restaurants = Map.of(
            1, new Restaurant(1, "jabar", "bandung", "5%"),
            2, new Restaurant(2, "jatim", "surabaya", "5%"),
            3, new Restaurant(3, "jabar", "tasikmalaya", "5%"),
            4, new Restaurant(4, "jateng", "semarang", "5%"),
            5, new Restaurant(5, "sulut", "manado", "5%"),
            6, new Restaurant(6, "sumut", "medan", "5%")
    );
}
