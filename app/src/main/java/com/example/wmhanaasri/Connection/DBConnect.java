package com.example.wmhanaasri.Connection;

public class DBConnect {
    public static String ip = "192.168.1.26";

    //Login URL
    public static final String urlLogin = "http://"+ip+"//API_Mobile/mobile_login.php";
    public static final String urlLupaPassword = "http://"+ip+"//API_Mobile/reset-password.php";
    public static final String urlNewPassword = "http://"+ip+"//API_Mobile/new-password.php";
    public static final String urlLogout = "http://"+ip+"//API_Mobile/logout.php";

    //Karyawan URL
    public static final String urlAbsensiMasuk = "http://"+ip+"//API_Mobile/absensi.php";
    public static final String urlAbsensiKeluar = "http://"+ip+"//API_Mobile/mobile_login.php";
    public static final String urlTugas = "http://"+ip+"//API_Mobile/mobile_login.php";
    public static final String urlIzin = "http://"+ip+"//API_Mobile/mobile_login.php";

    //Manajer URL
    public static final String getDevisi = "http://"+ip+"//API_Mobile/getDevisi.php";

}
