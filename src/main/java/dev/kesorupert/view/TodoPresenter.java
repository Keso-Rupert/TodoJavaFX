package dev.kesorupert.view;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import dev.kesorupert.model.Todo;
import dev.kesorupert.model.TodoCell;
import dev.kesorupert.service.TemporaryTodoService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class TodoPresenter {
    @FXML
    private View todoView;

    @FXML
    private CharmListView<Todo, LocalDate> todoListView;

    private TemporaryTodoService temporaryTodoService;

    public TodoPresenter(TemporaryTodoService service) {
        this.temporaryTodoService = service;
    }

    public void initialize(){
        todoView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setTitleText("Todo Application");
            }
        });

        todoListView.setItems(temporaryTodoService.getTodoList());
        todoListView.setCellFactory(cell -> new TodoCell());

        todoListView.setPlaceholder(new Label("There are no Todos yet"));

        FloatingActionButton floatingActionButton = new FloatingActionButton(MaterialDesignIcon.ADD.text,
                e -> System.out.println("Add new Todo"));
        floatingActionButton.showOn(this.todoView);
    }
}
