package be.intecbrussel.foodshop.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Order {
    private Map<Food, Integer> foodItems;
    private double discount;

    public Order() {
        this.foodItems = new HashMap<>();
    }

    public Map<Food, Integer> getFoodItems() {
        return foodItems;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setFoodItems(Map<Food, Integer> foodItems) { this.foodItems = foodItems; }

    public void applyDiscount(double percentDiscount) {
        if (percentDiscount > 0 && percentDiscount <= 100) {
            discount = percentDiscount;
        } else {
            // TODO -> handle incorrect percentage exception.
        }
    }

    public double getTotalPrice() {
        // obtain a set of key-value pairs:
        Set<Map.Entry<Food, Integer>> foodSet = foodItems.entrySet();

        double price = foodSet.stream()
                .map(food -> food.getKey().getPrice() * food.getValue())
                .reduce(0.0, (acc, el) -> acc + el);

        return price - (price * (discount/100));

    }

    public void addFoodToOrder(Food food, Integer amount) {
        Integer amountAlreadyInOrder = foodItems.putIfAbsent(food, amount);

        if (amountAlreadyInOrder == null) {
            return; }

        foodItems.replace(food, amountAlreadyInOrder + amount);
    }
}
