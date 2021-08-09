package dev.kesorupert.service;

import com.gluonhq.cloudlink.client.data.DataClient;
import com.gluonhq.cloudlink.client.data.DataClientBuilder;
import com.gluonhq.cloudlink.client.data.OperationMode;
import com.gluonhq.cloudlink.client.data.SyncFlag;
import com.gluonhq.connect.ConnectState;
import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.provider.DataProvider;
import dev.kesorupert.model.Todo;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import javax.annotation.PostConstruct;

/**
 * Service class that will handle the persistence of the Todos
 */
public class TodoService {

    // Identifier for the DataClient
    private static final String TODOS = "todos-v0";

    // Wrapper of an observable list of todos
    private final ListProperty<Todo> todoList = new SimpleListProperty<>(FXCollections.observableArrayList());

    private DataClient dataClient;

    @PostConstruct
    public void postconstruct(){
        // Use OperationMode.LOCAL_ONLY to indicate that all data operations will be exclusively local.
        // To use CLOUD you need a CloudLink account
        dataClient = DataClientBuilder.create()
                .operationMode(OperationMode.LOCAL_ONLY)
                .build();
    }

    public void retrieveTodos() {
        // SyncFlag.LIST_WRITE_THROUGH, so changes in the list are automatically stored locally
        // SyncFlag.OBJECT_WRITE_THROUGH, so changes in the properties of the list are also stored locally
        GluonObservableList<Todo> gluonTodoList = DataProvider.retrieveList(
                dataClient.createListDataReader(TODOS, Todo.class,
                        SyncFlag.LIST_WRITE_THROUGH, SyncFlag.OBJECT_WRITE_THROUGH));

        gluonTodoList.stateProperty().addListener((observable, oldValue, newValue) -> {
            if (ConnectState.SUCCEEDED.equals(newValue)) {
                todoList.set(gluonTodoList);
            }
        });
    }

    public Todo addTodo(Todo todo){
        todoList.get().add(todo);
        return todo;
    }

    public void removeTodo(Todo todo){
        todoList.get().remove(todo);
    }

    public ListProperty<Todo> todoListProperty(){
        return  todoList;
    }


}
