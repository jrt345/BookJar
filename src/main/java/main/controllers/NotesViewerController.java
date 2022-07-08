package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import main.utils.Book;

public class NotesViewerController {

    String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @FXML
    private TextArea notesAreaView;

    public void loadNotes() {
        notesAreaView.setText(notes);
    }

    @FXML
    void exitNotesView(ActionEvent event) {
        Book.closeStage();
    }
}
