package dev.kesorupert.view;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import dev.kesorupert.TodoApplication;
import dev.kesorupert.model.Todo;
import dev.kesorupert.service.TodoService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.time.LocalDate;

public class TodoPresenter extends GluonPresenter<TodoApplication> {

    @FXML
    private View todoView;

    @FXML
    private CharmListView<Todo, LocalDate> todoListView;

    @Inject
    private TodoService todoService;

    public void initialize(){
        todoView.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                // Setting the title of the default AppBar in the GlassPane
                getApp().getAppBar().setTitleText("Todo Application");

            }
        });

        todoListView.setPlaceholder(new Label("There are no Todos yet"));

        FloatingActionButton floatingActionButton = new FloatingActionButton(MaterialDesignIcon.ADD.text,
                e -> System.out.println("Add new Todo"));
        floatingActionButton.showOn(this.todoView);
    }

}
