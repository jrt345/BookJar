package com.example.bookjar.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Book implements Serializable {

    private String title;
    private String author;
    private String description;
    private String genre;
    private ArrayList<Tag> tags;
    private transient ArrayList<Chapter> chapters;

    private Book() {
        this.title = "";
        this.author = "";
        this.description = "";
        this.genre = "";
        this.tags = new ArrayList<>();
        this.chapters = new ArrayList<>();
    }

    private Book(String title) {
        this.title = title;
        this.author = "";
        this.description = "";
        this.genre = "";
        this.tags = new ArrayList<>();
        this.chapters = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!Objects.equals(title, book.title)) return false;
        if (!Objects.equals(author, book.author)) return false;
        if (!Objects.equals(description, book.description)) return false;
        if (!Objects.equals(genre, book.genre)) return false;
        if (!Objects.equals(tags, book.tags)) return false;
        return Objects.equals(chapters, book.chapters);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (chapters != null ? chapters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", tags=" + tags +
                ", chapters=" + chapters +
                '}';
    }
}
