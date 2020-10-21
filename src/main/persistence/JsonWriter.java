package persistence;

import model.MyMenu;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// The implementation of JsonWriter is based on the following Github code
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: write the menu to file
    public void write(MyMenu myMenu) throws FileNotFoundException {
        //open
        writer = new PrintWriter(destination);

        //write
        writer.print(myMenu.toJson().toString(4));

        //close
        writer.close();
    }
}
