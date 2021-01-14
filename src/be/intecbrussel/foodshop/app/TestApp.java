package be.intecbrussel.foodshop.app;

import be.intecbrussel.foodshop.exception.FoodAlreadyInStockException;
import be.intecbrussel.foodshop.exception.FoodNotInStockException;
import be.intecbrussel.foodshop.exception.NotEnoughFoodInStockException;
import be.intecbrussel.foodshop.exception.NotEnoughMoneyException;
import be.intecbrussel.foodshop.model.*;

import java.util.Map;

public class TestApp {
    public static void main(String[] args) {
        Stock stock = new Stock();
        FoodShop foodShop = new FoodShop(stock);
        Customer veryRichHuman = new Customer("Matteo", 1, "matteo.dkerpel@intecbrussel.be", 200);

        Food apple = new Food("Apple");
        Food pollo = new Food("Chicken");
        Food patat = new Food("Potato");
        Food hamburger = new Food("Big mac");
        Food pizza = new Food("Pizza");

        addFoodToStock(stock, pizza);
        addFoodToStock(stock, patat);
        addFoodToStock(stock, apple);

        addAmountOfFoodToStock(stock, pizza, 10);
        addAmountOfFoodToStock(stock, patat, 30);
        addAmountOfFoodToStock(stock, apple, 5);

        Order myLilOrder = new Order();
        myLilOrder.addFoodToOrder(pizza, 2);
        myLilOrder.addFoodToOrder(hamburger, 2);
        myLilOrder.addFoodToOrder(patat, 5);
        myLilOrder.addFoodToOrder(apple, 100);

        System.out.println("======= ORDER ========");
        myLilOrder.getFoodItems().forEach((food, amount) -> System.out.println(amount + " " + food));
        System.out.println();
        System.out.println("======= STOCK ========");
        stock.getFoodStock().forEach((food, amount) -> System.out.println(amount + " " + food));
        System.out.println();

        myLilOrder.applyDiscount(-50);

        try {
            foodShop.sellFood(myLilOrder, veryRichHuman);
        } catch (NotEnoughFoodInStockException notEnoughFoodInStockException) {
            System.out.println(notEnoughFoodInStockException.getMessage());
            System.out.println("we don't have enough of this to make the order, says enough about the order if u ask me.");
        } catch (FoodNotInStockException foodNotInStockException) {
            System.out.println(foodNotInStockException.getMessage());
            System.out.println("what are you even ordering, reading a menu is that too much to ask??");
        } catch (NotEnoughMoneyException notEnoughMoneyException) {
            System.out.println(notEnoughMoneyException.getMessage());
            System.out.println("Again, get a job u bum.");
        }

        // test addMethods
//        addFoodToStock(stock, pizza);
//        addAmountOfFoodToStock(stock, pizza, 99);
//
//        addFoodToStock(stock, pizza);
//        addAmountOfFoodToStock(stock, pizza, 99);
//
//        addFoodToStock(stock, patat);
//        addAmountOfFoodToStock(stock, patat, 50);
//
//        print(stock);

        // test removeMethods
//        removeAmountOfFoodFromStock(stock, patat, 35);
//        print(stock);
//
//        removeAmountOfFoodFromStock(stock, pizza, 69);
//        print(stock);
//
//        removeAmountOfFoodFromStock(stock, apple, 77);
//
//        stock.removeFood(patat);
//        print(stock);

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