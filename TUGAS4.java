package com.example.tugasp4;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TUGAS4 extends Application {

    private static final double KURS_USD_TO_IDR = 16543.0;

    @Override
    public void start(Stage primaryStage) {
        Label labelInput = new Label("Masukkan jumlah:");
        TextField fieldInput = new TextField();

        Button btnToRupiah = new Button("Dollar ke Rupiah");
        Button btnToDollar = new Button("Rupiah ke Dollar");

        Label hasil = new Label();

        btnToRupiah.setOnAction(e -> {
            try {
                double dollar = Double.parseDouble(fieldInput.getText());
                double rupiah = dollar * KURS_USD_TO_IDR;
                hasil.setText(String.format("Rp%,.2f", rupiah));
            } catch (NumberFormatException ex) {
                hasil.setText("Masukkan angka yang valid.");
            }
        });

        btnToDollar.setOnAction(e -> {
            try {
                double rupiah = Double.parseDouble(fieldInput.getText());
                double dollar = rupiah / KURS_USD_TO_IDR;
                hasil.setText(String.format("$%.2f", dollar));
            } catch (NumberFormatException ex) {
                hasil.setText("Masukkan angka yang valid.");
            }
        });

        VBox root = new VBox(10, labelInput, fieldInput, btnToRupiah, btnToDollar, hasil);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Konversi Dollar ⇄ Rupiah");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
