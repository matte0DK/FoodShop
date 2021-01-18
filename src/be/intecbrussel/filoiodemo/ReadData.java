package be.intecbrussel.filoiodemo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadData {
    public String readStringFromFile() {
        File textFile = new File("resources/MyTextFile.txt");

        try {
            FileReader fileReader = new FileReader(textFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Animal> readAllObjectsFromFile() {

       List<Animal> animals = new ArrayList<Animal>();
       ObjectInputStream objectInputStream;

        try {
            File myObjectFile = new File("resources/MyObjectFile.txt");
            FileInputStream fileInputStream = new FileInputStream(myObjectFile);
            objectInputStream = new ObjectInputStream(fileInputStream);

            Animal animal;
            while ((animal = (Animal) objectInputStream.readObject()) != null) {
                animals.add(animal);
            }
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
        return animals;

    }

}