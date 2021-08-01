package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class NotesController {

    private String notes;

    @FXML
    private TextArea notesArea;

    @FXML
    private Button saveNotesButton;

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
        Controller.notesStage.close();
    }

    @FXML
    private void reset(ActionEvent event) {
        notes = "";
        notesArea.setText(notes);
    }

    @FXML
    private void saveNotes(ActionEvent event) {
        notes = notesArea.getText();
        Controller.notesStage.close();
    }

    public String getNotesAreaString() {
        return notesArea.getText();
    }

    public void pushSaveButton() {
        saveNotesButton.fire();
    }
}
