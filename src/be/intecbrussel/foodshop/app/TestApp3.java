package be.intecbrussel.foodshop.app;

import be.intecbrussel.filoiodemo.Animal;
import be.intecbrussel.filoiodemo.ReadData;
import be.intecbrussel.filoiodemo.WriteData;

public class TestApp3 {
    public static void main(String[] args) {
        Animal animal = new Animal(" chickenito ");
        Animal anotherAnimal = new Animal(" spongebob ");

        WriteData writeData = new WriteData();
        writeData.writeObjectsToFile(animal);
        writeData.writeObjectsToFile(anotherAnimal);

    }
}
