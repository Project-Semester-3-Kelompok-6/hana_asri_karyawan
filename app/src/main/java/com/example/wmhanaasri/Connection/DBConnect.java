package com.example.wmhanaasri.Connection;

public class DBConnect {
    public static String ip = "192.168.1.14";

    public static final String BaseUrl = "http://"+ip+"//API_Mobile/";


    //Login URL
    public static final String urlLogin = "http://"+ip+"//API_Mobile/mobile_login.php";
    public static final String urlLupaPassword = "http://"+ip+"//API_Mobile/reset-password.php";
    public static final String urlNewPassword = "http://"+ip+"//API_Mobile/new-password.php";
    public static final String urlLogout = "http://"+ip+"//API_Mobile/logout.php";

    //Karyawan URL
    public static final String urlAbsensiMasuk = "http://"+ip+"//API_Mobile/absensi.php";
    public static final String tugasSedang = "http://"+ip+"//API_Mobile/list-job-dikerjakan.php";
    public static final String tugasSelesai = "http://"+ip+"//API_Mobile/list-job-selesai.php";

    public static final String perizinan = "http://"+ip+"//API_Mobile/perizinan.php";
    public static final String listIzin = "http://"+ip+"//API_Mobile/list-perizinan.php";
    public static final String listAbsensi = "http://"+ip+"//API_Mobile/list-absensi.php";

    //Manajer URL
    public static final String getDevisi = "http://"+ip+"//API_Mobile/getDevisi.php";
    public static final String getKaryawan = "http://"+ip+"//API_Mobile/getKaryawan.php";
    public static final String UrlTambahKaryawan = "http://"+ip+"//API_Mobile/tambah-akun.php";
    public static final String tugasManajerSedang = "http://"+ip+"//API_Mobile/manajer-tugas-sedang.php";
    public static final String tugasManajerSelesai = "http://"+ip+"//API_Mobile/manajer-tugas-selesai.php";
    public static final String manajerKaryawanTetap = "http://"+ip+"//API_Mobile/manajer-karyawan-tetap.php";
    public static final String manajerKaryawanTidakTetap = "http://"+ip+"//API_Mobile/manajer-karyawan-tidak-tetap.php";
    public static final String UrlTambahTugas = "http://"+ip+"//API_Mobile/manajer-tambah-tugas.php";

}
