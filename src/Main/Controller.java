package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static Stage stage;

    private static int index = 0;
    private static boolean initial = true;
    private static String notes = "";

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, Integer> indexColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TableColumn<Book, Button> notesColumn;

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField genreField;
    @FXML
    private TextArea notesArea;

    @FXML
    private void quitProgram(ActionEvent event) {
        Main.stage.close();
    }

    @FXML
    private void openNotes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("notes.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Notes");
        stage.setScene(new Scene(root, 450, 375));
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void addBook(ActionEvent event) {
        index++;
        Book book = new Book();
        book.setIndex(index);
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setGenre(genreField.getText());
        Button button = new Button("View notes");
        book.setNotesButton(button);
        notesColumn.setStyle("-fx-alignment: CENTER;");
        book.setNotes(notes);

        titleField.clear();
        authorField.clear();
        genreField.clear();
        notes = "";

        bookTable.getItems().add(book);
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
    private void saveNotes() {
        notes = notesArea.getText();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (initial) {
            initial = false;
            indexColumn.setCellValueFactory(new PropertyValueFactory<>("Index"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            authorColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
            genreColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
            notesColumn.setCellValueFactory(new PropertyValueFactory<>("NotesButton"));
        }

        if (notesArea != null) {
            notesArea.setText(notes);
        }
    }
}
