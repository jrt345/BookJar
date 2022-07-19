package main.utils;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UpdateManager {

    public static final String CURRENT_VERSION = "1.1.0";

    public static String getLatestVersion() {
        try {
            URL latestVersion = new URL("https://raw.githubusercontent.com/jrt345/BookJar/master/README.md");
            URLConnection gitHub = latestVersion.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(gitHub.getInputStream()));

            String stringCurrentVersion = in.readLine();
            in.close();

            return stringCurrentVersion.replaceAll("<", "")
                    .replaceAll("!", "")
                    .replaceAll("-", "")
                    .replaceAll("Version", "")
                    .replaceAll(">", "")
                    .replaceAll("\\s", "");
        } catch (IOException e) {
            return "0.0.0";
        }
    }


    public static boolean isUpdateAvailable() {
        try {
            String stringLatestVersion = getLatestVersion();

            String[] currentVersionsStringArray = CURRENT_VERSION.split("\\.");
            String[] latestVersionsStringArray = stringLatestVersion.split("\\.");

            int[] currentVersionsIntArray = new int[3];
            int[] latestVersionsIntArray = new int[3];

            for (int i = 0;i < 3;i++){
                currentVersionsIntArray[i] = Integer.parseInt(currentVersionsStringArray[i]);
                latestVersionsIntArray[i] = Integer.parseInt(latestVersionsStringArray[i]);
            }

            if (currentVersionsIntArray[0] < latestVersionsIntArray[0]){
                return true;
            } else if (currentVersionsIntArray[1] < latestVersionsIntArray[1]) {
                return true;
            } else {
                return currentVersionsIntArray[2] < latestVersionsIntArray[2];
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void showUpdateDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Update available!");

        VBox primaryVbox = new VBox();
        VBox vboxPt1 = new VBox();
        VBox vboxPt2 = new VBox();

        Label introLabel = new Label("A new version of BookJar has been released! Version: " + getLatestVersion());

        Label label = new Label(" is available at ");
        Hyperlink repoLink = new Hyperlink("github.com/jrt345/BookJar.");

        FlowPane flowPane = new FlowPane(label, repoLink);
        flowPane.setAlignment(Pos.CENTER);

        vboxPt1.getChildren().addAll(introLabel, flowPane);
        vboxPt1.setAlignment(Pos.CENTER);

        Label downloadLabel = new Label("You can download version: " + getLatestVersion() + " here: ");
        Hyperlink downloadLink = new Hyperlink("https://github.com/jrt345/BookJar/releases/latest");

        vboxPt2.getChildren().addAll(downloadLabel, downloadLink);
        vboxPt2.setAlignment(Pos.CENTER);

        primaryVbox.getChildren().addAll(vboxPt1, new Label(), vboxPt2);
        primaryVbox.setAlignment(Pos.CENTER);

        repoLink.setOnAction( (evt) -> {
            alert.close();
            Runtime rt = Runtime.getRuntime();
            String url = "https://github.com/jrt345/BookJar";
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );

        downloadLink.setOnAction( (evt) -> {
            alert.close();
            Runtime rt = Runtime.getRuntime();
            String url = "https://github.com/jrt345/BookJar/releases/latest";
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );

        alert.getDialogPane().contentProperty().set(primaryVbox);
        alert.showAndWait();
    }
}

