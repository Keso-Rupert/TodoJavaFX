package dev.kesorupert.view;

import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class TodoView extends View {

    public static View getView() {
        try {
            View view = FXMLLoader.load(TodoView.class.getResource("todo.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
