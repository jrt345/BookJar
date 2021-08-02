package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.utils.readwrite.ReadWriteFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static Stage stage;

    public static void closeStage() {
        stage.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            ReadWriteFile.loadData();
        } catch (IOException e) {
            FileOutputStream fileOut = new FileOutputStream("userbooks.dat");
            fileOut.close();
        }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("resources/fxml/bookJar.fxml")));

        stage = new Stage();
        stage.setTitle("BookJar");
        stage.setScene(new Scene(root, 900, 600));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/images/bookJarLogo-200x.png"))));
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
