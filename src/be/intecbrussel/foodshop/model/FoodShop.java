package be.intecbrussel.foodshop.model;

import be.intecbrussel.foodshop.exception.FoodNotInStockException;
import be.intecbrussel.foodshop.exception.NotEnoughFoodInStockException;
import be.intecbrussel.foodshop.exception.NotEnoughMoneyException;
import be.intecbrussel.foodshop.service.CustomerRepository;

import java.util.*;

public class FoodShop {
    private List<Register> registers;
    private Stock stock;
    private CustomerRepository customerRepository;

    // constructor
    public FoodShop(Stock stock) {
        this.stock = stock;
        this.registers = new ArrayList<>();
        registers.add(new Register());
    }

    // getters
    public List<Register> getRegisters() {
        return this.registers;
    }

    public Stock getStock() {
        return this.stock;
    }

    public CustomerRepository getCustomerRepository() {
        return this.customerRepository;
    }

    //setters
    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // SELL FOOD METHOD
    public Map<Food, Integer> sellFood(Order order, Customer customer) throws NotEnoughFoodInStockException, FoodNotInStockException, NotEnoughMoneyException {

        checkStock(order);
        checkCustomerMoney(customer, order);
        removeFoodFromStock(order);
        updateCustomersMoney(customer, order);
        addMoneyToRegister(order);

        return order.getFoodItems();
    }

    // PRIVATE METHODS!!!
    private void checkStock(Order order) throws FoodNotInStockException, NotEnoughFoodInStockException {
        for (Map.Entry<Food, Integer> entry : order.getFoodItems().entrySet()) {
            Food food = entry.getKey();
            Integer amount = entry.getValue();
            checkFoodInStock(food, amount);
        }
    }

    private void checkFoodInStock(Food food, Integer amount) throws NotEnoughFoodInStockException, FoodNotInStockException {
        Map<Food, Integer> foodStock = stock.getFoodStock();

        if (!stock.getFoodStock().containsKey(food)) {
            throw new FoodNotInStockException(food.getName() + "is not in stock!");
        }

        if (foodStock.get(food) < amount) {
            throw new NotEnoughFoodInStockException("There is not enough" + food.getName() + " in stock!");
        }
    }

    private void checkCustomerMoney(Customer customer, Order order) throws NotEnoughMoneyException {
        boolean notEnough = order.getTotalPrice() > customer.getMoney();
        if (notEnough) {
            throw new NotEnoughMoneyException("Get a job u bum! you only have " + customer.getMoney() + " while you're order is this much: " + order.getTotalPrice());
        }
    }

    private void removeFoodFromStock(Order order) throws FoodNotInStockException, NotEnoughFoodInStockException {
        for (Map.Entry<Food, Integer> entry : order.getFoodItems().entrySet()) {
            Food food = entry.getKey();
            Integer amount = entry.getValue();
            stock.removeFromStock(food, amount);
        }
    }

    private void updateCustomersMoney(Customer customer, Order order) {
        customer.setMoney(customer.getMoney() - order.getTotalPrice());
    }

    private void addMoneyToRegister(Order order) {
        registers.get(0).addMoney(order.getTotalPrice());
        // TODO register -> multithreading
    }
}
