package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.readwrite.ReadWriteFile;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            ReadWriteFile.loadData();
        } catch (IOException e) {
            FileOutputStream fileOut = new FileOutputStream("userbooks.dat");
            fileOut.close();
        }
        Parent root = FXMLLoader.load(getClass().getResource("bookJar.fxml"));
        stage = new Stage();
        stage.setTitle("BookJar");
        stage.setScene(new Scene(root, 900, 600));
        stage.show();

        stage.setOnCloseRequest(e -> {
            try {
                ReadWriteFile.saveData();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}
