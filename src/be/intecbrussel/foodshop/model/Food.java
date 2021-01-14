package be.intecbrussel.foodshop.model;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(name, price, calories, brand);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Food food = (Food) obj;
        return Double.compare(food.price, price) == 0 &&
                Double.compare(food.calories, calories) == 0 &&
                Objects.equals(name, food.name) &&
                Objects.equals(brand, food.brand);
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                ", brand='" + brand + '\'' +
                '}';
    }
}
