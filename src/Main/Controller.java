package Main;

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
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static Stage stage;

    private static int index = 0;
    private static Book book;
    private static boolean initial = true;
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
    private TableColumn<Book, String> notesColumn;
    @FXML
    private TextArea notesField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField genreField;

    @FXML
    public void quitProgram(ActionEvent event) {
        Main.stage.close();
    }

    @FXML
    public void addBook(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newBook.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 450, 450));
        stage.setResizable(false);
        stage.showAndWait();

        bookTable.getItems().add(book);
    }

    @FXML
    public void addBookToTable(ActionEvent event) {
        index++;
        book = new Book();
        book.setIndex(index);
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setGenre(genreField.getText());
        book.setNotes(notesField.getText());

        stage.close();
    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {
        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
        notesField.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (initial) {
            initial = false;
            indexColumn.setCellValueFactory(new PropertyValueFactory<>("Index"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            authorColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
            genreColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
            notesColumn.setCellValueFactory(new PropertyValueFactory<>("Notes"));
        }
    }
}
