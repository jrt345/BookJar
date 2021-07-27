package ReadWrite;

import java.io.Serializable;

public class ReadWriteBook implements Serializable {
    private int index;
    private String title;
    private String author;
    private String genre;
    private String notes;

    public ReadWriteBook() {
        this(0, "", "", "", "");
    }

    public ReadWriteBook(int index, String title, String author, String genre, String notes) {
        this.index = index;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.notes = notes;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
