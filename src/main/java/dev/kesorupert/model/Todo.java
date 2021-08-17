package dev.kesorupert.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/**
 * Model class for the Todos
 */
public class Todo {

    private StringProperty description = new SimpleStringProperty();
    private State state;
    private LocalDate creationDate;
    private LocalDate finishDate;

    public Todo(String description) {
        this.description.set(description);
        this.state = State.TODO;
        this.creationDate = LocalDate.now();
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void finishTodo() {
        this.state = State.DONE;
        this.finishDate = LocalDate.now();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
