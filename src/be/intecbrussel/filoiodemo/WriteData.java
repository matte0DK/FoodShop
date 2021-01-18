package be.intecbrussel.filoiodemo;

import be.intecbrussel.foodshop.model.Food;

import java.io.*;

public class WriteData {
    public void writeStringToFile(String text) {
        File textFile = new File("resources/MyTextFile.txt"); // relative path
        File textFileTest = new File("C:\\Users\\Matteo\\IdeaProjects\\FoodShop\\resources\\MyTextFile.txt"); // absolute path

        try {
            FileWriter fileWriter = new FileWriter(textFile, true);
            fileWriter.write(text);
            fileWriter.append(text);
            fileWriter.append(", Manu loves potato");
            fileWriter.close(); // we moeten onze filereader beeindigen met .close() aangezien er anders niets opgeslagen wordt bij het eindigen van het programma.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeObjectsToFile(Animal animal) {
        File myObj = new File("resources/MyObjectFile.txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(myObj);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(animal);

            objectOutputStream.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
