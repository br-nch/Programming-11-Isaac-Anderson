package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Spell {
    private String name;
    private String description;
    private int level;
    private String castingTime;
    private String school;
    private String components;
    private int range;
    private int duration;
    private boolean concentration;

    //Default constructor for spells
    public Spell(){
        this.name = "";
        this.school = "Evocation";
        this.level = 0;
        this.castingTime = "1 Action";
        this.components = "VSM";
        this.range = 0;
        this.duration = 0;
        this.description = "";
        this.concentration = false;
    }

    public Spell(String name, String school, int level, String castingTime, String components, int range, int duration, String description, boolean concentration) {
        this.name = name;
        this.school = school;
        this.level = level;
        this.castingTime = castingTime;
        this.components = components;
        this.range = range;
        this.duration = duration;
        this.description = description;
        this.concentration = concentration;
    }

    //Requires: spell with full information, valid file name
    //Modifies: Inputted file
    //Effects: writes this spell to inputted file
    public void writeToFile(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(this.name + "|\r");
        bw.write(this.school + "|\r");
        bw.write(this.level + "|\r");
        bw.write(this.castingTime + "|\r");
        bw.write(this.components + "|\r");
        bw.write(this.range + "|\r");
        bw.write(this.duration + "|\r");
        bw.write(this.description + "|\r");
        bw.write(this.concentration + "\r");
        bw.write("¤\r");
        bw.close();
    }

    //Effects: returns spell's name
    public String toString() {
        return this.name;
    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }


}
