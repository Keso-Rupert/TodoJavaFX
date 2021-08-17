package dev.kesorupert.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * We create a model class that keeps track of the selected exercise, if any, from the ExercisePresenter.
 * We'll use this to either create a new exercise or edit an existing one.
 */
public class TodoSelectionModel {
    private final ObjectProperty<Todo> activeTodo = new SimpleObjectProperty<>();

    public ObjectProperty<Todo> activeTodo() {
        return activeTodo;
    }

}

