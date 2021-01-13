package be.intecbrussel.foodshop.model;

import be.intecbrussel.foodshop.exception.FoodAlreadyInStockException;
import be.intecbrussel.foodshop.exception.FoodNotInStockException;
import be.intecbrussel.foodshop.exception.NotEnoughFoodInStockException;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private Map<Food, Integer> foodStock;

    {
        foodStock = new HashMap<>();
    }

    public Map<Food, Integer> getFoodStock() {
        return foodStock;
    }

    public void setFoodStock(Map<Food, Integer> foodStock) {
        this.foodStock = foodStock;
    }

    public void addFood(Food food) throws FoodAlreadyInStockException {
        if (food != null) {
            Integer value = foodStock.putIfAbsent(food, 0);

            if (value != null) {
                throw new FoodAlreadyInStockException(food.getName() + " -> already in our stock, amount in stock: " + value);
            }
        }
    }

    public void addToStock(Food food, int amount) throws FoodNotInStockException{
        if (foodStock.containsKey(food)) {
            int newAmount = foodStock.get(food) + amount;
            foodStock.replace(food,newAmount);

        } else {
            throw new FoodNotInStockException(food.getName() + " is not in our stock!");
        }
    }

    public void removeFood(Food food) {
        this.foodStock.remove(food);
    }

    public void removeFromStock(Food food, int amount) throws NotEnoughFoodInStockException, FoodNotInStockException {
        if (!foodStock.containsKey(food)) {
            throw new FoodNotInStockException(food.getName() + " -> not enough of this in stock, trying to sell: " + amount);
        }

        if (foodStock.get(food) < amount) {
            throw new NotEnoughFoodInStockException("There is not enough of this food (" + food.getName() + ") in out stock || Trying to remove " + amount + food.getName());
        } else {
            foodStock.replace(food, foodStock.get(food) - amount);
        }
    }
}
