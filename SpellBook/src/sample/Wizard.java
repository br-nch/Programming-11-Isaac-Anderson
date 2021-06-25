package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Wizard {
    private String name;
    private int level;
    private ArrayList<Spell> spellList;
    private static int conjuration = 0;
    private static int necromancy = 0;
    private static int evocation = 0;
    private static int abjuration = 0;
    private static int transmutation = 0;
    private static int divination = 0;
    private static int enchantment = 0;
    private static int illusion = 0;
    private static int highest = 0;
    private static String favSchool;

    //Default constructor for wizard
    public Wizard() {
        this.name = "";
        this.level = 1;
        this.spellList = new ArrayList<>();
    }

    public Wizard(String name, ArrayList spellList) {
        this.name = name;
        this.level = 1;
        this.spellList = spellList;
    }

    //Requires: integer > current level
    //Modifies: this
    //Effects: increases this wizard's level by specified amount
    public void changeLevel(int amount) {
         this.level += amount;
    }

    //Modifies: this
    //Effects: adds spell to this spell list
    public void addSpell(Spell spell) {
        this.spellList.add(spell);
    }

    //Requires: this.spellList != null
    //Effects: compares the amount of spells this character has of each school and returns the most common
    public String getFavouriteSchool() {
        conjuration = 0;
        necromancy = 0;
        evocation = 0;
        abjuration = 0;
        transmutation = 0;
        divination = 0;
        enchantment = 0;
        illusion = 0;
        highest = 0;
        favSchool = null;

        for (int i = 0; i < spellList.size(); i++){
            switch (spellList.get(i).getSchool()) {
                case "Conjuration": {
                    conjuration++;
                    checkFavouriteTie(favSchool, "Conjuration", highest, conjuration);
                    if (conjuration > highest) {
                        highest = conjuration;
                        favSchool = "Conjuration";
                    }
                    break;
                }
                case "Necromancy": {
                    necromancy++;
                    checkFavouriteTie(favSchool, "Necromancy", highest, necromancy);
                    if (necromancy > highest) {
                        highest = necromancy;
                        favSchool = "Necromancy";
                    }
                    break;
                }
                case "Evocation": {
                    evocation++;
                    checkFavouriteTie(favSchool, "Evocation", highest, evocation);
                    if (evocation > highest) {
                        highest = evocation;
                        favSchool = "Evocation";
                    }
                    break;
                }
                case "Abjuration": {
                    abjuration++;
                    checkFavouriteTie(favSchool, "Abjuration", highest, abjuration);
                    if (abjuration > highest) {
                        highest = abjuration;
                        favSchool = "Abjuration";
                    }
                    break;
                }
                case "Transmutation": {
                    transmutation++;
                    checkFavouriteTie(favSchool, "Transmutation", highest, transmutation);
                    if (transmutation > highest) {
                        highest = transmutation;
                        favSchool = "Transmutation";
                    }
                    break;
                }
                case "Divination": {
                    divination++;
                    checkFavouriteTie(favSchool, "Divination", highest, divination);
                    if (divination > highest) {
                        highest = divination;
                        favSchool = "Divination";
                    }
                    break;
                }
                case "Enchantment": {
                    enchantment++;
                    checkFavouriteTie(favSchool, "Enchantment", highest, enchantment);
                    if (enchantment > highest) {
                        highest = enchantment;
                        favSchool = "Enchantment";
                    }
                    break;
                }
                case "Illusion": {
                    illusion++;
                    checkFavouriteTie(favSchool, "Illusion", highest, illusion);
                    if (illusion > highest) {
                        highest = illusion;
                        favSchool = "Illusion";
                    }
                    break;
                }
            }
        }
        return favSchool;
    }

    //Modifies: favschool
    //Effects: if this spell school has is tied for the most spells, notes that
    private void checkFavouriteTie(String prev, String current, int prevScore, int currentScore) {
        if (currentScore == prevScore) {
            favSchool = prev + " and " + current;
        }
    }

    //Requires: Wizard has full information
    //Modifies: file
    //Effects: writes selected wizard to file
    public void writeFile() throws IOException {
        FileWriter fw = new FileWriter(this.name + ".txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(this.name + "@\r");
        bw.write(this.level + "@\r");
        bw.close();
        for (int i = 0; i < this.spellList.size(); i++) {
            this.spellList.get(i).writeToFile(this.name + ".txt");
        }
    }

    //Effects: returns wizard name
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Spell> getSpellList() {
        return spellList;
    }

    public void setSpellList(ArrayList<Spell> spellList) {
        this.spellList = spellList;
    }
}

