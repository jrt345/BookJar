package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NotesViewerController {

    String notes;
    @FXML
    private TextArea notesAreaView;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void loadNotes() {
        notesAreaView.setText(notes);
    }

    @FXML
    void cancelNotesView(ActionEvent event) {
        Book.closeStage();
    }

    @FXML
    void resetNotesView(ActionEvent event) {
        notesAreaView.setText("");
    }

    @FXML
    void saveNotesView(ActionEvent event) {
        notes = notesAreaView.getText();
        Book.closeStage();
    }
}
