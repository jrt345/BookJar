package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NotesController {

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
}
