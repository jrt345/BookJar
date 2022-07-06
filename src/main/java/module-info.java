module com.example.bookJar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens main to javafx.fxml;
    exports main;
    opens main.controllers to javafx.fxml;
    exports main.controllers;
    opens main.library to javafx.fxml;
    exports main.library;
    opens main.utils to javafx.fxml;
    exports main.utils;
    opens main.utils.readwrite to javafx.base;
    exports main.utils.readwrite;
}