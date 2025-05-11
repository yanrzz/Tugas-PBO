package com.example.tugasp4;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TUGAS3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Komponen GUI
        Label labelAngka = new Label("Nilai Angka (0-100):");
        TextField fieldAngka = new TextField();

        Label labelHuruf = new Label("Nilai Huruf:");
        ComboBox<String> comboHuruf = new ComboBox<>();
        comboHuruf.getItems().addAll("A", "A-", "B+", "B", "B-", "C", "D", "E");

        Button btnAngkaKeHuruf = new Button("Konversi ke Huruf");
        Button btnHurufKeAngka = new Button("Konversi ke Angka");

        Label hasil = new Label();

        // Tombol: angka → huruf
        btnAngkaKeHuruf.setOnAction(e -> {
            try {
                double nilai = Double.parseDouble(fieldAngka.getText());
                if (nilai < 0 || nilai > 100) {
                    hasil.setText("Nilai harus antara 0 - 100.");
                } else {
                    hasil.setText("Nilai Huruf: " + konversiAngkaKeHuruf(nilai));
                }
            } catch (NumberFormatException ex) {
                hasil.setText("Masukkan nilai angka yang valid.");
            }
        });

        // Tombol: huruf → angka
        btnHurufKeAngka.setOnAction(e -> {
            String huruf = comboHuruf.getValue();
            if (huruf == null) {
                hasil.setText("Pilih nilai huruf terlebih dahulu.");
            } else {
                hasil.setText("Nilai Angka rata-rata: " + konversiHurufKeAngka(huruf));
            }
        });

        VBox root = new VBox(10,
                labelAngka, fieldAngka,
                btnAngkaKeHuruf,
                labelHuruf, comboHuruf,
                btnHurufKeAngka,
                hasil
        );
        root.setPadding(new Insets(15));

        primaryStage.setScene(new Scene(root, 300, 350));
        primaryStage.setTitle("Konversi Nilai Mata Kuliah");
        primaryStage.show();
    }

    // Konversi dari angka ke huruf
    private String konversiAngkaKeHuruf(double nilai) {
        if (nilai >= 85) return "A";
        else if (nilai >= 80) return "A-";
        else if (nilai >= 75) return "B+";
        else if (nilai >= 70) return "B";
        else if (nilai >= 65) return "B-";
        else if (nilai >= 60) return "C";
        else if (nilai >= 50) return "D";
        else return "E";
    }

    // Konversi dari huruf ke rata-rata nilai angka
    private String konversiHurufKeAngka(String huruf) {
        return switch (huruf) {
            case "A" -> "≥ 85";
            case "A-" -> "80 - 84";
            case "B+" -> "75 - 79";
            case "B" -> "70 - 74";
            case "B-" -> "65 - 69";
            case "C" -> "60 - 64";
            case "D" -> "50 - 59";
            case "E" -> "< 50";
            default -> "Tidak valid";
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
