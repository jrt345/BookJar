module com.example.bookJar {
    requires javafx.controls;
    requires javafx.fxml;


    opens main to javafx.fxml;
    exports main;
    opens main.controllers to javafx.fxml;
    exports main.controllers;
    opens main.utils to javafx.fxml;
    exports main.utils;
    opens main.utils.readwrite to javafx.fxml;
    exports main.utils.readwrite;
}