package com.example.bookjar.controllers;

import com.example.bookjar.LoadBookJar;
import com.example.bookjar.utils.Book;
import com.example.bookjar.utils.readwrite.ReadWriteFile;
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
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static int index = 0;
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

    private static final ArrayList<Book> bookArrayList = new ArrayList<>();

    public static int getBookArrayListSize() {
        return bookArrayList.size();
    }

    public static Book getBookArrayListBook(int index) {
        return bookArrayList.get(index);
    }

    @FXML
    private void quitProgram(ActionEvent event) throws IOException {
        LoadBookJar.closeStage();
        ReadWriteFile.saveData();
    }

    @FXML
    private void about(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadBookJar.class.getResource("/com.example.bookjar/aboutBox.fxml"));
        Parent root = fxmlLoader.load();

        AboutBoxController aboutBoxController = fxmlLoader.getController();

        Stage aboutBoxStage = new Stage();

        aboutBoxStage.setTitle("About BookJar");
        aboutBoxStage.initModality(Modality.APPLICATION_MODAL);
        aboutBoxStage.getIcons().add(new Image(Objects.requireNonNull(LoadBookJar.class.getResourceAsStream("/com.example.bookjar/images/bookJarLogo-200x.png"))));
        aboutBoxStage.setScene(new Scene(root, 500, 400));
        aboutBoxStage.setResizable(false);

        aboutBoxController.setStage(aboutBoxStage);

        aboutBoxStage.showAndWait();
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
    private TextField searchField;

    private void notesAlertBox(NotesController controller, Stage stage, WindowEvent windowEvent) {
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
            stage.close();
        } else {
            windowEvent.consume();
        }
    }

    @FXML
    private void openNotes(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadBookJar.class.getResource("/com.example.bookjar/notes.fxml"));
        Parent root = fxmlLoader.load();

        NotesController notesController = fxmlLoader.getController();
        notesController.setNotes(notes);
        notesController.loadNotes();

        Stage notesStage = new Stage();

        notesController.setStage(notesStage);

        notesStage.setTitle("Notes");
        notesStage.initModality(Modality.APPLICATION_MODAL);
        notesStage.getIcons().add(new Image(Objects.requireNonNull(LoadBookJar.class.getResourceAsStream("/com.example.bookjar/images/bookJarLogo-200x.png"))));
        notesStage.setScene(new Scene(root, 450, 375));
        notesStage.setResizable(false);

        notesStage.setOnCloseRequest(e -> {
            if (!notes.equals(notesController.getNotesAreaString())) {
                notesAlertBox(notesController, notesStage, e);
            }
        });

        notesStage.showAndWait();

        notes = notesController.getNotes();
    }

    private void disableContextMenu(boolean disable) {
        editContext.setDisable(disable);
        deleteContext.setDisable(disable);
        viewContext.setDisable(disable);
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
    }

    @FXML
    private void setContextMenu(ContextMenuEvent event){
        disableContextMenu(bookArrayList.size() == 0);

        disableContextMenu(bookTable.getSelectionModel().getSelectedIndices().size() == 0);

        if (bookTable.getSelectionModel().getSelectedIndices().size() > 1){
            viewContext.setDisable(true);
            editContext.setDisable(true);
        }
    }

    @FXML
    private void viewNotes(ActionEvent actionEvent) {
        int selectedIndex = bookTable.getSelectionModel().getFocusedIndex();
        int index;

        if (addButton.isDisabled()) {
            index = bookTable.getItems().get(selectedIndex).getIndex() - 1;
        } else {
            index = selectedIndex;
        }

        bookArrayList.get(index).getNotesButton().fire();
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

    private void editBooksAlertBox(BookEditorController notesController, Stage stage, WindowEvent windowEvent) {
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
            stage.close();
        } else {
            windowEvent.consume();
        }
    }

    @FXML
    private void editBook(ActionEvent event) throws IOException {
        int index;
        int selectedIndex = bookTable.getSelectionModel().getFocusedIndex();

        if (addButton.isDisabled()) {
            index = bookTable.getItems().get(selectedIndex).getIndex() - 1;
        } else {
            index = selectedIndex;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(LoadBookJar.class.getResource("/com.example.bookjar/bookEditor.fxml"));
        Parent root = fxmlLoader.load();

        BookEditorController bookEditorController = fxmlLoader.getController();
        bookEditorController.setBook(bookArrayList.get(index));
        bookEditorController.setTextFields();

        Stage editBookStage = new Stage();

        bookEditorController.setStage(editBookStage);

        editBookStage.setTitle("Book Editor");
        editBookStage.initModality(Modality.APPLICATION_MODAL);
        editBookStage.getIcons().add(new Image(Objects.requireNonNull(LoadBookJar.class.getResourceAsStream("/com.example.bookjar/images/bookJarLogo-200x.png"))));
        editBookStage.setScene(new Scene(root, 450, 450));
        editBookStage.setResizable(false);

        editBookStage.setOnCloseRequest(e -> {
            if (hasChanged(bookEditorController)) {
                editBooksAlertBox(bookEditorController, editBookStage, e);
            }
        });

        editBookStage.showAndWait();

        bookArrayList.set(index, bookEditorController.getBook());
        bookTable.getItems().set(selectedIndex, bookEditorController.getBook());

        ReadWriteFile.saveData();
    }

    @FXML
    private void deleteBook(ActionEvent event) throws IOException {
        int selectionSize = bookTable.getSelectionModel().getSelectedIndices().size();

        Alert alert;

        if (selectionSize > 1){
            alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete " +
                    selectionSize +
                    " books?? This process can not be undone. ");
        } else {
            alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete \"" +
                    bookTable.getItems().get(bookTable.getSelectionModel()
                            .getSelectedIndex()).getTitle() +
                    "\" ?? This process can not be undone. ");
        }

        alert.setHeaderText("Confirm delete");

        ButtonType deleteButton = new ButtonType("Delete");

        alert.getButtonTypes().set(1, deleteButton);
        alert.getButtonTypes().set(0, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get().equals(deleteButton)) {
            int[] indices = new int[selectionSize];
            int[] tableIndices = new int[selectionSize];

            index-=selectionSize;
            if (addButton.isDisabled()){
                for (int i = 0;i < selectionSize;i++){
                    tableIndices[i] = bookTable.getSelectionModel().getSelectedIndices().get(i);
                    indices[i] = bookTable.getItems().get(tableIndices[i]).getIndex() - 1;
                }
            } else {
                for (int i = 0;i < selectionSize;i++){
                    tableIndices[i] = bookTable.getSelectionModel().getSelectedIndices().get(i);
                    indices[i] = tableIndices[i];
                }
            }


            for (int i = 0;i < indices.length;i++){
                bookArrayList.remove(indices[i]-i);
                bookTable.getItems().remove(tableIndices[i]-i);
            }

            for (int i = 0;i < bookArrayList.size();i++){
                bookArrayList.get(i).setIndex(i+1);
            }

            ReadWriteFile.saveData();
        }
    }

    @FXML
    private RadioButton titleRadioButton;
    @FXML
    private RadioButton authorRadioButton;
    @FXML
    private RadioButton genreRadioButton;

    private static final int TITLE = 0;
    private static final int AUTHOR = 1;
    private static final int GENRE = 2;

    private void swapBookTableSearch(int searchBy) {
        bookTable.setItems(null);
        bookTable.setItems(searchTable(searchField.getText(), searchBy));

        disableContextMenu(bookTable.getItems().size() == 0);
    }

    @FXML
    private void searchByTitle(ActionEvent event) {
        if (!searchField.getText().equals("")) {
            swapBookTableSearch(TITLE);
        }
    }

    @FXML
    private void searchByAuthor(ActionEvent event) {
        if (!searchField.getText().equals("")) {
            swapBookTableSearch(AUTHOR);
        }
    }

    @FXML
    private void searchByGenre(ActionEvent event) {
        if (!searchField.getText().equals("")) {
            swapBookTableSearch(GENRE);
        }
    }

    private static ObservableList<Book> searchTable(String search, int searchBy) {
        ObservableList<Book> bookList = FXCollections.observableArrayList();

        if (searchBy == GENRE) {
            for (int i = 0; i < bookArrayList.size(); i++) {
                String identifier = bookArrayList.get(i).getGenre();

                if (identifier.toLowerCase().contains(search.toLowerCase())) {
                    bookList.add(bookArrayList.get(i));
                }
            }
        } else if (searchBy == AUTHOR) {
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

    private ObservableList<Book> getBook(boolean isInitial) {
        if (!isInitial){
            try {
                ReadWriteFile.loadData();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        ObservableList<Book> bookList = FXCollections.observableArrayList();
        index = ReadWriteFile.getBookArrayListSize();
        bookArrayList.clear();

        for (int i = 0; i < ReadWriteFile.getBookArrayListSize(); i++) {
            Book book = new Book();
            book.setIndex(ReadWriteFile.getBookArrayListBook(i).getIndex());
            book.setTitle(ReadWriteFile.getBookArrayListBook(i).getTitle());
            book.setAuthor(ReadWriteFile.getBookArrayListBook(i).getAuthor());
            book.setGenre(ReadWriteFile.getBookArrayListBook(i).getGenre());
            book.setNotes(ReadWriteFile.getBookArrayListBook(i).getNotes());

            book.setNotesButton(new Button("View notes"));
            book.initializeNotesButton();

            bookArrayList.add(book);
            bookList.add(book);
        }

        return bookList;
    }

    private void disableBookAdding(boolean disable) {
        titleField.setDisable(disable);
        authorField.setDisable(disable);
        genreField.setDisable(disable);
        notesButton.setDisable(disable);
        addButton.setDisable(disable);
    }

    @FXML
    private void searchBooksTable(KeyEvent keyEvent) {
        disableBookAdding(true);

        int searchBy;

        if (genreRadioButton.isSelected()) {
            searchBy = GENRE;
        } else if (authorRadioButton.isSelected()) {
            searchBy = AUTHOR;
        } else if (titleRadioButton.isSelected()) {
            searchBy = TITLE;
        } else {
            searchBy = TITLE;
        }

        if (!searchField.getText().equals("")) {
            swapBookTableSearch(searchBy);
        } else {
            disableBookAdding(false);
            disableContextMenu(false);

            bookTable.setItems(getBook(false));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        indexColumn.setCellValueFactory(new PropertyValueFactory<>("Index"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("Author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("NotesButton"));
        notesColumn.setStyle("-fx-alignment: CENTER;");

        bookTable.setItems(getBook(true));
        bookTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}