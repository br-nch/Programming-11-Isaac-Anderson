package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loading {
    private static String charName;
    private static int charLevel;
    private static ArrayList<Spell> spellList;
    private static String spellName;
    private static String description;
    private static int spellLevel;
    private static String castingTime;
    private static String school;
    private static String components;
    private static int range;
    private static int duration;
    private static boolean concentration;
    private static FileReader fr;
    private static BufferedReader br;

    //Requires: existing file with full information
    //Effects: reads file line by line, converting to string; parses wizard details; calls on readSpell, returns completed wizard
    public static Wizard loadWizard(String fileName) throws IOException {
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);
        String line;
        String fullWizard = "";
        int pos = 0;
        int atFound = 0;
        boolean isBorder;
        Wizard wizard;

        while ((line = br.readLine()) != null){
            fullWizard += line;
        }

        br.close();

        for (int i = 0; i < fullWizard.length(); i++) {

            if (fullWizard.charAt(i) == '@') {
                isBorder = true;
                atFound++;
            }
            else isBorder = false;

            if (isBorder && atFound == 1) {
                pos = i;
                charName = fullWizard.substring(0, pos);
                charLevel = Integer.parseInt(fullWizard.substring(pos+1, pos+2));
            }
            else if (isBorder && atFound == 2) {
                pos = i;
                readSpells(fullWizard.substring(pos+1));
            }
        }
        wizard = new Wizard(charName, spellList);
        wizard.setLevel(charLevel);
        return wizard;
    }

    //Requires: properly formatted string of spells
    //Effects: seperates individual spells from eachother then sends them to be parsed
    private static void readSpells(String spells) {
        int pos = 0;
        int prevPos;
        spellList = new ArrayList<>();

        for (int i = 0; i < spells.length(); i++) {
            if (spells.charAt(i) == '¤'){
                prevPos = pos;
                pos = i+1;
                parseSpell(spells.substring(prevPos, pos));
            }
        }
    }

    //Requires: spell in string form with full information
    //Modifies: loading wizard's spell list
    //Effects: parses spell from string then adds it to spell list.
    private static void parseSpell(String spell) {
        int pos = -1;
        int prevPos;
        int lineFound = 0;
        boolean isBorder;
        String subSpell;

        for (int i = 0; i < spell.length(); i++) {
            if (spell.charAt(i) == '|') {
                isBorder = true;
                lineFound++;
            }
            else isBorder = false;

            if (isBorder) {
                prevPos = pos+1;
                pos = i;
                subSpell = spell.substring(prevPos, pos);

                switch (lineFound){
                    case 1 : spellName = subSpell; break;
                    case 2 : school = subSpell; break;
                    case 3 : spellLevel = Integer.parseInt(subSpell); break;
                    case 4 : castingTime = subSpell; break;
                    case 5 : components = subSpell; break;
                    case 6 : range = Integer.parseInt(subSpell); break;
                    case 7 : duration = Integer.parseInt(subSpell); break;
                    case 8 : description = subSpell; break;
                    case 9 : concentration = Boolean.getBoolean(subSpell); break;
                }
            }
        }
        spellList.add(new Spell(spellName, school, spellLevel, castingTime, components, range, duration, description, concentration));
    }
}
