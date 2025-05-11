package com.example.tugasp4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TUGAS2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label labelInput = new Label("Masukkan nilai:");
        TextField inputField = new TextField();

        ComboBox<String> dariSatuan = new ComboBox<>();
        ComboBox<String> keSatuan = new ComboBox<>();
        String[] satuan = {"km", "hm", "dam", "m", "dm", "cm", "mm"};
        dariSatuan.getItems().addAll(satuan);
        keSatuan.getItems().addAll(satuan);

        Button tombolKonversi = new Button("Konversi");
        Label hasilLabel = new Label();

        tombolKonversi.setOnAction(e -> {
            try {
                double nilai = Double.parseDouble(inputField.getText());
                String dari = dariSatuan.getValue();
                String ke = keSatuan.getValue();

                if (dari == null || ke == null) {
                    hasilLabel.setText("Pilih satuan terlebih dahulu!");
                    return;
                }


                double dalamMeter = nilai * faktorKeMeter(dari);
                double hasil = dalamMeter / faktorKeMeter(ke);

                hasilLabel.setText(String.format("%.4f %s", hasil, ke));
            } catch (NumberFormatException ex) {
                hasilLabel.setText("Masukkan angka yang valid.");
            }
        });

        VBox root = new VBox(10, labelInput, inputField,
                new Label("Dari satuan:"), dariSatuan,
                new Label("Ke satuan:"), keSatuan,
                tombolKonversi, hasilLabel);
        root.setPadding(new Insets(500));

        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Konversi Panjang SI");
        primaryStage.show();
    }


    private double faktorKeMeter(String satuan) {
        return switch (satuan) {
            case "km" -> 1000;
            case "hm" -> 100;
            case "dam" -> 10;
            case "m" -> 1;
            case "dm" -> 0.1;
            case "cm" -> 0.01;
            case "mm" -> 0.001;
            default -> 1;
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
