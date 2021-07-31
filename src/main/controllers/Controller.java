package main.controllers;

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
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import main.utils.Book;
import main.utils.readwrite.ReadWriteFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static Stage notesStage;

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

    public static final ArrayList<Book> bookArrayList = new ArrayList<>();

    @FXML
    private void openNotes(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/notes.fxml"));
        Parent root = fxmlLoader.load();

        NotesController notesController = fxmlLoader.getController();
        notesController.setNotes(notes);
        notesController.loadNotes();

        notesStage = new Stage();
        notesStage.initModality(Modality.APPLICATION_MODAL);
        notesStage.setTitle("Notes");
        notesStage.setScene(new Scene(root, 450, 375));
        notesStage.setResizable(false);
        notesStage.showAndWait();

        notes = notesController.getNotes();
    }

    @FXML
    private MenuItem editContext;
    @FXML
    private MenuItem deleteContext;
    @FXML
    private MenuItem viewContext;

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField genreField;
    @FXML
    private Button notesButton;
    @FXML
    private Button addButton;

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

        try {
            ReadWriteFile.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (editContext.isDisable()) {
            editContext.setDisable(false);
            deleteContext.setDisable(false);
            viewContext.setDisable(false);
        }
    }

    @FXML
    private void deleteBook(ActionEvent event) {
        int index;
        int selectedIndex = bookTable.getSelectionModel().getFocusedIndex();

        if (addButton.isDisabled()) {
            System.out.println("PRO");
            index = bookTable.getItems().get(selectedIndex).getIndex() - 1;
        } else {
            index = selectedIndex;
        }

        Controller.index--;
        bookArrayList.remove(index);
        bookTable.getItems().remove(selectedIndex);

        for (int i = index; i < bookArrayList.size(); i++) {
            bookArrayList.get(i).setIndex(bookArrayList.get(i).getIndex() - 1);
        }

        try {
            ReadWriteFile.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bookArrayList.size() == 0) {
            editContext.setDisable(true);
            deleteContext.setDisable(true);
            viewContext.setDisable(true);
        }
    }

    public static Stage editBookStage;

    @FXML
    private void editBook(ActionEvent event) throws IOException {
        int index;
        int selectedIndex = bookTable.getSelectionModel().getFocusedIndex();

        if (addButton.isDisabled()) {
            index = bookTable.getItems().get(selectedIndex).getIndex() - 1;
        } else {
            index = selectedIndex;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/bookEditor.fxml"));
        Parent root = fxmlLoader.load();

        BookEditorController bookEditorController = fxmlLoader.getController();
        bookEditorController.setBook(bookArrayList.get(index));
        bookEditorController.setTextFields();

        editBookStage = new Stage();
        editBookStage.initModality(Modality.APPLICATION_MODAL);
        editBookStage.setScene(new Scene(root, 450, 450));
        editBookStage.setResizable(false);
        editBookStage.showAndWait();

        bookArrayList.set(index, bookEditorController.getBook());
        bookTable.getItems().set(selectedIndex, bookEditorController.getBook());


        try {
            ReadWriteFile.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void viewNotes(ActionEvent actionEvent) {
        int selectedIndex = bookTable.getSelectionModel().getFocusedIndex();
        int index;

        if (addButton.isDisabled()) {
            index = bookTable.getItems().get(selectedIndex).getIndex() - 1;
        } else {
            index = selectedIndex;
        }

        bookArrayList.get(index).getNotesButton().fire();
    }

    @FXML
    private RadioButton titleRadioButton;
    @FXML
    private RadioButton authorRadioButton;
    @FXML
    private RadioButton genreRadioButton;


    @FXML
    private TextField searchField;

    private static ObservableList<Book> searchTable(String search, boolean isTitle) {
        ObservableList<Book> bookList = FXCollections.observableArrayList();

        if (!isTitle) {
            for (int i = 0; i < bookArrayList.size(); i++) {
                String identifier = bookArrayList.get(i).getAuthor();

                if (identifier.toLowerCase().contains(search.toLowerCase())) {
                    bookList.add(bookArrayList.get(i));
                }
            }
        } else {
            for (int i = 0; i < bookArrayList.size(); i++) {
                String identifier = bookArrayList.get(i).getTitle();

                if (identifier.toLowerCase().contains(search.toLowerCase())) {
                    bookList.add(bookArrayList.get(i));
                }
            }
        }

        return bookList;
    }

    public ObservableList<Book> getBook() {
        try {
            ReadWriteFile.loadData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList<Book> bookList = FXCollections.observableArrayList();
        index = ReadWriteFile.bookArrayList.size();
        bookArrayList.clear();

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

    @FXML
    private void searchBooksTable(KeyEvent keyEvent) {
        titleField.setDisable(true);
        authorField.setDisable(true);
        genreField.setDisable(true);
        notesButton.setDisable(true);
        addButton.setDisable(true);

        boolean isTitle;

        if (authorRadioButton.isSelected()) {
            isTitle = false;
        } else if (titleRadioButton.isSelected()) {
            isTitle = true;
        } else {
            isTitle = true;
        }

        if (!searchField.getText().equals("")) {
            bookTable.setItems(null);
            bookTable.setItems(searchTable(searchField.getText(), isTitle));
        } else {
            titleField.setDisable(false);
            authorField.setDisable(false);
            genreField.setDisable(false);
            notesButton.setDisable(false);
            addButton.setDisable(false);

            bookTable.setItems(getBook());
        }

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

            if (bookArrayList.size() == 0) {
                editContext.setDisable(true);
                deleteContext.setDisable(true);
                viewContext.setDisable(true);
            }
        }
    }
}