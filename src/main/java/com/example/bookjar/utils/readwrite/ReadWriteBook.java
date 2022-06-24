package com.example.bookjar.utils.readwrite;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class ReadWriteBook implements Serializable {

    @Serial
    private static final long serialVersionUID = 2406571069511999211L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadWriteBook that = (ReadWriteBook) o;

        if (index != that.index) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(author, that.author)) return false;
        if (!Objects.equals(genre, that.genre)) return false;
        return Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReadWriteBook{" +
                "index=" + index +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
