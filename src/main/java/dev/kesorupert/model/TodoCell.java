package dev.kesorupert.model;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TodoCell extends CharmListCell<Todo> {

    private final ListTile listTile;
    private Todo currentTodoItem;

    public TodoCell(){
        listTile = new ListTile();

        Button checkButton = MaterialDesignIcon.CHECK.button();
        checkButton.setOnAction(e -> System.out.println("check button is pressed"));
//        HBox buttonBar = new HBox(checkButton);
//        buttonBar.setAlignment(Pos.CENTER_RIGHT);

        listTile.setSecondaryGraphic(checkButton);
    }

    @Override
    public void updateItem(Todo item, boolean empty){
        super.updateItem(item, empty);
        currentTodoItem = item;
        if (!empty && item != null) {
            listTile.textProperty().setAll(currentTodoItem.getDescription());
            setGraphic(listTile);
        } else {
            setGraphic(null);
        }
    }

}
