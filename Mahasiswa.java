package com.example.tugasp4;
public class Mahasiswa {
    String kodeMk,Matakuliah,sks, Nilai;

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public String getMatakuliah() {
        return Matakuliah;
    }

    public void setMatakuliah(String Matakuliah) {
        this.Matakuliah = Matakuliah;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getNilai() {
        return Nilai;
    }

    public void setNilai(String Nilai) {
        this.Nilai = Nilai;
    }

    public Mahasiswa(String kodeMk, String Matakuliah, String sks,
                     String Nilai) {
        this.kodeMk = kodeMk;
        this.Matakuliah = Matakuliah;
        this.sks = sks;
        this.Nilai = Nilai;
    }
}
