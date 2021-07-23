package Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public static Stage stage;

    public void quitProgram() {
        Main.stage.close();
    }

    public void addBook() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newBook.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 450, 450));
        stage.setResizable(false);
        stage.showAndWait();
    }
}
