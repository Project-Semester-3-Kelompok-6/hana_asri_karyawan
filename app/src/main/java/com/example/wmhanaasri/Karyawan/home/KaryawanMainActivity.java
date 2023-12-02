package com.example.wmhanaasri.Karyawan.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.wmhanaasri.Karyawan.absensi.AbsensiFragment;
import com.example.wmhanaasri.Karyawan.home.HomeFragment;
import com.example.wmhanaasri.Karyawan.izin.IzinFragment;
import com.example.wmhanaasri.Karyawan.tugas.TugasFragment;
import com.example.wmhanaasri.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import com.example.wmhanaasri.Karyawan.adapter.ViewPagerAdapter;

public class KaryawanMainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private AbsensiFragment tugasFragment;
    private TugasFragment rekapFragment;
    private IzinFragment karyawanFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.karyawan_activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavbar);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Inisialisasi fragment di sini
        homeFragment = new HomeFragment();
        tugasFragment = new AbsensiFragment();
        rekapFragment = new TugasFragment();
        karyawanFragment = new IzinFragment();

        //gawe swipe per fragment
        ViewPager2 viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        int itemId = item.getItemId(); // Simpan nilai item.getItemId() dalam variabel itemId

        if (itemId == R.id.home) {
            selectedFragment = new HomeFragment();
        } else if (itemId == R.id.absensi) {
            selectedFragment = new AbsensiFragment();
        } else if (itemId == R.id.tugas) {
            selectedFragment = new TugasFragment();
        } else if (itemId == R.id.izin) {
            selectedFragment = new IzinFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, selectedFragment).commit();
            return true;
        }

        return false;
    }

}