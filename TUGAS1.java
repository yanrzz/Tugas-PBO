package com.example.tugasp4;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TUGAS1 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Teks awal");
        Button button = new Button("Tekan saya");

        button.setOnAction(e -> label.setText("Tombol ditekan!"));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Contoh JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
