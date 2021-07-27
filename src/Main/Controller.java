package Main;

import ReadWrite.ReadWriteFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
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

    public static final ArrayList<Book> bookArrayList = new ArrayList<>();

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

    @FXML
    private void quitProgram(ActionEvent event) {
        Main.stage.close();
        try {
            ReadWriteFile.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addBook(ActionEvent event) {
        index++;
        bookArrayList.add(new Book());
        bookArrayList.get(index - 1).setIndex(index);
        bookArrayList.get(index - 1).setTitle(titleField.getText());
        bookArrayList.get(index - 1).setAuthor(authorField.getText());
        bookArrayList.get(index - 1).setGenre(genreField.getText());
        bookArrayList.get(index - 1).setNotes(notes);

        bookArrayList.get(index - 1).setNotesButton(new Button("View notes"));
        bookArrayList.get(index - 1).initializeNotesButton();

        titleField.clear();
        authorField.clear();
        genreField.clear();
        notes = "";

        bookTable.getItems().add(bookArrayList.get(index - 1));
    }

    @FXML
    private TextArea notesAreaView;

    @FXML
    private void cancelNotesView(ActionEvent event) {
        Book.closeStage();
    }

    @FXML
    private void resetNotesView(ActionEvent event) {
        notesAreaView.setText("");
    }

    @FXML
    private void saveNotesView(ActionEvent event) {
        bookArrayList.get(Book.getNoteIndex() - 1).setNotes(notesAreaView.getText());
        Book.closeStage();
    }

    public ObservableList<Book> getBook() {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        index = ReadWriteFile.bookArrayList.size();

        for (int i = 0; i < ReadWriteFile.bookArrayList.size(); i++) {
            Book book = new Book();
            book.setIndex(ReadWriteFile.bookArrayList.get(i).getIndex());
            book.setTitle(ReadWriteFile.bookArrayList.get(i).getTitle());
            book.setAuthor(ReadWriteFile.bookArrayList.get(i).getAuthor());
            book.setGenre(ReadWriteFile.bookArrayList.get(i).getGenre());
            book.setNotes(ReadWriteFile.bookArrayList.get(i).getNotes());

            book.setNotesButton(new Button("View notes"));
            book.initializeNotesButton();

            bookArrayList.add(book);
            bookList.add(book);
        }

        return bookList;
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
            notesColumn.setStyle("-fx-alignment: CENTER;");

            bookTable.setItems(getBook());
        }

        if (notesArea != null) {
            notesArea.setText(notes);
        }

        if (Book.getNoteIndex() > 0) {
            notesAreaView.setText(bookArrayList.get(Book.getNoteIndex() - 1).getNotes());
        }
    }
}
