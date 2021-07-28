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
    void exitNotesView(ActionEvent event) {
        Book.closeStage();
    }
}
