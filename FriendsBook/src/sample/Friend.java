package sample;

import javafx.scene.paint.Color;

import java.awt.*;

public class Friend {
    private String name;
    private int age;
    private javafx.scene.paint.Color favColour;
    private String favFood;

    Friend() {
        this.name = "";
        this.age = 0;
        this.favColour = Color.BLACK;
        this.favFood = "";
    }
    Friend(String name, int age, javafx.scene.paint.Color favColour, String favFood) {
        this.name = name;
        this.age = age;
        this.favColour = favColour;
        this.favFood = favFood;
    }

    //Effects: Returns name of friend
    public String toString(){
        return name;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Color getFavColour() {
        return favColour;
    }

    public String getFavFood() {
        return favFood;
    }
}
