package dev.kesorupert.service;

import dev.kesorupert.model.State;
import dev.kesorupert.model.Todo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TemporaryTodoService {

    private static Todo todo1, todo2, todo3;

    static {
        todo1 = new Todo("Do some dishes");
        todo2 = new Todo("Write a paper");
        todo3 = new Todo("Workout");
        todo3.setState(State.DONE);
    }

    private static ObservableList<Todo> todoList = FXCollections.observableArrayList(todo1, todo2, todo3);

    public ObservableList<Todo> getTodoList(){
        return todoList;
    }

    public Todo addTodo(Todo todo){
        todoList.add(todo);
        return todo;
    }

    public void removeTodo(Todo todo){
        todoList.remove(todo);
    }
}
