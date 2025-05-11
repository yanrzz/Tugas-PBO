package com.example.tugasp4;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.example.tugasp4.Mahasiswa;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class Myjendela extends Application {
    private TextField tkodeMk;
    private TextField tMatakuliah;
    private TextField tSKS;
    private TextField tNilai;
    private TableView<com.example.tugasp4.Mahasiswa> table;
    private ObservableList<com.example.tugasp4.Mahasiswa> dataList;
    private com.example.tugasp4.Mahasiswa selectedMahasiswa = null;

    public Myjendela() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("CRUD SEDERHANA");
        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(true);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        VBox side = new VBox((double)10.0F);
        side.setPadding(new Insets((double)20.0F, (double)10.0F, (double)10.0F, (double)20.0F));
        side.setPrefWidth((double)300.0F);
        Label title = new Label("Data Mahasiswa");
        this.tkodeMk = new TextField();
        this.tMatakuliah = new TextField();
        this.tSKS = new TextField();
        this.tNilai = new TextField();
        side.getChildren().addAll(new Node[]{title, this.createField("KODE MK", this.tkodeMk), this.createField("MATAKULIAH", this.tMatakuliah), this.createField("SKS", this.tSKS), this.createField("Nilai", this.tNilai)});
        Button btnTambah = new Button("Tambah Data");
        Button btnEdit = new Button("Edit Data");
        Button btnDelete = new Button("Hapus Data");
        btnTambah.setOnAction((e) -> this.tambahData());
        btnEdit.setOnAction((e) -> this.editData());
        btnDelete.setOnAction((e) -> this.hapusData());
        side.getChildren().addAll(new Node[]{btnTambah, btnEdit, btnDelete});
        String[] columnProperties = new String[]{"kodeMk", "matakuliah", "sks", "nilai"};
        String[] columnLabels = new String[]{"Kode MK", "Matakuliah", "SKS", "Nilai"};

        this.table = new TableView<>();
        this.dataList = FXCollections.observableArrayList();

        for (int i = 0; i < columnProperties.length; i++) {
            TableColumn<com.example.tugasp4.Mahasiswa, String> col = new TableColumn<>(columnLabels[i]);
            col.setCellValueFactory(new PropertyValueFactory<>(columnProperties[i]));
            col.setPrefWidth(150.0);
            this.table.getColumns().add(col);
        }


        this.table.setItems(this.dataList);
        this.table.setOnMouseClicked((e) -> this.pilihMahasiswa());
        root.setLeft(side);
        root.setCenter(this.table);
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        side.setStyle("-fx-background-color: #2c3e50;");
        title.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        btnTambah.setStyle("-fx-background-color: #27ea60; -fx-text-fill: white;");
        btnEdit.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
        btnDelete.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        this.table.setStyle("-fx-font-size: 14px;");
        this.muatDataDariCSV();
        stage.show();
    }

    private HBox createField(String labelText, TextField textField) {
        Label label = new Label(labelText + ":");
        label.setPrefWidth((double)80.0F);
        textField.setMaxWidth((double)200.0F);
        HBox field = new HBox((double)10.0F, new Node[]{label, textField});
        textField.setStyle("-fx-background-color: transparent; -fx-border-color: #3498db; -fx-border-width: 2px; -fx-text-fill: white; -fx-padding: 5px;");
        label.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        return field;
    }

    private void muatDataDariCSV() {
        String filename = "src/main/java/com/example/tugasp4/database.csv";
        Path path = Path.of(filename);
        List<com.example.tugasp4.Mahasiswa> data = new ArrayList();

        try {
            List<String> lines = Files.readAllLines(path);

            for(int i = 1; i < lines.size(); ++i) {
                String line = ((String)lines.get(i)).trim();
                if (!line.isEmpty()) {
                    String[] elems = line.split(";");
                    if (elems.length >= 4) {
                        data.add(new com.example.tugasp4.Mahasiswa(elems[0], elems[1], elems[2], elems[3]));
                    }
                }
            }
        } catch (IOException var8) {
        }

        this.dataList.clear();
        this.dataList.addAll(data);
    }

    private void tambahData() {
        if (!this.tkodeMk.getText().isEmpty()  &&  !this.tSKS.getText().isEmpty() && !this.tNilai.getText().isEmpty()) {
            com.example.tugasp4.Mahasiswa m = new com.example.tugasp4.Mahasiswa(this.tkodeMk.getText(), this.tMatakuliah.getText() ,this.tSKS.getText(), this.tNilai.getText());
            this.dataList.add(m);
            this.clearField();
            this.simpanDataKeCSV();
        } else {
            System.out.println("Semua field harus diisi!");
        }
    }

    private void editData() {
        if (this.selectedMahasiswa != null) {
            this.selectedMahasiswa.setKodeMk(this.tkodeMk.getText());
            this.selectedMahasiswa.setMatakuliah(this.tMatakuliah.getText());
            this.selectedMahasiswa.setSks(this.tSKS.getText());
            this.selectedMahasiswa.setNilai(this.tNilai.getText());
            this.table.refresh();
            this.clearField();
            this.simpanDataKeCSV();
        }

    }

    private void hapusData() {
        if (this.selectedMahasiswa != null) {
            this.dataList.remove(this.selectedMahasiswa);
            this.clearField();
            this.simpanDataKeCSV();
        }

    }

    private void pilihMahasiswa() {
        this.selectedMahasiswa = (com.example.tugasp4.Mahasiswa)this.table.getSelectionModel().getSelectedItem();
        if (this.selectedMahasiswa != null) {
            this.tkodeMk.setText(this.selectedMahasiswa.getKodeMk());
            this.tMatakuliah.setText(this.selectedMahasiswa.getMatakuliah());
            this.tSKS.setText(this.selectedMahasiswa.getSks());
            this.tNilai.setText(this.selectedMahasiswa.getNilai());
        }

    }

    private void clearField() {
        this.tkodeMk.clear();
        this.tMatakuliah.clear();
        this.tSKS.clear();
        this.tNilai.clear();
    }

    private void simpanDataKeCSV() {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/com/example/tugasp4/database.csv"))) {
                writer.write("Kode MK; Matakuliah; SKS; Nilai\n");

                for(com.example.tugasp4.Mahasiswa mhs : this.dataList) {
                    writer.write(String.format("%s;%s;%s;%s\n", mhs.getKodeMk(), mhs.getMatakuliah(), mhs.getSks(), mhs.getNilai()));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

