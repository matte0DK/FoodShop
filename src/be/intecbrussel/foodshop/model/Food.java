package be.intecbrussel.foodshop.model;

public class Food {
    private String name;
    private double price;
    private double calories;
    private String brand;

    public Food(String name) {
        this.name = name;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getBrand() {
        return this.brand;
    }

    public double getPrice() {
        return this.price;
    }

    public double getCalories() {
        return this.calories;
    }

    // setters
    public void setPrice(double price) {
        this.price = price;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }


}
