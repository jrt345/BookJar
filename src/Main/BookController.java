package Main;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BookController {
    @FXML
    TextField titleField;

    @FXML
    TextField authorField;

    @FXML
    TextField genreField;

    @FXML
    TextArea notesField;

    public void addBook() {
        System.out.println(titleField.getText());
        Controller.stage.close();
    }

    public void reset() {
        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
        notesField.setText("");
    }

    public void cancel() {

    }
}
