package test;
import myprogram.Mahasiswa;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        ArrayList<Mahasiswa> dm = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Mahasiswa m = new Mahasiswa();
            m.setNama("Mahasiswa "+ i);
            m.setAlamat("Alamat "+ i);
            m.setNim("D0224310" + String.format("%03", i));
            dm.add(m);
        }
        for (Mahasiswa m : dm) {
            System.out.println(m.toString());
            System.out.println("---------------------------");
        }

    }
}
