package com.example.wmhanaasri;

public class ListAktivitas {
    private String judul;
    private String nama;
    private String tenggat;

    public ListAktivitas(String judul, String nama, String tenggat){
        this.judul = judul;
        this.nama = nama;
        this.tenggat = tenggat;
    }

    public String getJudul(){
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTenggat(String tenggat) {
        this.tenggat = tenggat;
    }

    public String getTenggat(){
        return tenggat;
    }
}
