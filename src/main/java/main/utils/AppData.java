package main.utils;

import main.library.Book;

import java.io.*;
import java.util.regex.Pattern;

public class AppData {

    public static final String LIBRARY_DIRECTORY = System.getProperty("user.dir").concat("\\library\\");

    private static String getDuplicateFolderName(String name) {
        int index = 0;
        String duplicateFolderName = name;
        File file = new File(LIBRARY_DIRECTORY.concat(name));

        while (file.exists()){
            index++;
            duplicateFolderName = name + " (" + index + ")";

            file = new File(LIBRARY_DIRECTORY.concat(duplicateFolderName));
        }

        return duplicateFolderName;
    }

    private static String filterFolderName(String name) {
        String filteredFolderName = name;

        filteredFolderName = filteredFolderName.replaceAll("[<>:\"/\\\\|?.*]", "_");

        filteredFolderName = filteredFolderName.trim();

        if (Pattern.matches("CON$|PRN$|AUX$|NUL$|COM1$|COM2$|COM3$|COM4$|COM5$|COM6$|COM7$|COM8$|COM9$|LPT1$|LPT2$|LPT3$|LPT4$|LPT5$|LPT6$|LPT7$|LPT8$|LPT9$", filteredFolderName)) {
            filteredFolderName = "_" + filteredFolderName + "_";
        }

        if (new File(LIBRARY_DIRECTORY.concat(filteredFolderName)).exists()){
            filteredFolderName = getDuplicateFolderName(filteredFolderName);
        }

        return filteredFolderName;
    }

    public static void createBookSave(String folderName, Book book) throws IOException {
        String filteredFolderName = filterFolderName(folderName);

        boolean isLibraryFolderCreated = new File(LIBRARY_DIRECTORY).exists();

        if (!isLibraryFolderCreated) {
            isLibraryFolderCreated = new File(AppData.LIBRARY_DIRECTORY).mkdirs();
        }

        boolean isBookFolderCreated = false;

        if (isLibraryFolderCreated) {
            if (new File(LIBRARY_DIRECTORY.concat(filteredFolderName)).exists()){
                filteredFolderName = getDuplicateFolderName(filteredFolderName);
            }

            isBookFolderCreated = new File(LIBRARY_DIRECTORY.concat(filteredFolderName)).mkdirs();

        }

        if (isBookFolderCreated) {
            AppData.serialize(filteredFolderName, book);
        }
    }

    public static void serialize(String folder, Book book) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(LIBRARY_DIRECTORY.concat(folder + "\\book.dat"));
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(book);
        out.close();
        fileOut.close();
    }

    public static Book deserialize(String folder) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(LIBRARY_DIRECTORY.concat(folder + "\\book.dat"));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Book book = (Book) in.readObject();
        in.close();
        fileIn.close();

        return book;
    }
}
