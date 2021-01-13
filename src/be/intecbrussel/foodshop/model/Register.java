package be.intecbrussel.foodshop.model;

import be.intecbrussel.foodshop.exception.NotEnoughMoneyInRegisterException;

public class Register {
    private double money;

    public double getMoney() {
        return this.money;
    }

    public void addMoney(double amount) {
        this.money += amount;
    }

    public void deductMoney(double amount) throws NotEnoughMoneyInRegisterException {
        if (amount > money) {
            throw new NotEnoughMoneyInRegisterException("There is not enough money in the register to proceed this transaction: " + money
                                                                                                + "€ | Trying to deduct: " + amount + "€");
        } else {
            this.money -= amount;
        }
    }
}
