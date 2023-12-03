package com.example.wmhanaasri.Karyawan.izin.adapter;

public class ListDataPerizinan {
    private String Tanggal;
    private String Status;
    private String BuktiFoto;

    public ListDataPerizinan(String Tanggal , String Status, String BuktiFoto){
        this.Tanggal = Tanggal;
        this.Status = Status;
        this.BuktiFoto = BuktiFoto;
    }
    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getBuktiFoto() {
        return BuktiFoto;
    }

    public void setBuktiFoto(String buktiFoto) {
        BuktiFoto = buktiFoto;
    }



}
