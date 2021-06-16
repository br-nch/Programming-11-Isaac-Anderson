package sample;

import java.io.*;
import java.util.ArrayList;

public class SaveLoad {
    private static String name;
    private static int age;
    private static String colour;
    private static String food;
    private static FileReader fr;
    private static BufferedReader br;
    private static ArrayList<Friend> friends = new ArrayList<>();

    //Requires: selected file is properly formatted
    //Modifies: friends list
    //Effects: loads all friends from selected file
    public static ArrayList<Friend> loadFriends(String filename) throws IOException {
        fr = new FileReader(filename + ".txt");
        br = new BufferedReader(fr);
        String currentLine;
        String friendString = "";

        while ((currentLine = br.readLine()) != null) {
            if (!currentLine.equals(";")) friendString += currentLine;
            else {
                readFriend(friendString);
                friendString = "";
            }
        }
        br.close();
        return friends;
    }

    //Modifies: Friends list
    //Effects: parses a friend and returns it to the load function
    public static void readFriend(String friend) {
        int comma = 0;
        int prevComma;
        int field = 0;
        name = "";
        age = 0;
        colour = "";
        food = "";

        for (int i = 0; i < friend.length(); i++) {
            if (friend.charAt(i) == ',') {
                prevComma = comma;
                comma = i;
                switch (field) {
                    case 0:
                        name = friend.substring(0, i);
                        break;
                    case 1:
                        age = Integer.parseInt(friend.substring(prevComma+1, comma));
                        break;
                    case 2: {
                        colour = friend.substring(prevComma+1, comma);
                        food = friend.substring(comma + 1);
                        break;
                    }
                }
                field++;
            }
        }
        if (!Controller.friends.toString().contains(name)) friends.add(new Friend(name, age, colour, food));
    }

    //Modifies: Selected file
    //Effects: saves current friendsbook to the selected file
    public static void saveFriend(Friend friend) throws IOException {
        FileWriter fw = new FileWriter(Controller.fileName + ".txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        FileReader fr = new FileReader(Controller.fileName + ".txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine = "";
        boolean isSaved = false;
        if (br.readLine() != null) {
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.contains(friend.getName())) isSaved = true;
            }
        }
        if (!isSaved) {
            if (currentLine.equals(";")) bw.write("\r");
            bw.write(friend.getName() + ",\r");
            bw.write(friend.getAge() + ",\r");
            bw.write(friend.getFavColour() + ",\r");
            bw.write(friend.getFavFood() + "\r");
            bw.write(";\r");
            bw.close();
        }
    }

}
