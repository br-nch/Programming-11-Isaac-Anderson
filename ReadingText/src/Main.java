import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Integer> locate(String word) throws IOException {
        //Creates variables
        ArrayList<Integer> positions = new ArrayList<>();
        FileReader fr = new FileReader("ProgrammingHistory.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int i = 0;

        //While current line is not null, checks whether current line contains word, if yes notes it down. Increases index by 1.
        while ((line = br.readLine()) != null) {
            if (line.contains(word)) positions.add(i);
            i++;
        }

        //Closes reader and returns an arraylist of all index positions of the word
        br.close();
        return positions;
    }

    public static void main(String[] args) throws IOException {
        //Creates variables
        ArrayList<String> contents = new ArrayList<>();
        FileReader fr = new FileReader("ProgrammingHistory.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;

        //While current line is not null, adds current line to the arraylist
        while ((line = br.readLine()) != null) {
            contents.add(line);
        }

        //Closes reader
        br.close();
    }
}
