package com.example.bookjar.utils;

import com.example.bookjar.utils.readwrite.ReadWriteBook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadWriteFile {

    private static ArrayList<ReadWriteBook> bookArrayList = new ArrayList<>();

    public static int getBookArrayListSize() {
        return bookArrayList.size();
    }

    public static ReadWriteBook getBookArrayListBook(int index) {
        return bookArrayList.get(index);
    }

    public static void loadData() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir").concat("\\userbooks.dat"));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        bookArrayList = (ArrayList<ReadWriteBook>) in.readObject();
        in.close();
        fileIn.close();
    }
}
