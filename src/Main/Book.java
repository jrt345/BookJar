package Main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {

    private SimpleIntegerProperty index;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty genre;
    private SimpleStringProperty notes;

    public Book() {
        this(0, "", "", "", "");
    }

    public Book(int index, String title, String author, String genre, String notes) {
        this.index = new SimpleIntegerProperty(index);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.notes = new SimpleStringProperty(notes);
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
        this.notes = new SimpleStringProperty(notes);
    }
}
