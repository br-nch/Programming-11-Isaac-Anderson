package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    private ArrayList<Friend> friends = new ArrayList<>();
    private static String name;
    private static int age;
    private static javafx.scene.paint.Color colour;
    private static String food;

    //Modifies: Friends, button, friend list
    //Effects: Adds a friend to the list then resets the friend creation screen
    public void addFriend(ActionEvent actionEvent) {
        friends.add(new Friend(name, age, colour, food));
        listFriends.getItems().setAll(friends);
        textName.clear();
        textFood.clear();
        textYear.clear();
        btnAddFriend.setDisable(true);
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
            colour = colourPicker.getValue();
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
        Color selectedColour = selected.getFavColour();
        String hexCode = String.format("#%02x%02x%02x", (int)(selectedColour.getRed()*255), (int)(selectedColour.getGreen()*255), (int) (selectedColour.getBlue()*255));
        paneFavColour.setStyle("-fx-background-color: " + hexCode);
        lblDisplayFood.setText(selected.getFavFood());
        btnDeleteFriend.setDisable(false);
    }
}
