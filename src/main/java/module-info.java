module com.example.bookJar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookjar to javafx.fxml;
    exports com.example.bookjar;
    opens com.example.bookjar.controllers to javafx.fxml;
    exports com.example.bookjar.controllers;
    opens com.example.bookjar.utils to javafx.fxml;
    exports com.example.bookjar.utils;
    opens com.example.bookjar.utils.readwrite to javafx.fxml;
    exports com.example.bookjar.utils.readwrite;
}