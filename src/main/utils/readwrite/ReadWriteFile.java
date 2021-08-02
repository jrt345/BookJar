package main.utils.readwrite;

import main.controllers.Controller;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteFile {

    public static void saveData() throws IOException {
        ArrayList<ReadWriteBook> bookArrayList = new ArrayList<>();

        for (int i = 0; i < Controller.getBookArrayListSize(); i++) {
            ReadWriteBook book = new ReadWriteBook();
            book.setIndex(Controller.getBookArrayListBook(i).getIndex());
            book.setTitle(Controller.getBookArrayListBook(i).getTitle());
            book.setAuthor(Controller.getBookArrayListBook(i).getAuthor());
            book.setGenre(Controller.getBookArrayListBook(i).getGenre());
            book.setNotes(Controller.getBookArrayListBook(i).getNotes());
            bookArrayList.add(book);
        }

        FileOutputStream fileOut = new FileOutputStream("userbooks.dat");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(bookArrayList);
        out.close();
        fileOut.close();
    }

    public static ArrayList<ReadWriteBook> bookArrayList = new ArrayList<>();

    public static void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("userbooks.dat");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        bookArrayList = (ArrayList<ReadWriteBook>) in.readObject();
        in.close();
        fileIn.close();
    }
}
