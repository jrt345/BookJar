package main.utils;

import main.library.Book;
import main.utils.readwrite.ReadWriteBook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadWriteFile {

    public static void convertOldBooks() throws IOException, ClassNotFoundException {
        ArrayList<ReadWriteBook> oldBooks = loadData();
        ArrayList<Book> newBooks = new ArrayList<>();

        for (ReadWriteBook oldBook : oldBooks) {
            Book book = new Book();
            book.setTitle(oldBook.getTitle());
            book.setAuthor(oldBook.getAuthor());
            book.setGenre(oldBook.getGenre());
            newBooks.add(book);
        }

        for (Book newBook : newBooks) {
            AppData.createBookSave(newBook.getTitle(), newBook);
        }
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<ReadWriteBook> loadData() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir").concat("\\userbooks.dat"));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        ArrayList<ReadWriteBook> bookArrayList = (ArrayList<ReadWriteBook>) in.readObject();
        in.close();
        fileIn.close();

        return bookArrayList;
    }
}
