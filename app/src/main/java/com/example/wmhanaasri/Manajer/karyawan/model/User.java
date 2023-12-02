package com.example.wmhanaasri.Manajer.karyawan.model;

public class User {
    public User(String nama, String email, String role) {
        this.nama = nama;
        this.email = email;
        this.role = role;
    }

    private String nama;
    private String email;
    private String role;
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
