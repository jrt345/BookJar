package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.utils.Book;

public class BookEditorController {

    private static Stage stage;

    public void setStage(Stage stage) {
        BookEditorController.stage = stage;
    }

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField genreField;
    @FXML
    private TextArea notesArea;

    @FXML
    public Button saveBookButton;

    public String getTitleField() {
        return titleField.getText();
    }

    public String getAuthorField() {
        return authorField.getText();
    }

    public String getGenreField() {
        return genreField.getText();
    }

    public String getNotesArea() {
        return notesArea.getText();
    }

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setTextFields() {
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        genreField.setText(book.getGenre());
        notesArea.setText(book.getNotes());
    }

    @FXML
    void saveBook(ActionEvent event) {
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setGenre(genreField.getText());
        book.setNotes(notesArea.getText());
        stage.close();
    }

    public void pushSaveButton() {
        saveBookButton.fire();
    }

    @FXML
    void reset(ActionEvent event) {
        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
        notesArea.setText("");
    }

    @FXML
    void cancel(ActionEvent event) {
        stage.close();
    }

    @FXML
    void resetNotes(ActionEvent event) {
        notesArea.setText("");
    }
}
