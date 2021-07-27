package ReadWrite;

import Main.Controller;

import java.io.*;
import java.util.ArrayList;

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
    }

    public static ArrayList<ReadWriteBook> bookArrayList = new ArrayList<>();
    ;

    public static void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("userbooks.dat");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        bookArrayList = (ArrayList<ReadWriteBook>) in.readObject();
        in.close();
        fileIn.close();
    }
}
