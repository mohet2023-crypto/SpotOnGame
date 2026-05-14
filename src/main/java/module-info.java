module com.example.spotongame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.spotongame to javafx.fxml;
    exports com.example.spotongame;
}