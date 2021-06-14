package sample;

public class Friend {
    private String name;
    private int age;
    private String favColour;
    private String favFood;

    Friend() {
        this.name = "";
        this.age = 0;
        this.favColour = "#000000";
        this.favFood = "";
    }
    Friend(String name, int age, String favColour, String favFood) {
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

    public String getFavColour() {
        return favColour;
    }

    public String getFavFood() {
        return favFood;
    }
}
