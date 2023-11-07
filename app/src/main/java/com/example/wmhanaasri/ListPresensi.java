package com.example.wmhanaasri;

public class ListPresensi {
    private String nama, divisi, status, jam;

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {

        this.nama = nama;
    }

    public String getDivisi() {
        return divisi;
    }

    public String getStatus() {
        return status;
    }

    public String getJam() {
        return jam;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public ListPresensi(String nama, String divisi, String status, String jam) {
        this.nama = nama;
        this.divisi = divisi;
        this.status = status;
        this.jam = jam;
    }
}
