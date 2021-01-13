package be.intecbrussel.foodshop.app;

import be.intecbrussel.foodshop.exception.FoodAlreadyInStockException;
import be.intecbrussel.foodshop.exception.FoodNotInStockException;
import be.intecbrussel.foodshop.exception.NotEnoughFoodInStockException;
import be.intecbrussel.foodshop.model.Food;
import be.intecbrussel.foodshop.model.Stock;

import java.util.Map;

public class TestApp {
    public static void main(String[] args) {
        Stock stock = new Stock();

        Food apple = new Food("Apple");
        Food pollo = new Food("Chicken");
        Food patat = new Food("Potato");
        Food pizza = new Food("Pizza");

        // test addMethods
        addFoodToStock(stock, pizza);
        addAmountOfFoodToStock(stock, pizza, 99);

        addFoodToStock(stock, pizza);
        addAmountOfFoodToStock(stock, pizza, 99);

        addFoodToStock(stock, patat);
        addAmountOfFoodToStock(stock, patat, 50);

        print(stock);

        // test removeMethods
        removeAmountOfFoodFromStock(stock, patat, 35);
        print(stock);

        removeAmountOfFoodFromStock(stock, pizza, 69);
        print(stock);

        removeAmountOfFoodFromStock(stock, apple, 77);

        stock.removeFood(patat);
        print(stock);

    }

    // print-method
    private static void print(Stock stock) {
        Map<Food, Integer> foodStock = stock.getFoodStock();

        System.out.println("-----------FOOD STOCK----------");
        foodStock.forEach((food, integer) -> System.out.println(food.getName().toUpperCase() + " -> stock: " + integer));
    }

    // methods to add, add amount of food to stock
    private static void addFoodToStock(Stock stock, Food food) {
        try {
            stock.addFood(food);
        } catch (FoodAlreadyInStockException faise) {
            System.err.println(faise.getMessage());
        }
    }

    private static void addAmountOfFoodToStock(Stock stock, Food food, int amount) {
        try {
            stock.addToStock(food, amount);
        } catch (FoodNotInStockException fnise) {
            System.err.println(fnise.getMessage());
        }
    }

    private static void removeAmountOfFoodFromStock(Stock stock, Food food, int amount) {
        try {
            stock.removeFromStock(food, amount);
        } catch (NotEnoughFoodInStockException nefise) {
            System.err.println(nefise.getMessage());
            System.out.println("You need to order more!");
        } catch (FoodNotInStockException fnise) {
            System.err.println(fnise.getMessage());
            System.out.println("No stock for this item!");
        }
    }
}