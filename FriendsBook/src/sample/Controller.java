package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;

public class Controller {
    public TextField textName;
    public TextField textYear;
    public ColorPicker colourPicker;
    public TextField textFood;
    public Button btnAddFriend;
    public Label lblDisplayName;
    public Label lblDisplayAge;
    public Pane paneFavColour;
    public Label lblDisplayFood;
    public Button btnDeleteFriend;
    public ListView listFriends;
    public Button btnLoad;
    public Button btnSave;
    public TextField textFileName;
    public static ArrayList<Friend> friends = new ArrayList<>();
    private static String name;
    private static int age;
    private static String colour;
    private static String food;
    public static String fileName;


    //Modifies: Friends, button, friend list
    //Effects: Adds a friend to the list then resets the friend creation screen
    public void addFriend(ActionEvent actionEvent) {
        friends.add(new Friend(name, age, colour, food));
        listFriends.getItems().setAll(friends);
        textName.clear();
        textFood.clear();
        textYear.clear();
        btnAddFriend.setDisable(true);
        if (fileName != null) btnSave.setDisable(false);
        listFriends.refresh();
    }

    //Requires: Friend selected
    //Modifies: Friends, friend list, button, labels
    //Effects: Removes selected friend from the list and resets the screen
    public void deleteFriend(ActionEvent actionEvent) {
        friends.remove((Friend) listFriends.getSelectionModel().getSelectedItem());
        listFriends.getItems().setAll(friends);
        lblDisplayFood.setText("");
        lblDisplayAge.setText("");
        lblDisplayName.setText("");
        paneFavColour.setStyle("-fx-background-color: #ffffff");
        listFriends.refresh();
        btnDeleteFriend.setDisable(true);
    }

    //Modifies: button
    //Effects: checks whether add friend screen has enough info to be added and submits info if so
    public void checkAllFull(ActionEvent actionEvent) {
        if (textName.getText() != null && textYear.getText() != null && textFood.getText() != null) {
            btnAddFriend.setDisable(false);
            name = textName.getText();
            age = Integer.parseInt(textYear.getText());
            Color selectedColour = colourPicker.getValue();
            colour = String.format("#%02x%02x%02x", (int)(selectedColour.getRed()*255), (int)(selectedColour.getGreen()*255), (int) (selectedColour.getBlue()*255));
            food = textFood.getText();
        }
        else {
            btnAddFriend.setDisable(true);
        }
    }

    //Effects: Displays details of selected friend
    public void displayFriend(MouseEvent mouseEvent) {
        Friend selected = (Friend) listFriends.getSelectionModel().getSelectedItem();
        lblDisplayName.setText(selected.getName());
        lblDisplayAge.setText(String.valueOf(selected.getAge()));
        paneFavColour.setStyle("-fx-background-color: " + selected.getFavColour());
        lblDisplayFood.setText(selected.getFavFood());
        btnDeleteFriend.setDisable(false);
    }

    //Modifies: friends list
    //Effects: Adds friends from a text file to the program
    public void loadFriends(ActionEvent actionEvent) throws IOException {
        friends.addAll(SaveLoad.loadFriends(fileName));
        listFriends.getItems().setAll(friends);
        listFriends.refresh();
    }

    //Requires: some existing friends
    //Modifies: selected text file
    //Effects: saves all friends to the selected file
    public void saveFriends(ActionEvent actionEvent) throws IOException {
        for (Friend i : friends) {
            SaveLoad.saveFriend(i);
        }
    }

    //Requires: Valid file name not including file type
    //Modifies: selected file, save and load buttons
    //Effects: selects a file to be saved to or loaded from
    public void selectFile() {
        if (textFileName.getText() != null && (!textFileName.getText().contains(" "))) {
            fileName = textFileName.getText();
            btnLoad.setDisable(false);
            if (listFriends.getItems() != null) btnSave.setDisable(false);
        }
    }


}
