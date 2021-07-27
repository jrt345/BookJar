package ReadWrite;

import Main.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class ReadWriteBook implements Serializable {
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

public class ReadWriteFile {

    public static void saveData() throws IOException {
        ArrayList<ReadWriteBook> bookArrayList = new ArrayList<>();

        for (int i = 0; i < Controller.bookArrayList.size(); i++) {
            ReadWriteBook book = new ReadWriteBook();
            book.setIndex(Controller.bookArrayList.get(i).getIndex());
            book.setTitle(Controller.bookArrayList.get(i).getTitle());
            book.setAuthor(Controller.bookArrayList.get(i).getAuthor());
            book.setGenre(Controller.bookArrayList.get(i).getGenre());
            book.setNotes(Controller.bookArrayList.get(i).getNotes());
            bookArrayList.add(book);
        }

        FileOutputStream fileOut = new FileOutputStream("userbooks.dat");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(bookArrayList);
        out.close();
        fileOut.close();
        System.out.println("SUCCESS");
    }

    public static void loadData() {

    }
}
