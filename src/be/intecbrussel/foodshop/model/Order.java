package be.intecbrussel.foodshop.model;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Order {
    private Map<Food, Integer> foodItems;
    private double discount;

    public Map<Food, Integer> getFoodItems() {
        return foodItems;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setFoodItems(Map<Food, Integer> foodItems) {
        this.foodItems = foodItems;
    }

    public double getTotalPrice() {
        // obtain a set of key-value pairs:
        Set<Map.Entry<Food, Integer>> foodSet = foodItems.entrySet();

        double price = foodSet.stream()
                .map(food -> food.getKey().getPrice() * food.getValue())
                .reduce(0.0, (acc, el) -> acc + el);

        return price - (price/100 * discount);

    }
}
