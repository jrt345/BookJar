package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class NotesController {

    private static Stage stage;

    public void setStage(Stage stage) {
        NotesController.stage = stage;
    }

    @FXML
    private TextArea notesArea;

    private String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @FXML
    private Button saveNotesButton;

    public void loadNotes() {
        notesArea.setText(notes);
    }

    public String getNotesAreaString() {
        return notesArea.getText();
    }

    public void pushSaveButton() {
        saveNotesButton.fire();
    }

    @FXML
    private void saveNotes(ActionEvent event) {
        notes = notesArea.getText();
        stage.close();
    }

    @FXML
    private void reset(ActionEvent event) {
        notes = "";
        notesArea.setText(notes);
    }

    @FXML
    private void cancel(ActionEvent event) {
        stage.close();
    }
}
