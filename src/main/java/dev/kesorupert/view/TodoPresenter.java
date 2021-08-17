package dev.kesorupert.view;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import dev.kesorupert.model.State;
import dev.kesorupert.model.Todo;
import dev.kesorupert.model.TodoCell;
import dev.kesorupert.model.TodoSelectionModel;
import dev.kesorupert.service.TemporaryTodoService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class TodoPresenter {
    @FXML
    private View todoView;

    @FXML
    private CharmListView<Todo, LocalDate> todoListView;

    private TemporaryTodoService temporaryTodoService;
    private TodoSelectionModel todoSelectionModel;

    public TodoPresenter(TemporaryTodoService service, TodoSelectionModel model) {
        this.temporaryTodoService = service;
        this.todoSelectionModel = model;
    }

    public void initialize() {
        todoView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setTitleText("Todo Application");
            }
        });

        todoListView.setItems(temporaryTodoService.getTodoList());
        todoListView.setCellFactory(cell -> new TodoCell(todo -> finishTodo(todo)));

        todoListView.setPlaceholder(new Label("There are no Todos yet"));

        FloatingActionButton floatingActionButton = new FloatingActionButton(MaterialDesignIcon.ADD.text,
                e -> {
                    System.out.println("Add new Todo");
                    showAddTodoDialog();
                });
        floatingActionButton.showOn(this.todoView);
    }

    public void finishTodo(Todo todo){
        todo.finishTodo();
    }

    public void showAddTodoDialog() {
        Dialog dialog = new Dialog();
        dialog.setTitleText("Add new todo");
        TextField inputTodo = new TextField();
        inputTodo.setPromptText("What needs todo'ing?");
        dialog.setContent(inputTodo);
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            String todo = inputTodo.getCharacters().toString();
            if (todo != null && !todo.isEmpty()) {
                System.out.println("Saving the todo");
                temporaryTodoService.addTodo(new Todo(inputTodo.getText()));
            }
            dialog.hide();
        });
        dialog.getButtons().add(okButton);
        dialog.showAndWait();
    }
}
