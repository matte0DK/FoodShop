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
    public FoodShop() {
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
        // check if all food is available
        enoughStockForOrder(order);

        // calculate the total order price and check of customer has enough money
        customerHasEnoughMoney(customer, order);

        // remove order from stock
        removeFoodFromStock(order);

        // update the money of customer
        updateCustomersMoney(customer, order);

        // add money to register (In which register will I add money ???
        addMoneyToRegister(order);

        return order.getFoodItems();
    }

    // PRIVATE METHODS!!!

    private void enoughStockForOrder(Order order) {
            removeFoodFromStock(order);
    }

    private void customerHasEnoughMoney(Customer customer, Order order) throws NotEnoughMoneyException {
        boolean notEnough = order.getTotalPrice() > customer.getMoney();
        if (notEnough) {
            addFoodBackIntoStock(order);
            throw new NotEnoughMoneyException();
        }
    }

    private void addFoodBackIntoStock(Order order) {
        for (Map.Entry<Food, Integer> entry : order.getFoodItems().entrySet()) {
            try {
                stock.addToStock(entry.getKey(), entry.getValue());
            } catch (FoodNotInStockException foodNotInStockException) {
                // should never be triggered!!!
                foodNotInStockException.printStackTrace();
            }
        }
    }

    private void removeFoodFromStock(Order order) {
        for (Map.Entry<Food, Integer> entry : order.getFoodItems().entrySet()) {
            Food food = entry.getKey();
            Integer amount = entry.getValue();
            try {
                stock.removeFromStock(food, amount);
            } catch (NotEnoughFoodInStockException notEnoughFoodInStockException) {
                order.getFoodItems().remove(food);
            } catch (FoodNotInStockException foodNotInStockException) {
                order.getFoodItems().remove(food);
            }
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
