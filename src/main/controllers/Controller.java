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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.Main;
import main.utils.Book;
import main.utils.readwrite.ReadWriteFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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

    private void notesAlertBox(NotesController controller, WindowEvent windowEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to save your notes before exiting??");
        alert.setHeaderText("Exit notes editor");

        ButtonType saveButton = new ButtonType("Save");
        ButtonType doNotSaveButton = new ButtonType("Don't Save");

        alert.getButtonTypes().set(0, saveButton);
        alert.getButtonTypes().set(1, doNotSaveButton);
        alert.getButtonTypes().add(2, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get().equals(saveButton)) {
            controller.pushSaveButton();
            notes = controller.getNotes();
        } else if (result.isPresent() && result.get().equals(doNotSaveButton)) {
            notesStage.close();
        } else {
            windowEvent.consume();
        }
    }

    @FXML
    private void openNotes(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/notes.fxml"));
        Parent root = fxmlLoader.load();

        NotesController notesController = fxmlLoader.getController();
        notesController.setNotes(notes);
        notesController.loadNotes();

        notesStage = new Stage();
        notesStage.setTitle("Notes");
        notesStage.initModality(Modality.APPLICATION_MODAL);
        notesStage.setScene(new Scene(root, 450, 375));
        notesStage.setResizable(false);

        notesStage.setOnCloseRequest(e -> {
            if (!notes.equals(notesController.getNotesAreaString())) {
                notesAlertBox(notesController, e);
            }
        });

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
    private void quitProgram(ActionEvent event) throws IOException {
        Main.stage.close();
        ReadWriteFile.saveData();
    }

    @FXML
    private void addBook(ActionEvent event) throws IOException {
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

        ReadWriteFile.saveData();

        if (editContext.isDisable()) {
            editContext.setDisable(false);
            deleteContext.setDisable(false);
            viewContext.setDisable(false);
        }
    }

    @FXML
    private void deleteBook(ActionEvent event) throws IOException {
        int selectedIndex = bookTable.getSelectionModel().getFocusedIndex();

        index--;
        bookArrayList.remove(selectedIndex);
        bookTable.getItems().remove(selectedIndex);

        for (int i = selectedIndex; i < bookArrayList.size(); i++) {
            bookArrayList.get(i).setIndex(bookArrayList.get(i).getIndex() - 1);
        }

        ReadWriteFile.saveData();

        if (bookArrayList.size() == 0) {
            editContext.setDisable(true);
            deleteContext.setDisable(true);
            viewContext.setDisable(true);
        }
    }

    public static Stage editBookStage;

    private void editBooksAlertBox(BookEditorController notesController, WindowEvent windowEvent) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Would you like to save your book information before exiting??");
        alert.setHeaderText("Exit book editor");

        ButtonType saveButton = new ButtonType("Save");
        ButtonType doNotSaveButton = new ButtonType("Don't Save");

        alert.getButtonTypes().set(0, saveButton);
        alert.getButtonTypes().set(1, doNotSaveButton);
        alert.getButtonTypes().add(2, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get().equals(saveButton)) {
            notesController.pushSaveButton();
        } else if (result.isPresent() && result.get().equals(doNotSaveButton)) {
            editBookStage.close();
        } else {
            windowEvent.consume();
        }
    }

    private boolean hasChanged(BookEditorController controller) {
        boolean hasChanged = false;

        if (!controller.getBook().getTitle().equals(controller.getTitleField())) {
            hasChanged = true;
        } else if (!controller.getBook().getAuthor().equals(controller.getAuthorField())) {
            hasChanged = true;
        } else if (!controller.getBook().getGenre().equals(controller.getGenreField())) {
            hasChanged = true;
        } else if (!controller.getBook().getNotes().equals(controller.getNotesArea())) {
            hasChanged = true;
        }

        return hasChanged;
    }

    @FXML
    private void editBook(ActionEvent event) throws IOException {
        int selectedIndex = bookTable.getSelectionModel().getFocusedIndex();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/bookEditor.fxml"));
        Parent root = fxmlLoader.load();

        BookEditorController bookEditorController = fxmlLoader.getController();
        bookEditorController.setBook(bookArrayList.get(selectedIndex));
        bookEditorController.setTextFields();

        editBookStage = new Stage();
        editBookStage.setTitle("Book Editor");
        editBookStage.initModality(Modality.APPLICATION_MODAL);
        editBookStage.setScene(new Scene(root, 450, 450));
        editBookStage.setResizable(false);

        editBookStage.setOnCloseRequest(e -> {
            if (hasChanged(bookEditorController)) {
                editBooksAlertBox(bookEditorController, e);
            }
        });

        editBookStage.showAndWait();

        bookArrayList.set(selectedIndex, bookEditorController.getBook());
        bookTable.getItems().set(selectedIndex, bookEditorController.getBook());

        ReadWriteFile.saveData();
    }

    @FXML
    public void viewNotes(ActionEvent actionEvent) {
        int selectedIndex = bookTable.getSelectionModel().getFocusedIndex();

        bookArrayList.get(selectedIndex).getNotesButton().fire();
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

            if (bookArrayList.size() == 0) {
                editContext.setDisable(true);
                deleteContext.setDisable(true);
                viewContext.setDisable(true);
            }
        }
    }
}