package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.utils.Book;

public class BookEditorController {

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
    void cancel(ActionEvent event) {
        Controller.editBookStage.close();
    }

    @FXML
    void reset(ActionEvent event) {
        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
        notesArea.setText("");
    }

    @FXML
    void resetNotes(ActionEvent event) {
        notesArea.setText("");
    }

    @FXML
    void saveBook(ActionEvent event) {
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setGenre(genreField.getText());
        book.setNotes(notesArea.getText());
        Controller.editBookStage.close();
    }

    public String getNotesArea() {
        return notesArea.getText();
    }

    public void pushSaveButton() {
        saveBookButton.fire();
    }
}
