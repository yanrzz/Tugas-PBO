import java.sql.SQLOutput;
import java.util.*;

public class UserInterface {
    public static void tampilkan(){
        System.out.println();
        System.out.println("+================+");
        System.out.println("|   pilih menu:  |");
        System.out.println("+-------------- -+");
        System.out.println("|  [C] : Create  |");
        System.out.println("|  [R] : Read    |");
        System.out.println("|  [U] : Update  |");
        System.out.println("|  [D] : Delete  |");
        System.out.println("|  [X] : Exit    |");
        System.out.println("==================");
    }
    public static void main(String[] args) {
        Database db = new Database();
        System.out.println(" SELAMAT DATANG DI SIMPLE DATA BASE");
        while(true){
            tampilkan();
            Scanner sc = new Scanner(System.in);
            System.out.print("Pilih menu: ");
            String pilih = sc.nextLine();
            pilih = pilih.toUpperCase();

            switch(pilih){
                case "C":
                    System.out.println("INFO: Anda memilih menu CREATE");
                    System.out.println("------------------------------------------");
                    System.out.println("INPUT DATA BARU");
                    System.out.print("NIM:             ");
                    String nim = sc.nextLine();
                    System.out.print("NAMA:            ");
                    String nama = sc.nextLine();
                    System.out.print("ALAMAT:          ");
                    String alamat = sc.nextLine();
                    System.out.print("SEMESTER:        ");
                    int semester = sc.nextInt();
                    System.out.print("SKS:             ");
                    int sks = sc.nextInt();
                    System.out.print("IPK:             ");
                    double ipk = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("------------------------------------------");
                    boolean status = db.insert(nim,nama,alamat,semester,sks,ipk);
                    if(status==true){
                        System.out.println("DATA ANDA BERHASIL DITAMBAHKAN");
                    }else{
                        System.out.println("NIM "+ nim + " SUDAH ADA DI DATABASE");
                        System.err.println("GAGAL MENAMBAHKAN DATA BARU");
                    }
                    System.out.println("------------------------------------------");
                    break;
                case "R":
                    System.out.println("INFO: Anda memili menu READ");
                    db.view();
                     break;
                case "U":
                    System.out.println("INFO: Anda memilih menu UPDATE");
                    db.view();
                    System.out.print("Input key(NIM yang akan di update: ");
                    String key = sc.nextLine();
                    int index = db.search(key);
                    if(index >= 0){
                        System.out.println("Anda akan mengupdate data "+ db.getData().get(index));
                        System.out.println("------------------------------------------");
                        System.out.println("INPUT DATA BARU");
                        System.out.print("NIM       :        ");
                        nim = sc.nextLine();
                        System.out.print("NAMA      :        ");
                        nama = sc.nextLine();
                        System.out.print("ALAMAT    :        ");
                        alamat = sc.nextLine();
                        System.out.print("SEMESTER  :        ");
                        semester = sc.nextInt();
                        System.out.print("SKS       :        ");
                        sks = sc.nextInt();
                        System.out.print("IPK       :        ");
                        ipk = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("------------------------------------------");
                        status = db.update(index,nim,nama,alamat,semester,sks,ipk);
                        if(status==true){
                            System.out.println("DATA BERHASIL DIPERBARUI");
                        }else{
                            System.err.println("GAGAT MEMPERBARUIDATA");
                        }
                        System.out.println("------------------------------------------");
                    }else{
                        System.err.println("Mahasiswa dengan NIM "+ key + " TIDAK ADA DI DATABASE");
                    }
                    break;
                case "D":
                    System.out.println("INFO: ANDA MEMILIH MENU DELETE");
                    db.view();
                    System.out.print("Input key(NIM mahasiswa yang akan dihapus): ");
                    key = sc.nextLine();
                    index = db.search(key);
                    if(index >= 0){
                        System.out.println("Apakah anda yakin akan menghapus data "+ db.getData().get(index));
                        System.out.print("Pilih : ");
                        pilih = sc.nextLine();
                        if(pilih.equalsIgnoreCase("Y"));
                        status = db.delete(index);
                        if(status==true){
                            System.out.println("DATA BERHASIL DIHAPUS");
                        }else{
                            System.err.println("GAGAL MENGHAPUS DATA");
                        }
                    }else{
                        System.err.println("Mahasiswa dengan NIM "+ key + " TIDAK ADA DI DATABASE");
                    }
                    break;
                case "X":
                    System.out.println("INFO: Anda memilih menu EXIT");
                    System.out.print("APAKAH ANDA YAKIN AKAN KELUAR? (Y/N): ");
                    pilih = sc.nextLine();
                    if(pilih.equalsIgnoreCase("Y")){
                        System.exit(0);
                    }
                    break;
            }
        }
    }
}
