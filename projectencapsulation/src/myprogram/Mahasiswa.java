package myprogram;

public class Mahasiswa extends Manusia{
    private String nim;

    public void setNim(String nim){
        this.nim = nim;
    }

    public String getNim() {
        return this.nim;
    }
    public void setNama(String nama){
        super.setNama(nama);
    }
    public String getNama(){
        return super.getNama();
    }
    public void setAlamat(String alamat) {
        super.setAlamat(alamat);
    }

    public String getAlamat() {
        return super.getAlamat();
    }
    @Override
    public String toString() {
        return "Nama: " + getNama() + "\n" +
                "NIM: " + getNim() + "\n" +
                "Alamat: " + getAlamat();
    }
}
