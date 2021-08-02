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

    private String notes;

    @FXML
    private TextArea notesArea;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void loadNotes() {
        notesArea.setText(notes);
    }

    @FXML
    private void cancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    private void reset(ActionEvent event) {
        notes = "";
        notesArea.setText(notes);
    }

    @FXML
    private void saveNotes(ActionEvent event) {
        notes = notesArea.getText();
        stage.close();
    }

    public String getNotesAreaString() {
        return notesArea.getText();
    }

    @FXML
    private Button saveNotesButton;

    public void pushSaveButton() {
        saveNotesButton.fire();
    }
}
