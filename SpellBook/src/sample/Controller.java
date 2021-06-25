package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    public ListView listChars;
    public Button btnSubmitChar;
    public Button btnAddChar;
    public Label lblCharName;
    public Label lblCharLvl;
    public Button btnLvlUp;
    public Button btnDeleteChar;
    public TextField textSpellName;
    public ChoiceBox cboxLvl;
    public ChoiceBox cboxSchool;
    public TextField textTime;
    public TextField textRange;
    public TextField textDuration;
    public CheckBox checkConcentration;
    public TextArea textDescription;
    public Button btnAddSpell;
    public ListView listSpells;
    public Label lblSpellName;
    public Label lblSpellLevel;
    public Label lblSchool;
    public Label lblCastTime;
    public Label lblComponents;
    public Label lblRange;
    public Label lblDuration;
    public TextArea flowDescription;
    public Button btnDeleteSpell;
    public Label lblFavSchool;
    public TextField textCharName;
    public TextField textCharFileName;
    public CheckBox checkVerbal;
    public CheckBox checkSomatic;
    public CheckBox checkMaterial;
    public Label lblOwner;
    public Button btnSaveChar;
    public Button btnLoadChar;
    private ArrayList<Wizard> characters = new ArrayList<>();
    private static String name;
    private static String description;
    private static int level;
    private static String castingTime;
    private static String school;
    private static String components;
    private static int range;
    private static int duration;
    private static boolean concentration;
    private static Wizard selectedChar;

    //Requires: something to select
    //Modifies: selected character, spell list, character display
    //Effects: Sets selected character as active character, modifying the GUI and internal data accoordingly; enables buttons etc that require an active character.
    public void selectChar(MouseEvent mouseEvent) {
        Wizard selected = (Wizard) listChars.getSelectionModel().getSelectedItem();
        lblCharName.setText(selected.getName());
        lblCharLvl.setText(Integer.toString(selected.getLevel()));
        btnDeleteChar.setDisable(false);
        btnSaveChar.setDisable(false);
        if (selected.getLevel() < 9) btnLvlUp.setDisable(false);
        if (selected.getSpellList() != null) lblFavSchool.setText(selected.getFavouriteSchool());
        selectedChar = selected;
        lblOwner.setText(selected.getName() + "'s Spellbook");
        listSpells.getItems().setAll(selected.getSpellList());
    }

    //Modifies: name, add character button
    //Effects: records inputted name, then enables add character button
    public void submitChar(ActionEvent actionEvent) {
        if (textCharName.getText().length() != 0) {
            name = textCharName.getText();
            btnAddChar.setDisable(false);
        }
        else  btnAddChar.setDisable(true);
    }

    //Modifies: character list, add character button & textfield
    //Effects: Adds submitted character to the list then resets character adding display
    public void addChar(ActionEvent actionEvent) {
        characters.add(new Wizard(name, new ArrayList()));
        listChars.getItems().setAll(characters);
        textCharName.clear();
        btnAddChar.setDisable(true);
        listChars.refresh();
    }

    //Modifies: selected wizard, character display
    //Effects: Increases the level of selected wizard by 1
    public void lvlUp(ActionEvent actionEvent) {
        Wizard selected = (Wizard) listChars.getSelectionModel().getSelectedItem();
        selected.changeLevel(1);
        lblCharLvl.setText(Integer.toString(selected.getLevel()));
        if (selected.getLevel() == 20) btnLvlUp.setDisable(true);
    }

    //Modifies: Character list, selected wizard, character display, spell display
    //Effects: removes selected character from the list and clears the labels displaying their information
    public void deleteChar(ActionEvent actionEvent) {
        Wizard selected = (Wizard) listChars.getSelectionModel().getSelectedItem();
        characters.remove(selected);
        listChars.getItems().setAll(characters);
        listChars.refresh();
        lblCharLvl.setText("");
        lblCharName.setText("");
        lblFavSchool.setText("");
        btnDeleteChar.setDisable(true);
        btnLvlUp.setDisable(true);
        selectedChar = null;
        lblOwner.setText("");
        listSpells.getItems().setAll();
        lblSpellName.setText("");
        lblSpellLevel.setText("");
        lblCastTime.setText("");
        lblComponents.setText("");
        lblSchool.setText("");
        lblDuration.setText("");
        lblRange.setText("");
        flowDescription.setText("");
        btnDeleteSpell.setDisable(true);
    }

    //Modifies: spell details, add spell button
    //Effects:records inputted data, enables add spell button
    public void submitSpell(ActionEvent actionEvent) {
        if (textSpellName.getText().length() != 0 && !cboxLvl.getSelectionModel().isEmpty() && !cboxSchool.getSelectionModel().isEmpty() && textTime.getText().length() != 0 && textDuration.getText().length() != 0 && textRange.getText().length() != 0 && textDescription.getText().length() != 0 && selectedChar != null) {
            name = textSpellName.getText();
            description = textDescription.getText();
            level = Integer.parseInt(String.valueOf(cboxLvl.getSelectionModel().getSelectedItem()));
            castingTime = textTime.getText();
            school = String.valueOf(cboxSchool.getSelectionModel().getSelectedItem());
            components = parseComponents();
            range = Integer.parseInt(textRange.getText());
            duration = Integer.parseInt(textDuration.getText());
            concentration = checkConcentration.isSelected();
            btnAddSpell.setDisable(false);
        }
        else btnAddSpell.setDisable(true);
    }

    //Effects: returns some combination of the letters "VSM" in relation to which component boxes are checked
    private String parseComponents() {
        String parsing = "";
        if (checkVerbal.isSelected()) parsing += "V";
        if (checkSomatic.isSelected()) parsing += "S";
        if (checkMaterial.isSelected()) parsing += "M";
        return parsing;
    }

    //Modifies: Add spell screen, spell list, favourite school
    //Effects: adds submitted spell to the list; resets add spell screen; recalulates favourite school
    public void addSpell(ActionEvent actionEvent) {
        selectedChar.addSpell(new Spell(name, school, level, castingTime, components, range, duration, description, concentration));
        listSpells.getItems().setAll(selectedChar.getSpellList());
        textSpellName.clear();
        textDescription.clear();
        textRange.clear();
        textDuration.clear();
        textCharName.clear();
        textTime.clear();
        checkConcentration.setSelected(false);
        checkMaterial.setSelected(false);
        checkSomatic.setSelected(false);
        checkVerbal.setSelected(false);
        listSpells.refresh();
        btnAddSpell.setDisable(true);
        lblFavSchool.setText(selectedChar.getFavouriteSchool());
    }

    //Modifies: spell display
    //Effects: Sets the spell screen to display the information of the selected spell
    public void selectSpell(MouseEvent mouseEvent) {
        Spell selected = (Spell) listSpells.getSelectionModel().getSelectedItem();
        String selectedDuration = String.valueOf(selected.getDuration());
        if (selected.isConcentration()) selectedDuration += " (Concentration)";
        lblSpellName.setText(selected.getName());
        lblSpellLevel.setText(String.valueOf(selected.getLevel()));
        lblCastTime.setText(selected.getCastingTime());
        lblComponents.setText(selected.getComponents());
        lblSchool.setText(selected.getSchool());
        lblDuration.setText(selectedDuration);
        lblRange.setText(String.valueOf(selected.getRange()));
        flowDescription.setText(selected.getDescription());
        btnDeleteSpell.setDisable(false);
    }

    //Modifies: spell list, spell display
    //Effects: deletes spell from the spell list and resets spell display
    public void deleteSpell(ActionEvent actionEvent) {
        Spell selected = (Spell) listSpells.getSelectionModel().getSelectedItem();
        selectedChar.getSpellList().remove(selected);
        listSpells.getItems().remove(selected);
        lblSpellName.setText("");
        lblSpellLevel.setText("");
        lblCastTime.setText("");
        lblComponents.setText("");
        lblSchool.setText("");
        lblDuration.setText("");
        lblRange.setText("");
        flowDescription.setText("");
        btnDeleteSpell.setDisable(true);
    }

    //Effects: activates the writeFile method for selected character
    public void saveChar(ActionEvent actionEvent) throws IOException {
        selectedChar.writeFile();
    }

    //Requires: valid character name
    //Modifies: character list
    //Effects: Reads from file, then adds it to characterlist
    public void loadChar(ActionEvent actionEvent) throws IOException{
        boolean isThere = false;
        for (int i = 0; i < listChars.getItems().size(); i++) {
            if (listChars.getItems().get(i).toString().equals(textCharFileName.getText())) isThere = true;
        }
        if (!textCharFileName.getText().isEmpty() && !isThere) {
            listChars.getItems().add(Loading.loadWizard(textCharFileName.getText() + ".txt"));
        }
    }
}
