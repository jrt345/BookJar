package Main;

import ReadWrite.ReadWriteFile;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Book {

    private SimpleIntegerProperty index;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty genre;
    private SimpleStringProperty notes;
    private Button notesButton;

    public Book() {
        this(0, "", "", "", "", new Button());
    }

    public Book(int index, String title, String author, String genre, String notes, Button notesButton) {
        this.index = new SimpleIntegerProperty(index);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.notes = new SimpleStringProperty(notes);
        this.notesButton = notesButton;
    }

    public int getIndex() {
        return index.get();
    }
    public void setIndex(int index) {
        this.index = new SimpleIntegerProperty(index);
    }

    public String getTitle() {
        return title.get();
    }
    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }

    public String getAuthor() {
        return author.get();
    }
    public void setAuthor(String author) {
        this.author = new SimpleStringProperty(author);
    }

    public String getGenre() {
        return genre.get();
    }
    public void setGenre(String genre) {
        this.genre = new SimpleStringProperty(genre);
    }

    public String getNotes() {
        return notes.get();
    }

    public Button getNotesButton() {
        return notesButton;
    }

    public void setNotesButton(Button notesButton) {
        this.notesButton = notesButton;
    }

    private static Stage stage;

    public static void closeStage() {
        stage.close();
    }

    private void openNoteViewer() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Book.class.getResource("notesViewer.fxml"));
        Parent root = fxmlLoader.load();

        NotesViewerController notesViewerController = fxmlLoader.getController();
        notesViewerController.setNotes(getNotes());
        notesViewerController.loadNotes();

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Notes");
        stage.setScene(new Scene(root, 450, 375));
        stage.setResizable(false);
        stage.showAndWait();

        setNotes(notesViewerController.getNotes());

        try {
            ReadWriteFile.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNotes(String notes) {
        this.notes = new SimpleStringProperty(notes);
    }

    public void initializeNotesButton() {
        notesButton.setOnAction(event -> {
            try {
                openNoteViewer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
