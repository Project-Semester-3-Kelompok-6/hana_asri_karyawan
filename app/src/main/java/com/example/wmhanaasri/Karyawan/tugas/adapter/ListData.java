package com.example.wmhanaasri.Karyawan.tugas.adapter;

public class ListData {
    private String Judul;
    private String Deskripsi;
    private String Tanggal;
    public ListData(String Judul, String Deskripsi, String Tanggal){
        this.Judul = Judul;
        this.Deskripsi = Deskripsi;
        this.Tanggal = Tanggal;
    }
    //getter
    public String getJudul() {
        return Judul;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public String getTanggal() {
        return Tanggal;
    }
    //setter

    public void setJudul(String judul) {
        Judul = judul;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public void setWaktu(String Tanggal) {
        Tanggal = Tanggal;
    }
}
