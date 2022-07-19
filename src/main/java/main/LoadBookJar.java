package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.utils.UpdateManager;
import main.utils.readwrite.ReadWriteFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class LoadBookJar extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static Stage stage;

    public static void closeStage() {
        stage.close();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            ReadWriteFile.loadData();
        } catch (IOException e) {
            FileOutputStream fileOut = new FileOutputStream("userbooks.dat");
            fileOut.close();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(LoadBookJar.class.getResource("/main/bookJar.fxml"));

        stage.setTitle("BookJar");
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/main/images/bookJarLogo-200x.png"))));
        stage.show();
        LoadBookJar.stage = stage;

        if (UpdateManager.isUpdateAvailable()) {
            UpdateManager.showUpdateDialog();
        }

        stage.setOnCloseRequest(e -> {
            try {
                ReadWriteFile.saveData();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}
