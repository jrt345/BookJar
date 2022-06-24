package com.example.bookjar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoadBookJar extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadBookJar.class.getResource("bookJar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("BookJar");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/bookJarLogo-200x.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
