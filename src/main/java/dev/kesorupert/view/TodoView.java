package dev.kesorupert.view;

import com.gluonhq.charm.glisten.mvc.View;
import dev.kesorupert.service.TemporaryTodoService;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class TodoView extends View {

    public static View getView() {
        try {
            TemporaryTodoService service = new TemporaryTodoService();

            FXMLLoader loader = new FXMLLoader(TodoView.class.getResource("todo.fxml"));
            TodoPresenter todoPresenter = new TodoPresenter(service);
            loader.setController(todoPresenter);
            View view = loader.load();
            return view;
        } catch (IOException e) {
            System.out.println("IOException while loading the todo.fxml file: " + e);
            e.printStackTrace();
            return new View();
        }
    }
}
