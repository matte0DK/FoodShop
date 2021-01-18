package be.intecbrussel.filoiodemo;

import java.io.Serializable;

public class Animal implements Serializable {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
