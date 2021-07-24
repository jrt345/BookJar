package Main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public Button getNotesButton() {
        return notesButton;
    }

    public void setNotesButton(Button notesButton) {
        this.notesButton = notesButton;
    }

    static int noteIndex;

    public static int getNoteIndex() {
        return noteIndex;
    }

    public static void setNoteIndex(int index) {
        noteIndex = index;
    }

    private void openNoteViewer() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("noteViewer.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Notes");
        stage.setScene(new Scene(root, 450, 375));
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void initializeNotesButton() {
        notesButton.setOnAction(event -> {
            System.out.println("AS1");
            System.out.println(index);
            noteIndex = getIndex();
            try {
                openNoteViewer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
